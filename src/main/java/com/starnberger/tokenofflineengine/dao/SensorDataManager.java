/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.starnberger.tokenengine.connector.parser.SensorValue;
import com.starnberger.tokenofflineengine.model.SensorData;
import com.starnberger.tokenofflineengine.model.SensorDataListWrapper;
import com.starnberger.tokenofflineengine.model.SensorType;
import com.starnberger.tokenofflineengine.model.Token;

/**
 * @author Roman Kaufmann
 *
 */
public class SensorDataManager {
	private static final SensorDataManager _INSTANCE = new SensorDataManager();
	private static final SimpleDateFormat formater = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

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
	 * @param value
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public SensorData addNewRecord(SensorValue value) throws NumberFormatException, ParseException {
		return addNewRecord(value.mac, getPositionForSensorType(value.sensor), formater.parse(value.timestamp), Double.valueOf(value.value1),
				Double.valueOf(value.value2), Double.valueOf(value.value3), value.isAlarm);
	}

	/**
	 * Returns the fixed sensor type position to determine the correct sensor
	 * configuration or to correctly interpret a sensor data value
	 * 
	 * @param type
	 * @return
	 */
	private String getPositionForSensorType(com.starnberger.tokenengine.connector.parser.SensorValue.SensorType type) {
		switch (type) {
		case TEMP1:
			return "1";
		case TEMP2:
			return "2";
		case TEMP3:
			return "3";
		case HUM:
			return "4";
		case BAR:
			return "5";
		case ORIENT:
			return "6";
		case PIR:
			return "7";
		case MOTION:
			return "8";
		case SHOCK:
			return "9";
		case BATT:
			return "A";
		default:
			return null;
		}
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
			sensorType = TokenModelManager.getInstance().findSensorTypeByPosition(token.getModel(), sensorPosition);
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
	
	/**
	 * @param lastSync
	 * @return
	 */
	public SensorDataListWrapper findSensorDataValuesSinceLastSync(Date lastSync) {
		SensorDataListWrapper wrapper = new SensorDataListWrapper();
		TypedQuery<SensorData> query = em.createNamedQuery("SensorData.findSinceLastSync", SensorData.class);
		query.setParameter("lastSync", lastSync);
		List<SensorData> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0)
			wrapper.setList(resultList);
		return wrapper;
	}
}
