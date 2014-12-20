/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.starnberger.tokenofflineengine.dao.AuthRequestFilter;
import com.starnberger.tokenofflineengine.dao.EMF;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.SyncData;
import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class DownloadTask {
	private static final String SYNC_URL = GatewayInfo.SERVER_URL + "auth/sync";
	private static final String LOGIN_URL = GatewayInfo.SERVER_URL + "auth/login";
	private static final String LOGOUT_URL = GatewayInfo.SERVER_URL + "auth/logout";

	private final EntityManager em;
	private final Client client;
	private final Task task;

	/**
	 * Constructor.
	 * 
	 * @param task
	 */
	public DownloadTask(Task task) {
		this.task = task;
		this.em = EMF.get().createEntityManager();
		ClientConfig config = new ClientConfig();
		config.register(AuthRequestFilter.class);
		config.register(JacksonFeature.class);
		this.client = ClientBuilder.newClient(config);
		this.client.register(new LoggingFilter(Logger.getGlobal(), true));
	}

	/**
	 * @param me
	 * @return
	 */
	private boolean login(Gateway me) {
		MultivaluedHashMap<String, String> formData = new MultivaluedHashMap<String, String>();
		formData.add("username", me.getUuid());
		formData.add("password", me.getPassword());
		Response response = client.target(LOGIN_URL).request().post(Entity.form(formData));
		if (response.getStatus() == 200) {
			GatewayInfo.MY_TOKEN = response.getHeaderString(AuthHttpHeader.AUTH_TOKEN);
			return true;
		}
		GatewayInfo.MY_TOKEN = null;
		return false;
	}

	/**
	 * @return
	 */
	private boolean logout() {
		Response response = client.target(LOGOUT_URL).request().get();
		GatewayInfo.MY_TOKEN = null;
		if (response.getStatus() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * Executes the task
	 * 
	 * @return true if the execution was successful
	 */
	public boolean execute() {
		Gateway me = GatewayManager.getInstance().findMe();
		login(me);
		if (me == null)
			return false;
		Date lastSyncDate = new Date(0);
		if (me.getLastSync() != null)
			lastSyncDate = me.getLastSync();
		Response response = client.target(SYNC_URL).path("/{date}")
				.resolveTemplate("date", Long.toString(lastSyncDate.getTime())).request(MediaType.APPLICATION_JSON)
				.get();
		System.out.println(response.toString());
		System.out.println(response.getStatusInfo());
		System.out.println(response.getHeaders());
		if (response.bufferEntity())
		{
			// TODO: GAE id of type Key causes problems during JSON serialization -> solve this issue
			SyncData entity = response.readEntity(SyncData.class);
			System.out.println(entity.toString());
		}
		logout();
		return true;
	}

	/**
	 * @return
	 */
	public Task getTask() {
		return task;
	}

}
