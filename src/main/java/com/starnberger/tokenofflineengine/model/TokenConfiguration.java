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
		@NamedQuery(name = "TokenConfiguration.deleted", query = "SELECT g from TokenConfiguration g WHERE g.deleted = :isDeleted ORDER BY g.name"),
		@NamedQuery(name = "TokenConfiguration.forPartner", query = "SELECT g from TokenConfiguration g WHERE g.deleted = FALSE and g.partnerKey = :partner ORDER BY g.name"),
		@NamedQuery(name = "TokenConfiguration.findMyWebKey", query = "select s from TokenConfiguration s where s.remoteId = :webKey"),
		@NamedQuery(name = "TokenConfiguration.findByModel", query = "select s from TokenConfiguration s where s.modelKey = :modelKey") })
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
	// A log download is started automatically, if the related token was not
	// seen for the defined interval that is specified here
	@Column
	protected int logDownloadInterval = -1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenConfiguration other = (TokenConfiguration) obj;
		if (bleAdvertisingConditionAlways != other.bleAdvertisingConditionAlways)
			return false;
		if (bleAdvertisingInterval != other.bleAdvertisingInterval)
			return false;
		if (bleBondableInterval != other.bleBondableInterval)
			return false;
		if (bleTxPower != other.bleTxPower)
			return false;
		if (logDownloadInterval != other.logDownloadInterval)
			return false;
		if (modelKey == null) {
			if (other.modelKey != null)
				return false;
		} else if (!modelKey.equals(other.modelKey))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (partnerKey == null) {
			if (other.partnerKey != null)
				return false;
		} else if (!partnerKey.equals(other.partnerKey))
			return false;
		if (sensorConfigKeys == null) {
			if (other.sensorConfigKeys != null)
				return false;
		} else if (!sensorConfigKeys.equals(other.sensorConfigKeys))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (bleAdvertisingConditionAlways ? 1231 : 1237);
		result = prime * result + bleAdvertisingInterval;
		result = prime * result + bleBondableInterval;
		result = prime * result + bleTxPower;
		result = prime * result + logDownloadInterval;
		result = prime * result + ((modelKey == null) ? 0 : modelKey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((partnerKey == null) ? 0 : partnerKey.hashCode());
		result = prime * result + ((sensorConfigKeys == null) ? 0 : sensorConfigKeys.hashCode());
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
				+ ", sensorConfigKeys=" + sensorConfigKeys + ", partnerKey=" + partnerKey + ", logDownloadInterval="
				+ logDownloadInterval + "]";
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

	/**
	 * @return the logDownloadInterval
	 */
	public int getLogDownloadInterval() {
		return logDownloadInterval;
	}

	/**
	 * @param logDownloadInterval
	 *            the logDownloadInterval to set
	 */
	public void setLogDownloadInterval(int logDownloadInterval) {
		this.logDownloadInterval = logDownloadInterval;
	}

	@Override
	public String toFilterString() {
		return String.valueOf(bleAdvertisingConditionAlways) + " " + String.valueOf(bleAdvertisingInterval) + " "
				+ String.valueOf(bleBondableInterval) + " " + String.valueOf(bleTxPower) + " " + String.valueOf(name)
				+ " " + String.valueOf(logDownloadInterval);
	}

}