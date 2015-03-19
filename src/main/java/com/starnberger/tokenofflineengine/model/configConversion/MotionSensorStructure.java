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
public class MotionSensorStructure extends AbstractSensorStructure {
	private UInt16 threshold;
	private UInt8 interval;
	private UInt8 timeout;
	private SensorCommon common;
	private EventSensorCommon commonEvent;

	/**
	 * @param configValues
	 */
	public MotionSensorStructure(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		threshold = new UInt16(getIntValue("sensorMotionThreshold"));
		interval = new UInt8(getIntValue("sensorMotionDetectionInterval"));
		timeout = new UInt8(getIntValue("sensorMotionDetectionTimeout"));
		common = new SensorCommon(configValues);
		commonEvent = new EventSensorCommon(configValues);
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = addAll(result, threshold, isBigEndian);
		result = ArrayUtils.add(result, interval.byteValue());
		result = ArrayUtils.add(result, timeout.byteValue());
		result = ArrayUtils.addAll(result, common.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, commonEvent.toByteArray(isBigEndian));
		return result;
	}
}
