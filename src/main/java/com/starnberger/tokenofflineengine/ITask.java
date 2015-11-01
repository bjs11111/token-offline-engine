package com.starnberger.tokenofflineengine;

import java.util.List;

import com.starnberger.tokenofflineengine.model.Task;

public interface ITask {

	/**
	 * Executes the task
	 * 
	 * @return true if the execution was successful
	 */
	public abstract boolean execute();

	/**
	 * @return the followUpTasks
	 */
	public abstract List<Task> getFollowUpTasks();

	/**
	 * @return
	 */
	public abstract Task getTask();

}