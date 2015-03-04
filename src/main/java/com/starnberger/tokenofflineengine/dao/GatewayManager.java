/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.starnberger.tokenofflineengine.model.Gateway;

/**
 * @author Roman Kaufmann
 *
 */
public class GatewayManager {
	private static final GatewayManager _INSTANCE = new GatewayManager();
	private static Long gatewayId = null;
	
	/**
	 * @return
	 */
	public static final GatewayManager getInstance() {
		return _INSTANCE;
	}
	
	private final EntityManager em;
	
	/**
	 * Returns the ID of the current gateway.
	 * @return
	 */
	public static Long getMyId() {
		if (gatewayId == null) {
			Gateway me = _INSTANCE.findMe();
			if (me != null)
				gatewayId = me.getId();
		}
		return gatewayId;
	}
	
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
