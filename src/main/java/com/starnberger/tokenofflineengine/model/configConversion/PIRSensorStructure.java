/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.Map;

import net.sourceforge.juint.UInt8;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public class PIRSensorStructure extends AbstractSensorStructure {
	private UInt8 sensorPresenceTimeout;
	private SensorCommon common;
	private EventSensorCommon commonEvent;

	/**
	 * @param configValues
	 */
	public PIRSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		sensorPresenceTimeout = new UInt8(getIntValue("sensorPresenceTimeout"));
		common = new SensorCommon(configValues);
		commonEvent = new EventSensorCommon(configValues);
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = ArrayUtils.add(result, sensorPresenceTimeout.byteValue());
		result = ArrayUtils.addAll(result, common.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, commonEvent.toByteArray(isBigEndian));
		return result;
	}
}
