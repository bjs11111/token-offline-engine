/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.Map;

import net.sourceforge.juint.UInt16;
import net.sourceforge.juint.UInt8;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public class ShockSensorStructure extends AbstractSensorStructure {
	private UInt16 alarmGX;
	private UInt16 alarmGY;
	private UInt16 alarmGZ;
	private UInt8 timeWindow;
	private SensorCommon common;
	private EventSensorCommon commonEvent;

	/**
	 * @param configValues
	 */
	public ShockSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		alarmGX = new UInt16(getIntValue("sensorMechanicalShockAlarmGX"));
		alarmGY = new UInt16(getIntValue("sensorMechanicalShockAlarmGY"));
		alarmGZ = new UInt16(getIntValue("sensorMechanicalShockAlarmGZ"));
		timeWindow = new UInt8(getIntValue("sensorPresenceTimeout"));
		common = new SensorCommon(configValues);
		commonEvent = new EventSensorCommon(configValues);
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = addAll(result, alarmGX, isBigEndian);
		result = addAll(result, alarmGY, isBigEndian);
		result = addAll(result, alarmGZ, isBigEndian);
		result = ArrayUtils.add(result, timeWindow.byteValue());
		result = ArrayUtils.addAll(result, common.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, commonEvent.toByteArray(isBigEndian));
		return result;
	}
}
