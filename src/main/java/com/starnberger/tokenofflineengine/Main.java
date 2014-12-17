/**
 * 
 */
package com.starnberger.tokenofflineengine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Roman Kaufmann
 *
 */
public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void Main() {
		logger.info("Starting Token Offline Engine Version: " + Version.INSTANCE.getVersion());
	}
	
	public void run() {
		startUpChecks();
		workerLoop();
		shutdownTasks();
	}
	
	public void startUpChecks() {
		
	}
	
	public void workerLoop() {
		// TODO: Implement blocked queue mechanism here
	}
	
	public void shutdownTasks() {
		
	}

}
