/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.starnberger.tokenengine.connector.bluez.BluezConnector;
import com.starnberger.tokenengine.connector.examples.GatewayExample;
import com.starnberger.tokenengine.connector.parser.AdvertisingPackageParser;
import com.starnberger.tokenengine.connector.parser.SensorList;
import com.starnberger.tokenengine.connector.parser.SensorValue;
import com.starnberger.tokenofflineengine.common.TaskType;
import com.starnberger.tokenofflineengine.dao.AuthenticationManager;
import com.starnberger.tokenofflineengine.dao.EMF;
import com.starnberger.tokenofflineengine.dao.GatewayConfigurationManager;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.dao.SensorDataManager;
import com.starnberger.tokenofflineengine.dao.TaskManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.GatewayConfiguration;
import com.starnberger.tokenofflineengine.model.Task;
import com.starnberger.tokenofflineengine.model.Token;

/**
 * @author Roman Kaufmann
 *
 */
public class Main {
	private static final Logger logger = Logger.getLogger(Main.class.getName());

	private GatewayConfiguration config = null;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
	private final AdvertisingPackageParser listener = new AdvertisingPackageParser() {

		@Override
		public void onSuccessfullyParsed(SensorList sensorValues) {
			storeSensorList(sensorValues);
		}
	};
	private final Runnable addSyncTask = new Runnable() {

		@Override
		public void run() {
			Task uploadTask = new Task();
			uploadTask.setType(TaskType.UPLOAD);
			TaskQueue.addTaskToQueue(uploadTask);
			addDownloadTask();
		}
	};
	private final Runnable uploadStatusTask = new Runnable() {

		@Override
		public void run() {
			Task uploadStatusTask = new Task();
			uploadStatusTask.setType(TaskType.UPLOAD_STATUS);
			TaskQueue.addTaskToQueue(uploadStatusTask);
		}
	};
	private final Runnable uploadLogTask = new Runnable() {

		@Override
		public void run() {
			Task uploadLogs = new Task();
			uploadLogs.setType(TaskType.UPLOAD_LOGS);
			TaskQueue.addTaskToQueue(uploadLogs);
		}
	};

	private ScheduledFuture<?> syncFuture;
	private ScheduledFuture<?> statusUpdateFuture;
	private ScheduledFuture<?> logUploadFuture;

	private final BluezConnector connector;

