/**
 * 
 */
package com.starnberger.tokenofflineengine;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.starnberger.tokenofflineengine.dao.AuthRequestFilter;
import com.starnberger.tokenofflineengine.dao.EMF;
import com.starnberger.tokenofflineengine.model.SyncData;
import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class DownloadTask {
	private static final String SYNC_URL = GatewayInfo.SERVER_URL + "auth/sync";

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
		this.client = ClientBuilder.newClient(config);
	}

	/**
	 * Executes the task
	 * 
	 * @return true if the execution was successful
	 */
	public boolean execute() {
		SyncData syncData = client.target(SYNC_URL).path("/{id}").resolveTemplate("id", GatewayInfo.CPUID)
				.request(MediaType.APPLICATION_JSON).get(SyncData.class);
		System.out.println(syncData.toString());
		return true;
	}

	/**
	 * @return
	 */
	public Task getTask() {
		return task;
	}

}
