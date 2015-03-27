package com.starnberger.tokenofflineengine;

import java.util.HashMap;
import java.util.Map;

import com.starnberger.tokenengine.connector.parser.SensorValue.SensorType;
import com.starnberger.tokenofflineengine.model.Token;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenInfoStructure {
	public Token token;
	public Map<SensorType, Long> sensorTypeToId = new HashMap<SensorType, Long>();
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
}
