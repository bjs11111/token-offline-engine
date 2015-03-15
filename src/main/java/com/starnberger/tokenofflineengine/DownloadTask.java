/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.starnberger.tokenofflineengine.common.EntityState;
import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.SyncData;
import com.starnberger.tokenofflineengine.model.SyncEntity;
import com.starnberger.tokenofflineengine.model.Task;

/**
 * @author Roman Kaufmann
 *
 */
public class DownloadTask extends AbstractTask implements ITask {
	private static final String SYNC_URL = GatewayInfo.SERVER_URL + "auth/sync";
	private final List<Task> followUpTasks = new ArrayList<Task>();

	/**
	 * Constructor.
	 * 
	 * @param task
	 */
	public DownloadTask(Task task) {
		super(task);
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenofflineengine.ITask#execute()
	 */
	@Override
	public boolean execute() {
		Gateway me = GatewayManager.getInstance().findMe();
		login(me);
		if (me == null)
			return false;
		Date lastSyncDate = new Date(0);
		if (me.getLastSync() != null)
			lastSyncDate = me.getLastSync();
		updateTask(Status.IN_PROGRESS);
		Response response = client.target(SYNC_URL).path("/{date}")
				.resolveTemplate("date", Long.toString(lastSyncDate.getTime())).request(MediaType.APPLICATION_JSON)
				.get();
		if (response.getStatus() >= 400) {
			updateTask(Status.FAILED);			
			return false;
		}
		if (response.bufferEntity()) {
			SyncData entity = response.readEntity(SyncData.class);
			System.out.println(entity.toString());
			// Store all entities
			em.getTransaction().begin();
			updateEntities(entity.getUpdatedTasks());
			updateEntities(entity.getUpdatedSensorTypes());
			updateEntities(entity.getUpdatedTokenModels());
			updateEntities(entity.getUpdatedTokens());
			updateEntities(entity.getUpdatedTokenConfigurations());
			updateEntities(entity.getUpdatedSensorConfigurations());
			updateEntities(entity.getSensorConfigParameters());
			updateEntities(entity.getSensorConfigValues());
			updateEntities(entity.getUpdatedGatewayConfigurations());
			em.flush();
			em.getTransaction().commit();
			updateTask(Status.COMPLETED);
		}
		logout();
		return true;
	}

	/**
	 * @param list
	 * @return
	 */
	private boolean updateEntities(List<? extends SyncEntity> list) {
		if (list == null)
			return false;
		Iterator<? extends SyncEntity> iterator = list.iterator();
		while (iterator.hasNext()) {
			SyncEntity syncEntity = (SyncEntity) iterator.next();
			storeEntity(syncEntity);
		}
		return true;
	}

	/**
	 * @param entity
	 * @return
	 */
	private boolean storeEntity(SyncEntity entity) {
		if (entity == null)
			return false;
		if (entity.getState() == null)
			entity.setState(EntityState.CREATED);
		System.out.println("Entity type: " + entity.getClass().getName());
		switch (entity.getState()) {
		case CREATED:
			em.persist(entity);
			break;
		case UPDATED:
			SyncEntity foundEntity = findWebKey(entity);
			if (foundEntity == null)
				em.persist(entity);
			else
				foundEntity.copyValues(entity);
			break;
		case DELETED:
			em.remove(entity);
			break;

		default:
			break;
		}
		return true;
	}

	/**
	 * @param search
	 * @return
	 */
	private SyncEntity findWebKey(SyncEntity search) {
		TypedQuery<? extends SyncEntity> query = em.createNamedQuery(search.getClass().getSimpleName()
				+ ".findMyWebKey", search.getClass());
		query.setParameter("webKey", search.getId());
		List<? extends SyncEntity> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenofflineengine.ITask#getFollowUpTasks()
	 */
	@Override
	public List<Task> getFollowUpTasks() {
		return followUpTasks;
	}

}
