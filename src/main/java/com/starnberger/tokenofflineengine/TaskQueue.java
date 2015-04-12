package com.starnberger.tokenofflineengine;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class TaskQueue {
	private static final Logger logger = LogManager.getLogger(TaskQueue.class.getName());
	private static final TaskQueue instance = new TaskQueue();
	
	private LinkedBlockingQueue<Task> tasks = new LinkedBlockingQueue<Task>();
	
	/**
	 * Private default constructor.
	 */
	private TaskQueue() {
		super();
	}
	
	/**
	 * @return
	 */
	public static final TaskQueue getInstance() {
		return instance;
	}
	
	/**
	 * @return
	 * @throws InterruptedException 
	 */
	public synchronized Task take() throws InterruptedException {
		return tasks.take();
	}
	
	/**
	 * @param task
	 */
	public synchronized void addTaskToQueue(Task task) {
		if (logger.isInfoEnabled())
			logger.info("Adding " + task.toString() + " to processing queue");
		tasks.add(task);
	}

}
