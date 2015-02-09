package com.starnberger.tokenofflineengine.common;

import com.google.gwt.core.client.js.JsType;
import com.workingflows.js.jscore.client.api.JsObject;

/**
 * @author Roman Kaufmann
 *
 */
@JsType
public interface ITokenEntity extends JsObject {

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
	 * @return
	 */
	public abstract Long getId();

	/**
	 * @param id
	 */
	public abstract void setId(Long id);

	/**
	 * @return the webKey
	 */
	// public abstract String getWebKey();

	/**
	 * @param webKey
	 *            the webKey to set
	 */
	// public abstract void setWebKey(String webKey);

	/**
	 * Copy values from the passed source to this instance.
	 * 
	 * @param source
	 */
	public abstract void copyValues(ITokenEntity source);

}