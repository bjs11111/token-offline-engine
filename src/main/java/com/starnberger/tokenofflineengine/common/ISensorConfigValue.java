package com.starnberger.tokenofflineengine.common;


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
	public abstract String getValue();

	/**
	 * @param value
	 *            the value to set
	 */
	public abstract void setValue(String value);

}