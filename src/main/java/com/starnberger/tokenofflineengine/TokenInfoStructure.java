package com.starnberger.tokenofflineengine;

import java.util.HashMap;
import java.util.Map;

import com.starnberger.tokenengine.connector.parser.SensorValue.SensorType;
import com.starnberger.tokenofflineengine.model.SensorData;
import com.starnberger.tokenofflineengine.model.Token;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenInfoStructure {
	public Token token;
	public Map<SensorType, Long> sensorTypeToId = new HashMap<SensorType, Long>();
	public Map<String, SensorData> latestValuePerPosition = new HashMap<String, SensorData>();
	public Map<String, com.starnberger.tokenofflineengine.model.SensorType> sensorTypesByPosition = new HashMap<String, com.starnberger.tokenofflineengine.model.SensorType>();

	/**
	 * @param position
	 * @return
	 */
	public Long getSensorTypeForPosition(String position) {
		com.starnberger.tokenofflineengine.model.SensorType sensorType = sensorTypesByPosition.get(position);
		if (sensorType != null)
			return sensorType.getRemoteId();
		return null;
	}
	
	/**
	 * @param position
	 */
	public void removeSensorTypeForPosition(String position) {
		if (sensorTypesByPosition == null)
			return;
		sensorTypesByPosition.remove(position);
	}
	
	/**
	 * @param position
	 * @return
	 */
	public SensorData getLatestSensorData(String position) {
		if (position == null || position.isEmpty())
			return null;
		return latestValuePerPosition.get(position);
	}
	
	/**
	 * @param position
	 * @param value
	 */
	public void addLatestSensorDataForPosition(String position, SensorData value) {
		if (position == null || value == null || position.isEmpty())
			return;
		latestValuePerPosition.put(position, value);
	}
	
}
