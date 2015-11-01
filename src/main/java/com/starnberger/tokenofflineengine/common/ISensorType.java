package com.starnberger.tokenofflineengine.common;

import java.util.List;

/**
 * @author Roman Kaufmann
 *
 */
public interface ISensorType extends ISyncEntity {

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
	 * @return the unit
	 */
	public abstract String getUnit();

	/**
	 * @param unit
	 *            the unit to set
	 */
	public abstract void setUnit(String unit);

	/**
	 * @return the numberOfValues
	 */
	public abstract int getNumberOfValues();

	/**
	 * @param numberOfValues
	 *            the numberOfValues to set
	 */
	public abstract void setNumberOfValues(int numberOfValues);

	/**
	 * @return the configValues
	 */
	public abstract List<Long> getConfigValues();

	/**
	 * @param configValues
	 *            the configValues to set
	 */
	public abstract void setConfigValues(List<Long> configValues);

	public abstract void setWebComponentPath(String webComponentPath);

	public abstract String getWebComponentPath();

	public abstract void setWebComponentName(String webComponentName);

	public abstract String getWebComponentName();

}