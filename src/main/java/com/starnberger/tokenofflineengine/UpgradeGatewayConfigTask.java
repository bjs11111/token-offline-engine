/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.dao.GatewayConfigurationManager;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.dao.TaskManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class UpgradeGatewayConfigTask extends AbstractTask {
	private static final String UPLOAD_URL = GatewayInfo.getInstance().getServerUrl() + "gateways/configUpgraded";
	private static final Logger logger = Logger.getLogger(UpgradeGatewayConfigTask.class.getName());

	private final Main owner;

	/**
	 * @param task
	 * @param owner
	 */
	public UpgradeGatewayConfigTask(Task task, Main owner) {
		super(task);
		this.owner = owner;
	}
	
	@Override
	public boolean execute() {
		if (logger.isInfoEnabled())
			logger.info("Starting gateway config upgrade");

		// Upgrade task status
		task.setStatus(Status.IN_PROGRESS);
		TaskManager.getInstance().update(task);
		
		// Upgrade config
		owner.stopScheduledTasks();
		owner.startScheduledTasks(GatewayConfigurationManager.getInstance().findByRemoteId(task.getRelatedId()));
		
		// Mark task as completed
		task.setStatus(Status.COMPLETED);
		task.setCompleted(new Date());
		TaskManager.getInstance().update(task);
		
		reportGatewayAsUpgraded(GatewayManager.getInstance().findMe());
		if (logger.isInfoEnabled())
			logger.info("Finished gateway config upgrade");
		return true;
	}

	/**
	 * Informs the token engine that the configuration of this gateway was
	 * upgraded successfully.
	 * 
	 * @param me
	 */
	public void reportGatewayAsUpgraded(Gateway me) {
		if (me == null)
			return;
		login(me);
		Response response = client.target(UPLOAD_URL).path("/{id}").resolveTemplate("id", me.getRemoteId())
				.request().put(Entity.text(""));
		if (logger.isInfoEnabled())
			logger.info("Reported gateway to server as upgraded!. Returned status code: " + response.getStatus());
		response.close();
		logout();
	}

	@Override
	public List<Task> getFollowUpTasks() {
		return null;
	}

}
