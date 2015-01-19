package com.starnberger.tokenofflineengine.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.TokenConfiguration;

@MappedSuperclass
public abstract class AbstractTokenConfiguration extends SyncEntity implements ITokenConfiguration  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6398372617991253443L;
	@Column
	protected String modelKey;
	@Column
	protected int bleAdvertisingInterval;
	@Column
	protected int bleBondableInterval;
	@Column
	protected boolean bleAdvertisingConditionAlways;
	@Column
	protected int bleTxPower;
	@ElementCollection
	private Set<String> sensorConfigKeys = new HashSet<String>();

	public AbstractTokenConfiguration() {
		super();
	}

	@Override
	public String getModelKey() {
		return modelKey;
	}

	@Override
	public void setModelKey(String model) {
		this.modelKey = model;
	}

	@Override
	public int getBleAdvertisingInterval() {
		return bleAdvertisingInterval;
	}

	@Override
	public void setBleAdvertisingInterval(int bleAdvertisingInterval) {
		this.bleAdvertisingInterval = bleAdvertisingInterval;
	}

	@Override
	public int getBleBondableInterval() {
		return bleBondableInterval;
	}

	@Override
	public void setBleBondableInterval(int bleBondableInterval) {
		this.bleBondableInterval = bleBondableInterval;
	}

	@Override
	public boolean isBleAdvertisingConditionAlways() {
		return bleAdvertisingConditionAlways;
	}

	@Override
	public void setBleAdvertisingConditionAlways(boolean bleAdvertisingConditionAlways) {
		this.bleAdvertisingConditionAlways = bleAdvertisingConditionAlways;
	}

	@Override
	public int getBleTxPower() {
		return bleTxPower;
	}

	@Override
	public void setBleTxPower(int bleTxPower) {
		this.bleTxPower = bleTxPower;
	}

	@Override
	public Set<String> getSensorConfigKeys() {
		return sensorConfigKeys;
	}

	@Override
	public void setSensorConfigKeys(Set<String> sensorConfigs) {
		this.sensorConfigKeys = sensorConfigs;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof TokenConfiguration)
		{
			TokenConfiguration token = (TokenConfiguration) source;
			setBleAdvertisingConditionAlways(token.isBleAdvertisingConditionAlways());
			setBleAdvertisingInterval(token.getBleAdvertisingInterval());
			setBleBondableInterval(token.getBleBondableInterval());
			setBleTxPower(token.getBleTxPower());
			setModelKey(token.getModelKey());
			setSensorConfigKeys(token.getSensorConfigKeys());
			setWebKey(token.getWebKey());
		}
	}

}