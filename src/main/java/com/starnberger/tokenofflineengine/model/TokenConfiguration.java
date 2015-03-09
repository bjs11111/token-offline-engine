package com.starnberger.tokenofflineengine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ITokenConfiguration;
import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TokenConfiguration.lastModified", query = "SELECT g from TokenConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "TokenConfiguration.deleted", query = "SELECT g from TokenConfiguration g WHERE g.deleted = :isDeleted"),
		@NamedQuery(name = "TokenConfiguration.forPartner", query = "SELECT g from TokenConfiguration g WHERE g.deleted = FALSE and g.partnerKey = :partner"),
		@NamedQuery(name = "TokenConfiguration.findMyWebKey", query = "select s from TokenConfiguration s where s.id = :webKey") })
public class TokenConfiguration extends SyncEntity implements ITokenConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7773718071451539938L;
	@Column
	protected String name;
	@Column
	protected Long modelKey;
	@Column
	protected int bleAdvertisingInterval = 1000;
	@Column
	protected int bleBondableInterval = -1;
	@Column
	protected boolean bleAdvertisingConditionAlways = true;
	@Column
	protected int bleTxPower = 0;
	@Basic
	private Set<Long> sensorConfigKeys = new HashSet<Long>();
	@Column
	protected Long partnerKey;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TokenConfiguration)) {
			return false;
		}
		TokenConfiguration other = (TokenConfiguration) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenConfiguration [name=" + name + ", modelKey=" + modelKey + ", bleAdvertisingInterval="
				+ bleAdvertisingInterval + ", bleBondableInterval=" + bleBondableInterval
				+ ", bleAdvertisingConditionAlways=" + bleAdvertisingConditionAlways + ", bleTxPower=" + bleTxPower
				+ ", sensorConfigKeys=" + sensorConfigKeys + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getModelKey() {
		return modelKey;
	}

	@Override
	public void setModelKey(Long model) {
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
	public Set<Long> getSensorConfigKeys() {
		return sensorConfigKeys;
	}

	@Override
	public void setSensorConfigKeys(Set<Long> sensorConfigs) {
		this.sensorConfigKeys = sensorConfigs;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof TokenConfiguration) {
			TokenConfiguration token = (TokenConfiguration) source;
			setBleAdvertisingConditionAlways(token.isBleAdvertisingConditionAlways());
			setBleAdvertisingInterval(token.getBleAdvertisingInterval());
			setBleBondableInterval(token.getBleBondableInterval());
			setBleTxPower(token.getBleTxPower());
			setModelKey(token.getModelKey());
			setSensorConfigKeys(token.getSensorConfigKeys());
			setName(token.getName());
			setId(token.getId());
		}
	}

	/**
	 * @return the partnerKey
	 */
	@Override
	public Long getPartnerKey() {
		return partnerKey;
	}

	/**
	 * @param partnerKey
	 *            the partnerKey to set
	 */
	@Override
	public void setPartnerKey(Long partnerKey) {
		this.partnerKey = partnerKey;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

}