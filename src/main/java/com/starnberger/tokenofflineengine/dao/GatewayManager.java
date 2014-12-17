/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.starnberger.tokenofflineengine.model.Gateway;

/**
 * @author Roman Kaufmann
 *
 */
public class GatewayManager {
	private static final GatewayManager _INSTANCE = new GatewayManager();
	
	/**
	 * @return
	 */
	public static final GatewayManager getInstance() {
		return _INSTANCE;
	}
	
	private final EntityManager em;
	
	/**
	 * Private default constructor
	 */
	private GatewayManager() {
		this.em = EMF.get().createEntityManager();
	}
	
	/**
	 * @return
	 */
	public Gateway findMe() {
		TypedQuery<Gateway> query = em.createNamedQuery("Gateway.findMe", Gateway.class);
		List<Gateway> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0)
			return resultList.get(0);
		return null;
	}
}
