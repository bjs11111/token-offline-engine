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
	public abstract Long getOwnerKey();

	/**
	 * @param owner
	 *            the owner to set
	 */
	public abstract void setOwnerKey(Long owner);

	/**
	 * @return the sensorType
	 */
	public abstract Long getSensorTypeKey();

	/**
	 * @param sensorType
	 *            the sensorType to set
	 */
	public abstract void setSensorTypeKey(Long sensorType);

	/**
	 * @return the configValue
	 */
	public abstract List<Long> getConfigValueKeys();

	/**
	 * @param configValue
	 *            the configValue to set
	 */
	public abstract void setConfigValueKeys(List<Long> configValue);

	public abstract void setPosition(String position);

	public abstract String getPosition();

}