/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.dao.SensorDataManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.SensorDataListWrapper;
import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class UploadSensorDataTask extends AbstractTask {
	private static final String UPLOAD_URL = GatewayInfo.getInstance().getServerUrl() + "sensordata/upload";

	/**
	 * Default constructor.
	 * 
	 * @param task
	 */
	public UploadSensorDataTask(Task task) {
		super(task);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenofflineengine.ITask#execute()
	 */
	@Override
	public boolean execute() {
		Date deleteTimeStamp = new Date();
		Gateway me = GatewayManager.getInstance().findMe();
		if (me == null)
			return false;
		updateTask(Status.IN_PROGRESS);
		Date lastSync = me.getLastSync();
		SensorDataListWrapper wrapper = SensorDataManager.getInstance().findSensorDataValuesSinceLastSync(lastSync);
		boolean result = login(me);
		if (!result) {
			updateTask(Status.FAILED);			
			return false;
		}
		Response response = client.target(UPLOAD_URL).request(MediaType.APPLICATION_JSON).post(Entity.json(wrapper));
		if (response.getStatus() >= 400) {
			updateTask(Status.FAILED);			
			return false;
		}
		logout();
		updateTask(Status.COMPLETED);
		SensorDataManager.getInstance().deleteUploadedSensorData(deleteTimeStamp);
		return true;
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
