package com.starnberger.tokenofflineengine.common;

import java.util.List;

/**
 * @author Roman Kaufmann
 *
 */
public interface ISensorConfiguration extends ISyncEntity {

	/**
	 * @return the owner
	 */
	public abstract String getOwnerKey();

	/**
	 * @param owner
	 *            the owner to set
	 */
	public abstract void setOwnerKey(String owner);

	/**
	 * @return the sensorType
	 */
	public abstract String getSensorTypeKey();

	/**
	 * @param sensorType
	 *            the sensorType to set
	 */
	public abstract void setSensorTypeKey(String sensorType);

	/**
	 * @return the configValue
	 */
	public abstract List<String> getConfigValueKeys();

	/**
	 * @param configValue
	 *            the configValue to set
	 */
	public abstract void setConfigValueKeys(List<String> configValue);

}