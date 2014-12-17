/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.starnberger.tokenofflineengine.GatewayInfo;
import com.starnberger.tokenofflineengine.model.Gateway;

/**
 * @author Roman Kaufmann
 *
 */
public class AuthenticationManager {
	private static final String AUTH_ASSIGN_ME = GatewayInfo.SERVER_URL + "/auth/assignMe";
	private static final AuthenticationManager _INSTANCE = new AuthenticationManager();
	
	/**
	 * @return
	 */
	public static final AuthenticationManager getInstance() {
		return _INSTANCE;
	}
	
	private final EntityManager em;
	private final Client client;
	
	/**
	 * Private default constructor
	 */
	private AuthenticationManager() {
		this.em = EMF.get().createEntityManager();
		ClientConfig config = new ClientConfig();
		config.register(AuthRequestFilter.class);
		this.client = ClientBuilder.newClient(config);
	}
	
	/**
	 * Returns true if the 
	 * @return
	 */
	public boolean isAlreadyRegistered() {
		Gateway me = GatewayManager.getInstance().findMe();
		if (me == null) {
			// Create local gateway entry, when we are started the very first time
			em.getTransaction().begin();
			me = new Gateway();
			me.setUuid(GatewayInfo.CPUID);
			em.persist(me);
			em.getTransaction().commit();
		}
		if (me.getGatewayToken() == null || "".equals(me.getGatewayToken()))
			return false;
		return true;
	}
	
	/**
	 * Report the gateway to the token engine for the first time.
	 */
	public void reportForWork() {
		Gateway me = GatewayManager.getInstance().findMe();
		if (me == null)
			return;
		client.target(AUTH_ASSIGN_ME).request().post(Entity.entity(me, MediaType.APPLICATION_JSON));
	}
}
