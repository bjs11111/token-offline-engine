/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Roman Kaufmann
 * 
 */
public final class EMF {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("token-offline-engine-persistence-unit");

	private EMF() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}
