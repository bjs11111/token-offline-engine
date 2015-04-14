/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.starnberger.tokenofflineengine.common.EntityState;
import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.common.TaskType;
import com.starnberger.tokenofflineengine.dao.EMF;
import com.starnberger.tokenofflineengine.dao.GatewayManager;
import com.starnberger.tokenofflineengine.model.Gateway;
import com.starnberger.tokenofflineengine.model.SyncData;
import com.starnberger.tokenofflineengine.model.SyncEntity;
import com.starnberger.tokenofflineengine.model.Task;
import com.starnberger.tokenofflineengine.model.Token;

/**
 * @author Roman Kaufmann
 *
 */
public class DownloadTask extends AbstractTask implements ITask {
	private static final String SYNC_URL = GatewayInfo.getInstance().getServerUrl() + "auth/sync";
	private final List<Task> followUpTasks = new ArrayList<Task>();

	/**
	 * @param task
	 */
	public DownloadTask(Task task) {
		super(task);
	}

	/*
	 * (non-Javadoc)
	 * 
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
			EntityManager em = EMF.get().createEntityManager();
			SyncData entity = response.readEntity(SyncData.class);
			checkGateway(me, entity.getGateway());
			// Store all entities
			em.getTransaction().begin();
			updateEntities(em, entity.getUpdatedTasks());
			updateEntities(em, entity.getUpdatedSensorTypes());
			updateEntities(em, entity.getUpdatedTokenModels());
			updateEntities(em, entity.getUpdatedTokens());
			updateEntities(em, entity.getUpdatedTokenConfigurations());
			updateEntities(em, entity.getUpdatedSensorConfigurations());
			updateEntities(em, entity.getSensorConfigParameters());
			updateEntities(em, entity.getSensorConfigValues());
			updateEntities(em, entity.getUpdatedGatewayConfigurations());
			updateTask(em, Status.COMPLETED);
			em.flush();
			em.getTransaction().commit();
			em.close();
		}
		logout();
		return true;
	}

	/**
	 * @param me
	 * @param gateway
	 */
	private void checkGateway(Gateway me, Gateway gateway) {
		if (gateway != null && gateway.isNeedsConfigUpgrade()) {
			Task upgradeGatewayConfig = new Task();
			upgradeGatewayConfig.setType(TaskType.UPGRADE_GW_CONFIG);
			upgradeGatewayConfig.setRelatedId(gateway.getGatewayConfigKey());
			TaskQueue.getInstance().addTaskToQueue(upgradeGatewayConfig);
		}
		if (me.getRemoteId() == null && gateway.getId() != null) {
			me.setRemoteId(gateway.getId());
			GatewayManager.getInstance().update(me);
		}
	}

	/**
	 * @param em
	 * @param list
	 * @return
	 */
	private boolean updateEntities(EntityManager em, List<? extends SyncEntity> list) {
		if (list == null)
			return false;
		Iterator<? extends SyncEntity> iterator = list.iterator();
		while (iterator.hasNext()) {
			SyncEntity syncEntity = (SyncEntity) iterator.next();
			storeEntity(em, syncEntity);
			if (syncEntity instanceof Task) {
				Task task = (Task) syncEntity;
				if (!task.isDeleted())
					TaskQueue.getInstance().addTaskToQueue(task);
			} else if (syncEntity instanceof Token) {
				TokenInfoCache.getInstace().removeTokenFromCache((Token) syncEntity);
			}
		}
		return true;
	}

	/**
	 * @param em
	 * @param entity
	 * @return
	 */
	private boolean storeEntity(EntityManager em, SyncEntity entity) {
		if (entity == null)
			return false;
		if (entity.getState() == null)
			entity.setState(EntityState.CREATED);
		System.out.println("Entity type: " + entity.getClass().getName() + " id: " + entity.getId() + " state: "
				+ entity.getState());
		switch (entity.getState()) {
		case CREATED:
			storeNewEntity(em, entity);
			break;
		case UPDATED:
			SyncEntity foundEntity = findWebKey(em, entity);
			if (foundEntity == null) {
				storeNewEntity(em, entity);
			} else {
				foundEntity.copyValues(entity);
				em.merge(foundEntity);
			}
			break;
		case DELETED:
			SyncEntity deleteEntity = findWebKey(em, entity);
			if (deleteEntity != null)
				em.remove(entity);
			break;

		default:
			break;
		}
		return true;
	}

	/**
	 * @param em
	 * @param entity
	 */
	private void storeNewEntity(EntityManager em, SyncEntity entity) {
		entity.setRemoteId(entity.getId());
		em.persist(entity);
	}

	/**
	 * @param em
	 *            TODO
	 * @param search
	 * @return
	 */
	private SyncEntity findWebKey(EntityManager em, SyncEntity search) {
		TypedQuery<? extends SyncEntity> query = em.createQuery("select s from " + search.getClass().getSimpleName()
				+ " s where s.remoteId = :webKey", search.getClass());
		query.setParameter("webKey", search.getId());
		List<? extends SyncEntity> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenofflineengine.ITask#getFollowUpTasks()
	 */
	@Override
	public List<Task> getFollowUpTasks() {
		return followUpTasks;
	}

}
