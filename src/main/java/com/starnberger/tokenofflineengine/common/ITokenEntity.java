package com.starnberger.tokenofflineengine.common;

/**
 * @author Roman Kaufmann
 *
 */
public interface ITokenEntity {

	/**
	 * @return the version
	 */
	public abstract int getVersion();

	/**
	 * @param version
	 *            the version to set
	 */
	public abstract void setVersion(int version);

	/**
	 * @return the webKey
	 */
	public abstract String getWebKey();

	/**
	 * @param webKey
	 *            the webKey to set
	 */
	public abstract void setWebKey(String webKey);

}