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
public class ShockSensorStructure extends AbstractSensorStructure {
	private UInt8 alarmGX;
	private UInt8 alarmGY;
	private UInt8 alarmGZ;
	private UInt8 timeWindow;
	private SensorCommon common;
	private EventSensorCommon commonEvent;

	/**
	 * @param configValues
	 */
	public ShockSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		alarmGX = new UInt8(getIntValue("sensorMechanicalShockAlarmGX"));
		alarmGY = new UInt8(getIntValue("sensorMechanicalShockAlarmGY"));
		alarmGZ = new UInt8(getIntValue("sensorMechanicalShockAlarmGZ"));
		timeWindow = new UInt8(getIntValue("sensorPresenceTimeout"));
		common = new SensorCommon(configValues);
		commonEvent = new EventSensorCommon(configValues);
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = ArrayUtils.add(result, alarmGX.byteValue());
		result = ArrayUtils.add(result, alarmGY.byteValue());
		result = ArrayUtils.add(result, alarmGZ.byteValue());
		result = ArrayUtils.add(result, timeWindow.byteValue());
		result = ArrayUtils.addAll(result, common.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, commonEvent.toByteArray(isBigEndian));
		return result;
	}
}
