/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.Date;

import javax.persistence.EntityManager;

import com.starnberger.tokenofflineengine.model.SensorData;
import com.starnberger.tokenofflineengine.model.SensorType;
import com.starnberger.tokenofflineengine.model.Token;

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

	private final EntityManager em;

	/**
	 * Private default constructor
	 */
	private SensorDataManager() {
		this.em = EMF.get().createEntityManager();
	}

	/**
	 * @param dataRecord
	 */
	public void persist(SensorData dataRecord) {
		em.getTransaction().begin();
		em.persist(dataRecord);
		em.getTransaction().commit();
	}

	/**
	 * @param dataRecord
	 * @return
	 */
	public SensorData merge(SensorData dataRecord) {
		em.getTransaction().begin();
		SensorData updatedRecord = em.merge(dataRecord);
		em.getTransaction().commit();
		return updatedRecord;
	}

	/**
	 * @param dataRecord
	 */
	public void remove(SensorData dataRecord) {
		em.getTransaction().begin();
		em.remove(dataRecord);
		em.getTransaction().commit();
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorData findById(Long id) {
		return em.find(SensorData.class, id);
	}

	/**
	 * Adds a new
	 * 
	 * @param mac
	 * @param sensorPosition
	 * @param timeStamp
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param isAlarm
	 * @return
	 */
	public SensorData addNewRecord(String mac, String sensorPosition, Date timeStamp, Double value1, Double value2,
			Double value3, boolean isAlarm) {
		Token token = TokenManager.getInstance().findByMac(mac);
		SensorType sensorType = null;
		if (token == null) {
			// TODO: Record error message
		} else {
			sensorType = TokenModelManager.getInstance().findSensorTypeByPosition(token.getModel(),
					sensorPosition);
		}
		SensorData newRecord = new SensorData();
		newRecord.setAlarm(isAlarm);
		newRecord.setGateway(GatewayManager.getMyId());
		if (sensorType != null)
			newRecord.setSensorType(sensorType.getId());
		newRecord.setTimestamp(timeStamp);
		if (token != null)
			newRecord.setToken(token.getId());
		newRecord.setValue1(value1);
		newRecord.setValue2(value2);
		newRecord.setValue3(value3);
		persist(newRecord);
		return newRecord;
	}
}
