package com.starnberger.tokenofflineengine;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public final class TaskQueue {
	private static final Logger logger = Logger.getLogger(TaskQueue.class.getName());
	private static final LinkedBlockingQueue<Task> tasks = new LinkedBlockingQueue<Task>();
	
	/**
	 * @return
	 * @throws InterruptedException 
	 */
	public static final Task take() throws InterruptedException {
		return tasks.take();
	}
	
	/**
	 * @param task
	 */
	public static final void addTaskToQueue(Task task) {
		if (logger.isInfoEnabled())
			logger.info("Adding " + task.toString() + " to processing queue");
		tasks.add(task);
	}

}
