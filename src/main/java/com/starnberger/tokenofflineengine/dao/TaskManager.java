/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import javax.persistence.EntityManager;

import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class TaskManager {
	private static final TaskManager _INSTANCE = new TaskManager();
	
	/**
	 * @return
	 */
	public static final TaskManager getInstance() {
		return _INSTANCE;
	}
	
	private final EntityManager em;
	
	/**
	 * Private default constructor
	 */
	private TaskManager() {
		this.em = EMF.get().createEntityManager();
	}
	
	/**
	 * @param task
	 * @return
	 */
	public Task update(Task task) {
		em.getTransaction().begin();
		Task updated = em.merge(task);
		em.flush();
		em.getTransaction().commit();
		return updated;
	}
}
