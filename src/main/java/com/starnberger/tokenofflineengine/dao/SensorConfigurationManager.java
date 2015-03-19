/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.starnberger.tokenofflineengine.model.SensorConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class SensorConfigurationManager {
	private static final SensorConfigurationManager _INSTANCE = new SensorConfigurationManager();

	/**
	 * @return
	 */
	public static final SensorConfigurationManager getInstance() {
		return _INSTANCE;
	}

	/**
	 * Private default constructor
	 */
	private SensorConfigurationManager() {
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorConfiguration findById(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		SensorConfiguration tokenConfiguration = em.find(SensorConfiguration.class, id);
		em.close();
		return tokenConfiguration;
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorConfiguration findByRemoteId(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<SensorConfiguration> query = em.createNamedQuery("SensorConfiguration.findMyWebKey",
				SensorConfiguration.class);
		query.setParameter("webKey", id);
		List<SensorConfiguration> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}
}
