package com.starnberger.tokenofflineengine.common;

import java.io.Serializable;

/**
 * @author Roman Kaufmann
 *
 */
public interface ISensorConfigValue extends ISyncEntity {

	/**
	 * @return the key
	 */
	public abstract String getConfigKey();

	/**
	 * @param key
	 *            the key to set
	 */
	public abstract void setConfigKey(String key);

	/**
	 * @return the value
	 */
	public abstract Serializable getValue();

	/**
	 * @param value
	 *            the value to set
	 */
	public abstract void setValue(Serializable value);

}