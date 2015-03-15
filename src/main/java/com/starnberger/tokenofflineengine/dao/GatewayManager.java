/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.Date;
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
	 * @param lastSyncDate
	 */
	public void updateSyncDate(Date lastSyncDate) {
		Gateway me = findMe();
		me.setLastSync(lastSyncDate);
		update(me);
	}
	
	/**
	 * @param lastUploadDate
	 */
	public void updateUploadDate(Date lastUploadDate) {
		Gateway me = findMe();
		me.setLastUpload(lastUploadDate);
		update(me);
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
	
	/**
	 * @param entity
	 * @return
	 */
	public Gateway update(Gateway entity) {
		em.getTransaction().begin();
		Gateway newEntity = em.merge(entity);
		em.flush();
		em.getTransaction().commit();
		return newEntity;
	}
}
