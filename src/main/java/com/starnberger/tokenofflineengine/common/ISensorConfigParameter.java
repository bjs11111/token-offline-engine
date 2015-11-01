package com.starnberger.tokenofflineengine.common;

/**
 * @author Roman Kaufmann
 *
 */
public interface ISensorConfigParameter extends ISyncEntity {

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
	 * @return the description
	 */
	public abstract String getDescription();

	/**
	 * @param description
	 *            the description to set
	 */
	public abstract void setDescription(String description);

	/**
	 * @return the type
	 */
	public abstract SensorConfigType getType();

	/**
	 * @param type
	 *            the type to set
	 */
	public abstract void setType(SensorConfigType type);

	/**
	 * @return the sequence
	 */
	public abstract int getSequence();

	/**
	 * @param sequence
	 *            the sequence to set
	 */
	public abstract void setSequence(int sequence);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public abstract boolean equals(Object obj);

}