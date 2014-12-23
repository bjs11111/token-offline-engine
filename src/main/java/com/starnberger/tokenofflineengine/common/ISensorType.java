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
	public abstract List<String> getConfigValues();

	/**
	 * @param configValues
	 *            the configValues to set
	 */
	public abstract void setConfigValues(List<String> configValues);

}