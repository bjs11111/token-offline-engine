package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.Map;

import net.sourceforge.juint.Int16;
import net.sourceforge.juint.UInt16;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
public abstract class AbstractSensorStructure {

	protected Map<String, SensorConfigValue> configValues;

	/**
	 * @param configValues
	 */
	public AbstractSensorStructure(Map<String, SensorConfigValue> configValues) {
		super();
		this.configValues = configValues;
	}

	/**
	 * @param key
	 * @return
	 */
	protected int getBooleanValue(String key) {
		SensorConfigValue sensorConfigValue = configValues.get(key);
		if (sensorConfigValue != null) {
			String value = sensorConfigValue.getValue();
			if (value == null || value.isEmpty())
				return 0;
			if (Boolean.parseBoolean(value) == true)
				return 1;
		}
		return 0;
	}
	
	/**
	 * @param key
	 * @return
	 */
	protected int getIntValue(String key) {
		SensorConfigValue sensorConfigValue = configValues.get(key);
		if (sensorConfigValue != null) {
			String value = sensorConfigValue.getValue();
			if (value == null || value.isEmpty())
				return 0;
			return Integer.parseInt(value);
		}
		return 0;
	}
	
	/**
	 * @param target
	 * @param value
	 * @param isBigEndian
	 * @return
	 */
	protected byte[] addAll(byte[] target, UInt16 value, boolean isBigEndian) {
		if (isBigEndian)
			return ArrayUtils.addAll(target, value.toBigEndian());
		else
			return ArrayUtils.addAll(target, value.toLittleEndian());
	}
	
	/**
	 * @param target
	 * @param value
	 * @param isBigEndian
	 * @return
	 */
	protected byte[] addAll(byte[] target, Int16 value, boolean isBigEndian) {
		if (isBigEndian)
			return ArrayUtils.addAll(target, value.toBigEndian());
		else
			return ArrayUtils.addAll(target, value.toLittleEndian());
	}
	
	
	/**
	 * @param isBigEndian
	 * @return
	 */
	public abstract byte[] toByteArray(boolean isBigEndian);

}