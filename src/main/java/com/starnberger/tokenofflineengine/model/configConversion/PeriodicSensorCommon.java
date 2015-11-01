/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import net.sourceforge.juint.UInt8;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public class PeriodicSensorCommon extends AbstractSensorStructure {
	private UInt8 sensorReadInterval;
	private UInt8 logInterval;
	private UInt8 logSize;

	/**
	 * @param configValues
	 */
	public PeriodicSensorCommon(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		sensorReadInterval = new UInt8(getIntValue("sensorReadInterval"));
		logInterval = new UInt8(getIntValue("logInterval"));
		logSize = new UInt8(getIntValue("logSize"));
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = ArrayUtils.add(result, sensorReadInterval.byteValue());
		result = ArrayUtils.add(result, logInterval.byteValue());
		result = ArrayUtils.add(result, logSize.byteValue());
		return result;
	}

}
