/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class.getName());

	private LinkedBlockingQueue<Task> tasks = new LinkedBlockingQueue<Task>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: Add CPU ID read logic here
		Main main = new Main();
		main.startUpChecks();
		main.workerLoop();
		main.shutdownTasks();
	}

	/**
	 * Default constructor.
	 */
	public Main() {
		logger.info("Starting Token Offline Engine Version: " + Version.INSTANCE.getVersion());
	}

	/**
	 * 
	 */
	private void startUpChecks() {
		logger.info("Performing startup checks");
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
			logger.info("Rebooting the system");
			System.exit(1);
		}
		logger.info("Controlled shutdown");
		System.exit(0);
	}

	/**
	 * Main worker loop for token offline engine.
	 */
	private void workerLoop() {
		logger.info("Starting main logic");
		while (true) {
			try {
				processTask(tasks.take());
			} catch (InterruptedException e) {
				logger.fatal(e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param task
	 */
	private void processTask(Task task) {
		if (task == null) {
			logger.warn("Null task was inserted into queue!");
			return;
		}
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
		default:
			break;
		}
	}

	private void uploadSensorData(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting sensor upload");
	}

	private void upgradeGateway(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting gateway upgrade");

	}

	private void uploadGatewayStatus(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting gateway status upload");
	}

	private void upgradeToken(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting token upgrade");
	}

	private void scanForToken(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting sensor token scan");

	}

	private void stopTokenScan(Task task) {
		// TODO Auto-generated method stub
		logger.info("Stopping sensor token scan");

	}

	private void uploadLogs(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting log upload");
	}

	private void reportStolen(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting gateway wipe");
	}

	private void changeCellStatus(Task task) {
		// TODO Auto-generated method stub
		logger.info("Changin cell status");
	}

	private void updateOs(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting OS update");
	}

	private void doDownload(Task task) {
		// TODO Auto-generated method stub
		logger.info("Starting synchronization");
	}

	private void shutdownTasks() {
		logger.info("Shutting down");
	}

}
