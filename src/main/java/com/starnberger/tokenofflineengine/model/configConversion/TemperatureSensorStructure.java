/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.Map;

import net.sourceforge.juint.Int16;
import net.sourceforge.juint.Int8;
import net.sourceforge.juint.UInt8;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public class TemperatureSensorStructure extends AbstractSensorStructure {
	private Int16 alarmLow;													
	private Int16 alarmHigh;												
	private Int8 alarmInside;												
	private UInt8 oversamplingMode;											
	private SensorCommon common;								
	private PeriodicSensorCommon commonPeriodic;	
	private EventSensorCommon commonEvent;	

	/**
	 * @param configValues
	 */
	public TemperatureSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		alarmLow = new Int16(getIntValue("sensorTemperatureAlarmLow") * 100);
		alarmHigh = new Int16(getIntValue("sensorTemperatureAlarmHigh") * 100);
		alarmInside = new Int8(getBooleanValue("sensorTemperatureAlarmInside"));
		oversamplingMode = new UInt8(getIntValue("sensorTemperatureOversamplingMode"));
		common = new SensorCommon(configValues);
		commonPeriodic = new PeriodicSensorCommon(configValues);
		commonEvent = new EventSensorCommon(configValues);
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = addAll(result, alarmLow, isBigEndian);
		result = addAll(result, alarmHigh, isBigEndian);
		result = ArrayUtils.add(result, alarmInside.byteValue());
		result = ArrayUtils.add(result, oversamplingMode.byteValue());
		result = ArrayUtils.addAll(result, common.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, commonPeriodic.toByteArray(isBigEndian));
		result = ArrayUtils.add(result, (byte) 255);
		result = ArrayUtils.addAll(result, commonEvent.toByteArray(isBigEndian));
		return result;
	}
}
