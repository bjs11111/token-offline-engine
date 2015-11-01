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
	
	/**
	 * Private default constructor
	 */
	private TaskManager() {
	}
	
	/**
	 * @param task
	 * @return
	 */
	public Task persist(Task task) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		em.persist(task);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return task;
	}
	
	/**
	 * @param task
	 * @return
	 */
	public Task update(Task task) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		Task updated = em.merge(task);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return updated;
	}
	
	
	/**
	 * @param em
	 * @param task
	 * @return
	 */
	public Task update(EntityManager em, Task task) {
		return em.merge(task);
	}
}
