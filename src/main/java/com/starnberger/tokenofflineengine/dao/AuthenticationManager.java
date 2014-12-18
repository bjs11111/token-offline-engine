/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.starnberger.tokenofflineengine.AuthHttpHeader;
import com.starnberger.tokenofflineengine.GatewayInfo;
import com.starnberger.tokenofflineengine.model.Gateway;

/**
 * @author Roman Kaufmann
 *
 */
public class AuthenticationManager {
	private static final String AUTH_ASSIGN_ME = GatewayInfo.SERVER_URL + "auth/assignMe";
	private static final String AUTH_REGISTER_ME = GatewayInfo.SERVER_URL + "auth/registerMe";
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
	 * 
	 * @return
	 */
	public boolean isAlreadyRegistered() {
		Gateway me = GatewayManager.getInstance().findMe();
		if (me == null) {
			// Create local gateway entry, when we are started the very first
			// time
			em.getTransaction().begin();
			me = new Gateway();
			me.setUuid(GatewayInfo.CPUID);
			em.persist(me);
			em.getTransaction().commit();
			reportForWork(me);
		}
		if (me.getGatewayToken() == null || "".equals(me.getGatewayToken()))
			return false;
		GatewayInfo.MY_TOKEN = me.getGatewayToken();
		return true;
	}

	/**
	 * @return
	 */
	public boolean doRegister() {
		Gateway me = GatewayManager.getInstance().findMe();
		if (me == null)
			return false;
		Response response = client.target(AUTH_REGISTER_ME).request().post(Entity.entity(me, MediaType.APPLICATION_JSON));
		String token = response.getHeaderString(AuthHttpHeader.AUTH_TOKEN);
		System.out.println(response.readEntity(String.class));
		System.out.println(token);
		// TODO: Handle Unauthorized status		
		/*if (result == null || "".equals(result))
			return false;*/
		return true;
	}

	/**
	 * Report the gateway to the token engine for the first time.
	 */
	public void reportForWork(Gateway me) {
		Response response = client.target(AUTH_ASSIGN_ME).request().post(Entity.entity(me, MediaType.APPLICATION_JSON));
		// TODO: Handle Unauthorized status
		// Close is only necessary, because we do not read the returned entity from the response
		response.close();
	}
}
