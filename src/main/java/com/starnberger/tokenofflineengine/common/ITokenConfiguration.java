package com.starnberger.tokenofflineengine.common;

import java.util.Set;

/**
 * @author Roman Kaufmann
 *
 */
public interface ITokenConfiguration extends ISyncEntity {

	/**
	 * @return
	 */
	public abstract Long getModelKey();

	/**
	 * @param model
	 */
	public abstract void setModelKey(Long model);

	/**
	 * @return
	 */
	public abstract int getBleAdvertisingInterval();

	/**
	 * @param bleAdvertisingInterval
	 */
	public abstract void setBleAdvertisingInterval(int bleAdvertisingInterval);

	/**
	 * @return
	 */
	public abstract int getBleBondableInterval();

	/**
	 * @param bleBondableInterval
	 */
	public abstract void setBleBondableInterval(int bleBondableInterval);

	/**
	 * @return
	 */
	public abstract boolean isBleAdvertisingConditionAlways();

	/**
	 * @param bleAdvertisingConditionAlways
	 */
	public abstract void setBleAdvertisingConditionAlways(boolean bleAdvertisingConditionAlways);

	/**
	 * @return
	 */
	public abstract int getBleTxPower();

	/**
	 * @param bleTxPower
	 */
	public abstract void setBleTxPower(int bleTxPower);

	/**
	 * @return the sensorConfigs
	 */
	public abstract Set<Long> getSensorConfigKeys();

	/**
	 * @param sensorConfigs
	 *            the sensorConfigs to set
	 */
	public abstract void setSensorConfigKeys(Set<Long> sensorConfigs);

	public abstract void setName(String name);

	public abstract String getName();

}