	private Map<String, String> upgradeTokens = new HashMap<String, String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main main = new Main();
			main.startUpChecks();
			main.workerLoop();
			main.shutdownTasks();
		} catch (Throwable e) {
			logger.fatal(e);
		}
	}

	/**
	 * Default constructor.
	 */
	public Main() {
		if (logger.isInfoEnabled())
			if (logger.isInfoEnabled())
				logger.info("Starting Token Offline Engine Version: " + Version.INSTANCE.getVersion());
		connector = new BluezConnector();
		GatewayExample.checkBluetoothDevice(connector, 5);
	}

	/**
	 * Checks if this gateway is already registered. If not it tries to register
	 * itself at the token engine.
	 */
	private void startUpChecks() {
		if (logger.isInfoEnabled())
			logger.info("Performing startup checks");
		if (!AuthenticationManager.getInstance().isAlreadyRegistered()) {
			if (AuthenticationManager.getInstance().doRegister()) {
				addDownloadTask();
			}
		} else {
			addDownloadTask();
		}
	}

	/**
	 * Adds a download task to the current task list.
	 */
	private void addDownloadTask() {
		Task downloadTask = new Task();
		downloadTask.setType(TaskType.DOWNLOAD);
		TaskQueue.addTaskToQueue(downloadTask);
	}

	/**
	 * This method does a controlled shutdown of the Token Offline Engine.
	 * 
	 * @param doReboot
	 *            when set to reboot, then a different return code is set and
	 *            the outer shell script restarts the application
	 */
	private void doShutDown(boolean doReboot) {
		shutdownTasks();
		if (doReboot) {
			if (logger.isInfoEnabled())
				logger.info("Rebooting the system");
			System.exit(1);
		}
		if (logger.isInfoEnabled())
			logger.info("Controlled shutdown");
		System.exit(0);
	}

	/**
	 * Main worker loop for token offline engine.
	 */
	private void workerLoop() {
		if (logger.isInfoEnabled())
			logger.info("Starting main logic");
		registerBroadcastListener();
		prepareScheduledTasks();
		while (true) {
			try {
				processTask(TaskQueue.take());
			} catch (Exception e) {
				logger.fatal(e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Registers a generic broadcast listener that immediately stores the
	 * received sensor values.
	 */
	public void registerBroadcastListener() {
		listener.notifyAfterAmountOfValues = 10;
		connector.registerAdvertisingListener(listener);
	}

	/**
	 * Prepares all regular appearing tasks
	 */
	private void prepareScheduledTasks() {
		if (config == null) {
			config = createDefaultGatewayConfig();
		}
		stopScheduledTasks();
		startScheduledTasks(this.config);
	}

	/**
	 * @param configuration
	 */
	protected void startScheduledTasks(GatewayConfiguration configuration) {
		if (configuration != null)
			this.config = configuration;
		else
			this.config = createDefaultGatewayConfig();
		syncFuture = scheduler.scheduleAtFixedRate(addSyncTask, config.getSyncInterval(), config.getSyncInterval(),
				TimeUnit.MINUTES);
		statusUpdateFuture = scheduler.scheduleAtFixedRate(uploadStatusTask, config.getStatusUpdateInterval(),
				config.getStatusUpdateInterval(), TimeUnit.MINUTES);
		logUploadFuture = scheduler.scheduleAtFixedRate(uploadLogTask, config.getLogUpdateInterval(),
				config.getLogUpdateInterval(), TimeUnit.MINUTES);
	}

	/**
	 * @return
	 */
	private GatewayConfiguration createDefaultGatewayConfig() {
		GatewayConfiguration config = new GatewayConfiguration();
		// TODO: Find better default values
		config.setSyncInterval(1);
		config.setStatusUpdateInterval(2);
		config.setLogUpdateInterval(3);
		return config;
	}

	/**
	 * 
	 */
	protected void stopScheduledTasks() {
		if (syncFuture != null)
			syncFuture.cancel(false);
		if (statusUpdateFuture != null)
			statusUpdateFuture.cancel(false);
		if (logUploadFuture != null)
			logUploadFuture.cancel(false);
	}

	/**
	 * @param task
	 */
	private void processTask(Task task) {
		if (task == null) {
			logger.warn("Null task was inserted into queue!");
			return;
		}
		if (logger.isInfoEnabled())
			logger.info("Processing task: " + task.toString());
		switch (task.getType()) {
		case SHUTDOWN:
			doShutDown(false);
			break;
		case REBOOT:
			doShutDown(true);
			break;
		case DOWNLOAD:
			doDownload(task);
			break;
		case UPLOAD:
			uploadSensorData(task);
			break;
		case UPGRADE_FW:
			upgradeGateway(task);
			break;
		case UPGRADE_GW_CONFIG:
			upgradeGatewayConfig(task);
			break;
		case UPLOAD_STATUS:
			uploadGatewayStatus(task);
			break;
		case UPGRADE_TOKEN:
			upgradeToken(task);
			break;
		case SCAN_TOKEN:
			scanForToken(task);
			break;
		case STOP_SCAN:
			stopTokenScan(task);
			break;
		case UPLOAD_LOGS:
			uploadLogs(task);
			break;
		case STOLEN:
			reportStolen(task);
			break;
		case CHANGE_CELL_STATUS:
			changeCellStatus(task);
			break;
		case OS_UPDATE:
			updateOs(task);
			break;
		case DOWNLOAD_TOKENLOGS:
			dowloadTokenLogs(task);
			break;
		default:
			break;
		}
	}

	/**
	 * @param task
	 */
	private void upgradeGatewayConfig(Task task) {
		if (logger.isInfoEnabled())
			logger.info("Starting gateway config upgrade");
		UpgradeGatewayConfigTask upgradeGatewayConfigTask = new UpgradeGatewayConfigTask(task, this);
		upgradeGatewayConfigTask.execute();
	}

	/**
	 * @param task
	 */
	private void dowloadTokenLogs(Task task) {
		if (logger.isInfoEnabled())
			logger.info("Starting sensor upload");
		DownloadTokenLogTask downloadTokenLogTask = new DownloadTokenLogTask(task, connector);
		downloadTokenLogTask.execute();
	}

	/**
	 * @param task
	 */
	private void uploadSensorData(Task task) {
		if (logger.isInfoEnabled())
			logger.info("Starting sensor upload");
		UploadSensorDataTask uploadSensorDataTask = new UploadSensorDataTask(task);
		boolean result = uploadSensorDataTask.execute();
		if (result == true) {
			GatewayManager.getInstance().updateUploadDate(new Date());
		}
		if (logger.isInfoEnabled())
			logger.info("Download task execution returned: " + result);
	}

	private void upgradeGateway(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Starting gateway upgrade");
	}

	private void uploadGatewayStatus(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Starting gateway status upload");
	}

	/**
	 * @param task
	 */
	private void upgradeToken(Task task) {
		if (logger.isInfoEnabled())
			logger.info("Starting token upgrade");
		UpgradeTokenConfigTask upgradeTokenConfigTask = new UpgradeTokenConfigTask(task, connector, this);
		upgradeTokenConfigTask.execute();
	}

	/**
	 * @param result
	 * @param task
	 */
	public synchronized void upgradeTokenDone(boolean result, Task task) {
		if (task.getParameters() != null) {
			String mac = task.getParameters().get(0);
			String removedValue = upgradeTokens.remove(mac);
			if (logger.isInfoEnabled())
				logger.info("Removed mac " + mac + " from currently upgrading tokens list. " + removedValue
						+ " was actually removed from list.");
		}
		if (logger.isInfoEnabled())
			logger.info("Token upgrade task execution returned: " + result);
	}

	private void scanForToken(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Starting sensor token scan");
	}

	private void stopTokenScan(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Stopping sensor token scan");

	}

	private void uploadLogs(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Starting log upload");
	}

	private void reportStolen(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Starting gateway wipe");
	}

	private void changeCellStatus(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Changin cell status");
	}

	private void updateOs(Task task) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
			logger.info("Starting OS update");
	}

	/**
	 * @param task
	 */
	private void doDownload(Task task) {
		if (logger.isInfoEnabled())
			logger.info("Starting synchronization");
		DownloadTask downloadTask = new DownloadTask(task);
		boolean result = downloadTask.execute();
		if (result == true) {
			GatewayManager.getInstance().updateSyncDate(new Date());
			checkGatewayConfig();
		}
		if (logger.isInfoEnabled())
			logger.info("Download task execution returned: " + result);
	}

	/**
	 * 
	 */
	private void checkGatewayConfig() {
		Gateway findMe = GatewayManager.getInstance().findMe();
		if (findMe == null || findMe.getGatewayConfigKey() == null)
			return;
		GatewayConfiguration config = GatewayConfigurationManager.getInstance().findByRemoteId(
				findMe.getGatewayConfigKey());
		if (config != null) {
			this.config = config;
			prepareScheduledTasks();
		}
	}

	/**
	 * 
	 */
	private void shutdownTasks() {
		if (logger.isInfoEnabled())
			logger.info("Shutting down");
	}

	/**
	 * @param sensorValues
	 */
	protected void storeSensorList(SensorList sensorValues) {
		if (logger.isInfoEnabled())
			logger.info("Received SensorList from AdvertisingPackageParser");
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		Iterator<SensorValue> iterator = sensorValues.iterator();
		while (iterator.hasNext()) {
			SensorValue sensorValue = (SensorValue) iterator.next();
			TokenInfoStructure tokenInfo = TokenInfoCache.getInstace().getTokenInfo(sensorValue.mac);

			if (tokenInfo != null) {
				Token token = tokenInfo.token;
				if (token != null) {
					if (logger.isInfoEnabled())
						logger.debug("Token: " + token.toString());
					if (needsTokenConfigUpgrade(token, sensorValue.mac)) {
						if (logger.isInfoEnabled())
							logger.info("Token added to upgrade list");
						addTokenToUpgradeList(sensorValue, token);
					}
				} else {
					if (logger.isInfoEnabled())
						logger.info("Token is null! Could not upgrade!");
				}
				try {
					SensorDataManager.getInstance().addNewRecord(em, sensorValue, GatewayManager.getMyRemoteId(),
							tokenInfo);
				} catch (NumberFormatException e) {
					logger.fatal(e);
				} catch (ParseException e) {
					logger.fatal(e);
				}
			} else {
				if (logger.isInfoEnabled())
					logger.debug("No token information found for token with mac " + sensorValue.mac);
			}
		}
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param token
	 * @param mac
	 * @return
	 */
	private synchronized boolean needsTokenConfigUpgrade(Token token, String mac) {
		boolean needsConfigUpdate = token.isNeedsConfigUpdate();
		boolean containsKey = upgradeTokens.containsKey(mac);
		if (logger.isInfoEnabled()) {
			logger.info("Token needs upgrade: " + needsConfigUpdate);
			logger.info("upgradeTokens contains key: " + containsKey);
		}
		return (needsConfigUpdate && !containsKey);
	}

	/**
	 * @param sensorValue
	 * @param token
	 */
	private synchronized void addTokenToUpgradeList(SensorValue sensorValue, Token token) {
		upgradeTokens.put(sensorValue.mac, sensorValue.mac);
		if (logger.isInfoEnabled())
			logger.info("Added mac " + sensorValue.mac + " to list of tokens that are already upgrading.");
		Task configUpgradeTask = new Task();
		configUpgradeTask.setType(TaskType.UPGRADE_TOKEN);
		configUpgradeTask.setRelatedId(token.getRemoteId());
		List<String> parameters = new ArrayList<String>();
		parameters.add(token.getMac());
		configUpgradeTask.setParameters(parameters);
		TaskManager.getInstance().persist(configUpgradeTask);
		TaskQueue.addTaskToQueue(configUpgradeTask);
	}

}
