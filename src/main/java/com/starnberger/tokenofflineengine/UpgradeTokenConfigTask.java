/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.starnberger.tokenengine.connector.bluez.BluezConnector;
import com.starnberger.tokenengine.connector.controller.WriteConfigurationController;
import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.dao.TokenConfigurationManager;
import com.starnberger.tokenofflineengine.dao.TokenManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.Task;
import com.starnberger.tokenofflineengine.model.Token;
import com.starnberger.tokenofflineengine.model.TokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class UpgradeTokenConfigTask extends AbstractTask {
	private static final String CONFIG_UPGRADE_FLAG = "2b40";
	private static final String UPLOAD_URL = GatewayInfo.getInstance().getServerUrl() + "tokens/configUpgraded";
	private static final Logger logger = LogManager.getLogger(UpgradeTokenConfigTask.class.getName());

	private final BluezConnector connector;
	private final Main owner;

	/**
	 * @param task
	 * @param connector
	 * @param owner
	 */
	public UpgradeTokenConfigTask(Task task, BluezConnector connector, Main owner) {
		super(task);
		this.connector = connector;
		this.owner = owner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenofflineengine.ITask#execute()
	 */
	@Override
	public boolean execute() {
		if (logger.isInfoEnabled())
			logger.info("Upgrading token id " + task.getRelatedId() + " mac " + task.getParameters().get(0));
		Long relatedId = task.getRelatedId();
		final Token token = TokenManager.getInstance().findByRemoteId(relatedId);
		if (token == null) {
			logger.error("Token " + relatedId + " not found.");
			updateTask(Status.FAILED);
			return false;
		}
		if (token.getConfigId() == null) {
			logger.fatal("TokenConfiguration " + token.getConfigId() + " not found for Token " + token);
			updateTask(Status.FAILED);
			return false;
		}
		if (logger.isInfoEnabled())
			logger.info("Reading token configuration " + token.getConfigId());
		TokenConfiguration configuration = TokenConfigurationManager.getInstance().findByRemoteId(token.getConfigId());
		if (configuration == null) {
			logger.fatal("TokenConfiguration " + token.getConfigId() + " not found for Token " + token);
			updateTask(Status.FAILED);
			return false;
		}
		updateTask(Status.IN_PROGRESS);
		if (logger.isInfoEnabled())
			logger.info("Converting token configuration to byte array ...");
		byte[] configurationArray = TokenConfigurationManager.getInstance().generateByteArrayFromConfig(configuration,
				token);
		if (logger.isInfoEnabled())
			logger.info("Token configuration converted: " + Hex.encodeHexString(configurationArray) + " Length: "
					+ configurationArray.length);
		if (logger.isInfoEnabled())
			logger.info("Starting BlueZ write service ...");
		connector.writeService(token.getMac(), CONFIG_UPGRADE_FLAG,
				new WriteConfigurationController(configurationArray) {

					@Override
					public void onSuccessPartMessage(String address, byte[] writeData, int bytesWritten) {
						if (logger.isInfoEnabled())
							logger.info("written to address " + address + " data: " + writeData + ". " + bytesWritten
									+ " bytes written");
					}

					@Override
					public void onSuccess(String address, int bytesWritten) {
						if (logger.isInfoEnabled())
							logger.info("written to " + " written: " + bytesWritten + " bytes");
						try {
							TokenManager.getInstance().markTokenConfigUpgradeDone(token);
							reportTokenAsUpgraded(token);
							updateTask(Status.COMPLETED);
							owner.upgradeTokenDone(true, task);
						} catch (RuntimeException e) {
							logger.fatal(e);
						} catch (Exception e) {
							logger.fatal(e);
						}
					}

					@Override
					public void onFailed(String address, String msg, Exception e) {
						logger.error("failed to write at address " + address + " Message: " + msg, e);
						owner.upgradeTokenDone(false, task);
					}

				});
		return true;
	}

	/**
	 * Informs the token engine that the configuration of this token was
	 * upgraded successfully.
	 * 
	 * @param token
	 */
	public void reportTokenAsUpgraded(Token token) {
		Gateway me = GatewayManager.getInstance().findMe();
		if (me == null)
			return;
		login(me);
		Response response = client.target(UPLOAD_URL).path("/{id}").resolveTemplate("id", token.getRemoteId())
				.request().put(Entity.text(""));
		if (logger.isInfoEnabled())
			logger.info("Reported token to server as upgraded!. Returned status code: " + response.getStatus());
		response.close();
		logout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenofflineengine.ITask#getFollowUpTasks()
	 */
	@Override
	public List<Task> getFollowUpTasks() {
		return null;
	}

}
