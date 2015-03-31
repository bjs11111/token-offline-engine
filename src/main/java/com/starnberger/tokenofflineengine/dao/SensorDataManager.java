/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.starnberger.tokenengine.connector.parser.SensorValue;
import com.starnberger.tokenofflineengine.TokenInfoStructure;
import com.starnberger.tokenofflineengine.common.SensorTypeUtility;
import com.starnberger.tokenofflineengine.model.SensorData;
import com.starnberger.tokenofflineengine.model.SensorDataListWrapper;

/**
 * @author Roman Kaufmann
 *
 */
public class SensorDataManager {
	private static final SensorDataManager _INSTANCE = new SensorDataManager();

	/**
	 * @return
	 */
	public static final SensorDataManager getInstance() {
		return _INSTANCE;
	}

	/**
	 * Private default constructor
	 */
	private SensorDataManager() {
	}

	/**
	 * @param em
	 *            TODO
	 * @param dataRecord
	 */
	public void persist(EntityManager em, SensorData dataRecord) {
		em.persist(dataRecord);
		em.flush();
	}

	/**
	 * @param dataRecord
	 * @return
	 */
	public SensorData merge(SensorData dataRecord) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		SensorData updatedRecord = em.merge(dataRecord);
		em.getTransaction().commit();
		em.close();
		return updatedRecord;
	}

	/**
	 * @param dataRecord
	 */
	public void remove(SensorData dataRecord) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		em.remove(dataRecord);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorData findById(Long id) {
		EntityManager em = EMF.get().createEntityManager();
		SensorData find = em.find(SensorData.class, id);
		em.close();
		return find;
	}

	/**
	 * @param em
	 *            TODO
	 * @param value
	 * @param gatewayId
	 * @param tokenInfo
	 * @return
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	public SensorData addNewRecord(EntityManager em, SensorValue value, Long gatewayId, TokenInfoStructure tokenInfo)
			throws NumberFormatException, ParseException {
		Double value1 = !value.value1.isEmpty() ? Double.valueOf(value.value1) : null;
		Double value2 = !value.value2.isEmpty() ? Double.valueOf(value.value2) : null;
		Double value3 = !value.value3.isEmpty() ? Double.valueOf(value.value3) : null;
		return addNewRecord(em, value.mac, SensorTypeUtility.getPositionForSensorType(value.sensor),
				new Date(Long.valueOf(value.timestamp)), value1, value2, value3, value.isAlarm, gatewayId, tokenInfo);
	}

	/**
	 * Adds a new
	 * 
	 * @param em
	 * @param mac
	 * @param sensorPosition
	 * @param timeStamp
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param isAlarm
	 * @param gatewayId
	 * @param tokenInfo
	 * 
	 * @return
	 */
	public SensorData addNewRecord(EntityManager em, String mac, String sensorPosition, Date timeStamp, Double value1,
			Double value2, Double value3, boolean isAlarm, Long gatewayId, TokenInfoStructure tokenInfo) {
		Long sensorTypeForPosition = null;
		Long remoteId = null;
		SensorData newRecord = null;
		boolean isNew = false;
		if (tokenInfo != null) {
			sensorTypeForPosition = tokenInfo.getSensorTypeForPosition(sensorPosition);
			if (tokenInfo.token != null) {
				remoteId = tokenInfo.token.getRemoteId();
			}
			newRecord = tokenInfo.getLatestSensorData(sensorPosition);
		}
		if (!"A".equals(sensorPosition) && sensorTypeForPosition == null)
			return null;
		if (newRecord == null) {
			newRecord = new SensorData();
			isNew = true;
		} else {
			newRecord = findById(newRecord.getId());
			if (newRecord == null) {
				newRecord = new SensorData();
				tokenInfo.removeSensorTypeForPosition(sensorPosition);
			}
		}
		newRecord.setAlarm(isAlarm);
		newRecord.setSensorType(sensorTypeForPosition);
		newRecord.setToken(remoteId);
		newRecord.setGateway(gatewayId);
		newRecord.setTimestamp(timeStamp);
		newRecord.setValue1(value1);
		newRecord.setValue2(value2);
		newRecord.setValue3(value3);
		if (isNew) {
			persist(em, newRecord);
			em.flush();
		} else {
			newRecord = merge(newRecord);
		}
		if (tokenInfo != null)
			tokenInfo.addLatestSensorDataForPosition(sensorPosition, newRecord);
		return newRecord;
	}

	/**
	 * @param lastSync
	 * @return
	 */
	public SensorDataListWrapper findSensorDataValuesSinceLastSync(Date lastSync) {
		if (lastSync == null)
			lastSync = new Date(0);
		EntityManager em = EMF.get().createEntityManager();
		SensorDataListWrapper wrapper = new SensorDataListWrapper();
		TypedQuery<SensorData> query = em.createNamedQuery("SensorData.findSinceLastSync", SensorData.class);
		query.setParameter("lastSync", lastSync, TemporalType.TIMESTAMP);
		List<SensorData> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0)
			wrapper.setList(resultList);
		em.close();
		return wrapper;
	}

	/**
	 * @param deleteUntil
	 * @return
	 */
	public int deleteUploadedSensorData(Date deleteUntil) {
		if (deleteUntil == null)
			return 0;
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		Query deleteQuery = em.createNamedQuery("SensorData.deleteUploadedData");
		deleteQuery.setParameter("deleteUntil", deleteUntil);
		int result = deleteQuery.executeUpdate();
		em.getTransaction().commit();
		em.close();
		return result;
	}
}
