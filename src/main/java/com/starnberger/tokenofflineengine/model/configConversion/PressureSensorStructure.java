/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.Map;

import net.sourceforge.juint.Int8;
import net.sourceforge.juint.UInt32;
import net.sourceforge.juint.UInt8;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public class PressureSensorStructure extends AbstractSensorStructure {
	private UInt32 alarmLow;															//0
	private UInt32 alarmHigh;														//2
	private Int8 alarmInside;														//4
	private UInt8 oversamplingMode;											//5
	private SensorCommon common;									//6, size 2
	private PeriodicSensorCommon commonPeriodic;	//8, size 3
	private EventSensorCommon commonEvent;				//11, size 6

	/**
	 * @param configValues
	 */
	public PressureSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		alarmLow = new UInt32(getIntValue("sensorPressureAlarmLow") * 100);
		alarmHigh = new UInt32(getIntValue("sensorPressureAlarmHigh") * 100);
		alarmInside = new Int8(getBooleanValue("sensorPressureAlarmInside"));
		oversamplingMode = new UInt8(getIntValue("sensorPressureOversamplingMode"));
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
		result = ArrayUtils.add(result, (byte) 255);
		result = ArrayUtils.add(result, (byte) 255);
		return result;
	}
}
