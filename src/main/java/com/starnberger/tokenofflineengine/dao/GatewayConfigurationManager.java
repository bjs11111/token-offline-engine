/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import javax.persistence.EntityManager;

import com.starnberger.tokenofflineengine.model.GatewayConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class GatewayConfigurationManager {
	private static final GatewayConfigurationManager _INSTANCE = new GatewayConfigurationManager();

	/**
	 * @return
	 */
	public static final GatewayConfigurationManager getInstance() {
		return _INSTANCE;
	}

	private final EntityManager em;

	/**
	 * Private default constructor
	 */
	private GatewayConfigurationManager() {
		this.em = EMF.get().createEntityManager();
	}

	/**
	 * @param id
	 * @return
	 */
	public GatewayConfiguration findById(Long id) {
		return this.em.find(GatewayConfiguration.class, id);
	}

}
