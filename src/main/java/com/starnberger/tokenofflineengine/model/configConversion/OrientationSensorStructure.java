/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.Map;

import net.sourceforge.juint.Int16;
import net.sourceforge.juint.UInt8;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public class OrientationSensorStructure extends AbstractSensorStructure {
	private Int16 alarmLowX;
	private Int16 alarmHighX;
	private Int16 alarmLowY;
	private Int16 alarmHighY;
	private Int16 alarmLowZ;
	private Int16 alarmHighZ;
	private UInt8 alarmInside;
	private UInt8 samplingFrequency;
	private UInt8 cutOffHpLpFrequency;
	private UInt8 oversamplingMode;
	private UInt8 dynamicRange;
	private SensorCommon common;
	private PeriodicSensorCommon commonPeriodic;
	private EventSensorCommon commonEvent;

	/**
	 * @param configValues
	 */
	public OrientationSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		alarmLowX = new Int16(getIntValue("sensorOrientationAlarmLowX"));
		alarmHighX = new Int16(getIntValue("sensorOrientationAlarmHighX"));
		alarmLowY = new Int16(getIntValue("sensorOrientationAlarmLowY"));
		alarmHighY = new Int16(getIntValue("sensorOrientationAlarmHighY"));
		alarmLowZ = new Int16(getIntValue("sensorOrientationAlarmLowZ"));
		alarmHighZ = new Int16(getIntValue("sensorOrientationAlarmHighZ"));
		alarmInside = new UInt8(getIntValue("sensorOrientationAlarmInside"));
		samplingFrequency = new UInt8(getIntValue("sensorOrientationSemplingFrequency"));
		cutOffHpLpFrequency = new UInt8(getIntValue("sensorOrientationCutOffHpLpFrequency"));
		oversamplingMode = new UInt8(getIntValue("sensorOrientationOversamplingMode"));
		dynamicRange = new UInt8(getIntValue("sensorOrientationDynamicRange"));
		common = new SensorCommon(configValues);
		commonPeriodic = new PeriodicSensorCommon(configValues);
		commonEvent = new EventSensorCommon(configValues);
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = addAll(result, alarmLowX, isBigEndian);
		result = addAll(result, alarmHighX, isBigEndian);
		result = addAll(result, alarmLowY, isBigEndian);
		result = addAll(result, alarmHighY, isBigEndian);
		result = addAll(result, alarmLowZ, isBigEndian);
		result = addAll(result, alarmHighZ, isBigEndian);
		result = ArrayUtils.add(result, alarmInside.byteValue());
		result = ArrayUtils.add(result, samplingFrequency.byteValue());
		result = ArrayUtils.add(result, cutOffHpLpFrequency.byteValue());
		result = ArrayUtils.add(result, oversamplingMode.byteValue());
		result = ArrayUtils.add(result, dynamicRange.byteValue());
		result = ArrayUtils.addAll(result, common.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, commonPeriodic.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, commonEvent.toByteArray(isBigEndian));
		return result;
	}
}
