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
	private Int16 alarmLow;															//0
	private Int16 alarmHigh;														//2
	private Int8 alarmInside;														//4
	private UInt8 oversamplingMode;											//5
	private SensorCommon common;									//6, size 2
	private PeriodicSensorCommon commonPeriodic;	//8, size 3
	private EventSensorCommon commonEvent;				//11, size 6

	/**
	 * @param configValues
	 */
	public TemperatureSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		alarmLow = new Int16(getIntValue("sensorTemperatureAlarmLow"));
		alarmHigh = new Int16(getIntValue("sensorTemperatureAlarmHigh"));
		alarmInside = new Int8(getIntValue("sensorTemperatureAlarmInside"));
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
		result = ArrayUtils.addAll(result, commonEvent.toByteArray(isBigEndian));
		return result;
	}
}
