package com.starnberger.tokenofflineengine;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.dao.AuthRequestFilter;
import com.starnberger.tokenofflineengine.dao.TaskManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public abstract class AbstractTask implements ITask {

	private static final String LOGIN_URL = GatewayInfo.getInstance().getServerUrl() + "auth/login";
	private static final String LOGOUT_URL = GatewayInfo.getInstance().getServerUrl() + "auth/logout";
	protected final Client client;
	protected final Task task;

	/**
	 * Default constructor.
	 * 
	 * @param task
	 */
	public AbstractTask(Task task) {
		this.task = task;
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
	protected boolean login(Gateway me) {
		MultivaluedHashMap<String, String> formData = new MultivaluedHashMap<String, String>();
		formData.add("username", me.getUuid());
		formData.add("password", me.getPassword());
		Response response = client.target(LOGIN_URL).request().post(Entity.form(formData));
		if (response.getStatus() == 200) {
			GatewayInfo.getInstance().MY_TOKEN = response.getHeaderString(AuthHttpHeader.AUTH_TOKEN);
			return true;
		}
		GatewayInfo.getInstance().MY_TOKEN = null;
		return false;
	}

	/**
	 * @return
	 */
	protected boolean logout() {
		Response response = client.target(LOGOUT_URL).request().get();
		GatewayInfo.getInstance().MY_TOKEN = null;
		if (response.getStatus() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * @param newStatus
	 */
	protected void updateTask(Status newStatus) {
		if (newStatus == Status.COMPLETED)
			task.setCompleted(new Date());
		task.setStatus(newStatus);
		TaskManager.getInstance().update(task);
	}
	
	
	/**
	 * @param em
	 * @param newStatus
	 */
	protected void updateTask(EntityManager em, Status newStatus) {
		if (newStatus == Status.COMPLETED)
			task.setCompleted(new Date());
		task.setStatus(newStatus);
		TaskManager.getInstance().update(em, task);
	}

	@Override
	public Task getTask() {
		return task;
	}

}