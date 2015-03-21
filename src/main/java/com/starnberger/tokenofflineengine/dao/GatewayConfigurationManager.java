/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	/**
	 * Private default constructor
	 */
	private GatewayConfigurationManager() {
	}

	/**
	 * @param id
	 * @return
	 */
	public GatewayConfiguration findByRemoteId(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<GatewayConfiguration> query = em.createNamedQuery("GatewayConfiguration.findMyWebKey",
				GatewayConfiguration.class);
		query.setParameter("webKey", id);
		List<GatewayConfiguration> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

}
