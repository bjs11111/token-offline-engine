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
public class SensorCommon extends AbstractSensorStructure {
	private UInt8 sensorEnabled;
	private UInt8 sensorBroadCastValueEnabled;

	/**
	 * @param configValues
	 */
	public SensorCommon(Map<String, SensorConfigValue> configValues) {
		super(configValues);
		sensorEnabled = new UInt8(getBooleanValue("sensorEnabled"));
		sensorBroadCastValueEnabled = new UInt8(getBooleanValue("bleBroadCastValueEnabled"));
	}

	@Override
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = ArrayUtils.add(result, sensorEnabled.byteValue());
		result = ArrayUtils.add(result, sensorBroadCastValueEnabled.byteValue());
		return result;
	}
	
}
