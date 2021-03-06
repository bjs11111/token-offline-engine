/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import com.starnberger.tokenengine.connector.bluez.BluezConnector;
import com.starnberger.tokenengine.connector.controller.ClearLogController;
import com.starnberger.tokenengine.connector.parser.FetchLogParserBitByBit;
import com.starnberger.tokenengine.connector.parser.SensorList;
import com.starnberger.tokenengine.connector.parser.SensorValue;
import com.starnberger.tokenengine.connector.parser.SensorValue.SensorType;
import com.starnberger.tokenofflineengine.common.SensorTypeUtility;
import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.dao.EMF;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.dao.SensorDataManager;
import com.starnberger.tokenofflineengine.dao.TokenConfigurationManager;
import com.starnberger.tokenofflineengine.model.SensorConfigValue;
import com.starnberger.tokenofflineengine.model.Task;
import com.starnberger.tokenofflineengine.model.Token;
import com.starnberger.tokenofflineengine.model.TokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class DownloadTokenLogTask extends AbstractTask {
	private static final String CLEAR_LOG = "2b50";
	private static final String SERVICE_READ = "1940";
	private static final int TIMEOUT = 40000;
	private static final String SERVICE_WRITE = CLEAR_LOG;
	private static final Logger logger = Logger.getLogger(DownloadTokenLogTask.class.getName());

	private final BluezConnector connector;
	private EntityManager em;

	/**
	 * @param task
	 * @param connector
	 */
	public DownloadTokenLogTask(Task task, BluezConnector connector) {
		super(task);
		this.connector = connector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenofflineengine.ITask#execute()
	 */
	@Override
	public boolean execute() {
		TokenInfoStructure tokenInfo = TokenInfoCache.getInstace().getTokenInfo(task.getRelatedId());
		if (tokenInfo == null) {
			logger.error("Token " + task.getRelatedId() + " not found. Download token log aborted.");
			updateTask(Status.FAILED);
			return false;
		}

		// Create entity manager
		em = EMF.get().createEntityManager();
		// Load configuration
		Token token = tokenInfo.token;
		TokenConfiguration config = TokenConfigurationManager.getInstance().findByRemoteId(token.getConfigId());
		Map<String, Map<String, SensorConfigValue>> sensorConfigValues = TokenConfigurationManager.getInstance()
				.loadSensorConfigValues(config, token, em);
		em.close();

		// Loop over all sensor types and read the log for every sensor
		Iterator<String> positionIterator = tokenInfo.sensorTypesByPosition.keySet().iterator();
		while (positionIterator.hasNext()) {
			String position = (String) positionIterator.next();
			doDownloadTokenLog(tokenInfo, sensorConfigValues, position);
		}
		return true;
	}

	/**
	 * @param tokenInfo
	 * @param sensorConfigValues
	 * @param position
	 */
	public void doDownloadTokenLog(TokenInfoStructure tokenInfo,
			Map<String, Map<String, SensorConfigValue>> sensorConfigValues, String position) {
		// Get needed data
		SensorType sensorTypeForPosition = SensorTypeUtility.getSensorTypeForPosition(position);
		Map<String, SensorConfigValue> sensorConfigValueMap = sensorConfigValues.get(position);
		
		// Check if sensor is enabled
		SensorConfigValue isSensorEnabledValue = sensorConfigValueMap.get("sensorEnabled");
		boolean isSensorEnabled = false;
		if (isSensorEnabledValue != null)
			isSensorEnabled = Boolean.parseBoolean(isSensorEnabledValue.getValue());
		
		// Skip sensor if disabled
		if (!isSensorEnabled)
			return;

		try {
			// Fetch regular log
			fetchLogBitByBit(tokenInfo, false, sensorTypeForPosition);
			// Fetch alarm log
			fetchLogBitByBit(tokenInfo, true, sensorTypeForPosition);
		} catch (DecoderException e) {
			logger.fatal(e);
			rollback();
			updateTask(Status.FAILED);
		}
	}

	/**
	 * @param tokenInfo
	 * @param alarmLog
	 * @param sensorType
	 * @throws DecoderException
	 */
	private void fetchLogBitByBit(final TokenInfoStructure tokenInfo, final boolean alarmLog,
			final SensorType sensorType) throws DecoderException {
		Token token = tokenInfo.token;
		connector.writeServiceAndListen(token.getMac(), SERVICE_WRITE, SERVICE_READ, TIMEOUT,
				new FetchLogParserBitByBit(sensorType, alarmLog, connector, token.getMac(), SERVICE_WRITE,
						SERVICE_READ, TIMEOUT, 10) {

					@Override
					public void onSuccessfullyParsed(SensorList sensorValues) {
						storeSensorList(tokenInfo, sensorValues);
						try {
							clearLog(tokenInfo.token.getMac(), alarmLog, sensorType);
						} catch (DecoderException e) {
							logger.error(e);
							rollback();
							updateTask(Status.FAILED);
						}
					}

					@Override
					public void onFailedAfterRetry(String address, String msg, Exception e) {
						logger.error("fetch log failed for address: " + address, e);
						rollback();
						updateTask(Status.FAILED);
					}
				});
	}

	/**
	 * @param tokenInfo
	 * @param alarmLog
	 * @param sensorType
	 * @throws DecoderException
	 */
	protected void clearLog(String mac, final boolean alarmLog, SensorType sensorType) throws DecoderException {
		connector.writeService(mac, CLEAR_LOG, new ClearLogController(sensorType, alarmLog) {

			@Override
			public void onSuccessPartMessage(String address, byte[] message, int bytesWritten) {
				String writeData = new String(Hex.encodeHex(message));
				logger.debug("CLEAR LOG,written to " + address + " " + writeData + " written: " + bytesWritten
						+ " bytes");
			}

			@Override
			public void onSuccess(String address, int bytesWritten) {
				logger.info("CLEAR LOG,written to " + address + " written: " + bytesWritten + " bytes");
				commit();
				updateTask(Status.COMPLETED);
			}

			@Override
			public void onFailed(String address, String msg, Exception e) {
				logger.error("failed to clear log " + this.sensorType + "," + msg, e);
				rollback();
				updateTask(Status.FAILED);
			}

		});
	}

	/**
	 * Store all logged sensor values.
	 * 
	 * @param tokenInfo
	 * @param sensorValues
	 */
	protected void storeSensorList(final TokenInfoStructure tokenInfo, SensorList sensorValues) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		Iterator<SensorValue> iterator = sensorValues.iterator();
		while (iterator.hasNext()) {
			SensorValue sensorValue = (SensorValue) iterator.next();
			try {
				SensorDataManager.getInstance()
						.addNewRecord(em, sensorValue, GatewayManager.getMyRemoteId(), tokenInfo);
			} catch (NumberFormatException e) {
				logger.fatal(e);
				rollback();
				updateTask(Status.FAILED);
			} catch (ParseException e) {
				logger.fatal(e);
				rollback();
				updateTask(Status.FAILED);
			}
		}
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Task> getFollowUpTasks() {
		return null;
	}

	/**
	 * 
	 */
	public void rollback() {
		em.getTransaction().rollback();
		em.close();
	}

	/**
	 * 
	 */
	public void commit() {
		em.getTransaction().commit();
		em.close();
	}

}
