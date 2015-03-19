/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.starnberger.tokenengine.connector.bluez.BluezConnector;
import com.starnberger.tokenengine.connector.controller.WriteConfigurationController;
import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.dao.TokenConfigurationManager;
import com.starnberger.tokenofflineengine.dao.TokenManager;
import com.starnberger.tokenofflineengine.model.Task;
import com.starnberger.tokenofflineengine.model.Token;
import com.starnberger.tokenofflineengine.model.TokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class UpgradeTokenConfigTask extends AbstractTask {
	private static final String CONFIG_UPGRADE_FLAG = "2b40";
	private static final Logger logger = LogManager.getLogger(UpgradeTokenConfigTask.class.getName());
	private final BluezConnector connector;

	/**
	 * @param task
	 * @param connector
	 */
	public UpgradeTokenConfigTask(Task task, BluezConnector connector) {
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
		Long relatedId = task.getRelatedId();
		final Token token = TokenManager.getInstance().findByRemoteId(relatedId);
		if (token == null) {
			logger.error("Token " + relatedId + " not found.");
			updateTask(Status.FAILED);
			return false;
		}
		if (token.getConfigId() == null) {
			logger.fatal("TokenConfiguration " + token.getConfigId() + " not found for Token " + token);
			updateTask(Status.FAILED);
			return false;
		}
		TokenConfiguration configuration = TokenConfigurationManager.getInstance().findByRemoteId(token.getConfigId());
		if (configuration == null) {
			logger.fatal("TokenConfiguration " + token.getConfigId() + " not found for Token " + token);
			updateTask(Status.FAILED);
			return false;
		}
		updateTask(Status.IN_PROGRESS);
		byte[] configurationArray = TokenConfigurationManager.getInstance().generateByteArrayFromConfig(configuration,
				token);
		System.out.println("Byte array length: " + configurationArray.length);
		System.out.println("HEX Byte Array: " + Hex.encodeHexString(configurationArray));
		System.out.println("Byte Array: " + ArrayUtils.toString(configurationArray));
		connector.writeService(token.getMac(), CONFIG_UPGRADE_FLAG,
				new WriteConfigurationController(configurationArray) {

					@Override
					public void onFailed(String msg, Exception e) {
						logger.error("failed to write " + msg, e);
					}

					@Override
					public void onSuccessPartMessage(String address, byte[] writeData, int bytesWritten) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String address, int bytesWritten) {
						logger.info("written to " + " written: " + bytesWritten + " bytes");
						TokenManager.getInstance().markTokenConfigUpgradeDone(token);
						updateTask(Status.COMPLETED);
					}

				});
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenofflineengine.ITask#getFollowUpTasks()
	 */
	@Override
	public List<Task> getFollowUpTasks() {
		return null;
	}

}
