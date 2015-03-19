/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public class SensorConfigValueManager {
	private static final SensorConfigValueManager _INSTANCE = new SensorConfigValueManager();

	/**
	 * @return
	 */
	public static final SensorConfigValueManager getInstance() {
		return _INSTANCE;
	}

	/**
	 * Private default constructor
	 */
	private SensorConfigValueManager() {
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorConfigValue findById(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		SensorConfigValue value = em.find(SensorConfigValue.class, id);
		em.close();
		return value;
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorConfigValue findByRemoteId(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<SensorConfigValue> query = em.createNamedQuery("SensorConfigValue.findMyWebKey",
				SensorConfigValue.class);
		query.setParameter("webKey", id);
		List<SensorConfigValue> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}
}
