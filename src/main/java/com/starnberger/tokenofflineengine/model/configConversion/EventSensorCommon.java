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
public class EventSensorCommon extends AbstractSensorStructure {
	private UInt8 alarmEnabled;
	private UInt8 logIntervalAlarm;
	private UInt8 logSizeAlarm;
	private UInt8 bleAdvertisingAction;
	private UInt16 bleAdvertisingActionDuration;

	/**
	 * @param configValues
	 */
	public EventSensorCommon(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		alarmEnabled = new UInt8(getIntValue("alarmEnabled"));
		logIntervalAlarm = new UInt8(getIntValue("logIntervalAlarm"));
		logSizeAlarm = new UInt8(getIntValue("logSizeAlarm"));
		bleAdvertisingAction = new UInt8(getIntValue("bleAdvertisingAction"));
		bleAdvertisingActionDuration = new UInt16(getIntValue("bleAdvertisingActionDuration"));
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = ArrayUtils.add(result, alarmEnabled.byteValue());
		result = ArrayUtils.add(result, logIntervalAlarm.byteValue());
		result = ArrayUtils.add(result, logSizeAlarm.byteValue());
		result = ArrayUtils.add(result, bleAdvertisingAction.byteValue());
		if (isBigEndian)
			result = ArrayUtils.addAll(result, bleAdvertisingActionDuration.toBigEndian());
		else
			result = ArrayUtils.addAll(result, bleAdvertisingActionDuration.toLittleEndian());
		return result;
	}
}
