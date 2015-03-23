/**
 * 
 */
package com.starnberger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Roman Kaufmann
 * 
 */
public final class EMF {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("test-offline-engine-persistence-unit");

	private EMF() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}
