/**
 * 
 */
package com.starnberger.tokenofflineengine;

import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class DownloadTask {
	private final Task task;

	/**
	 * Constructor.
	 * 
	 * @param task
	 */
	public DownloadTask(Task task) {
		this.task = task;
	}
	
	/**
	 * Executes the task
	 * @return true if the execution was successful
	 */
	public boolean execute() {
		return true;
	}

	/**
	 * @return
	 */
	public Task getTask() {
		return task;
	}

}
