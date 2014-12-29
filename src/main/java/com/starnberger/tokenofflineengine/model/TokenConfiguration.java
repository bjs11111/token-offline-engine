package com.starnberger.tokenofflineengine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.ITokenConfiguration;
import com.starnberger.tokenofflineengine.common.SyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TokenConfiguration.lastModified", query = "SELECT g from TokenConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "TokenConfiguration.deleted", query = "SELECT g from TokenConfiguration g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "TokenConfiguration.findMyWebKey", query = "select s from TokenConfiguration s where s.webKey = :webKey") })
public class TokenConfiguration extends SyncEntity implements ITokenConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6398372617991253443L;

	@Column
	// TokenModel.webKey
	private String modelKey;

	@Column
	private int bleAdvertisingInterval;

	@Column
	private int bleBondableInterval;

	@Column
	private boolean bleAdvertisingConditionAlways;

	@Column
	private int bleTxPower;

	@ElementCollection
	// SensorConfiguration
	private Set<String> sensorConfigKeys = new HashSet<String>();

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

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#getModelKey()
	 */
	@Override
	public String getModelKey() {
		return modelKey;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#setModelKey(java.lang.String)
	 */
	@Override
	public void setModelKey(String model) {
		this.modelKey = model;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#getBleAdvertisingInterval()
	 */
	@Override
	public int getBleAdvertisingInterval() {
		return bleAdvertisingInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#setBleAdvertisingInterval(int)
	 */
	@Override
	public void setBleAdvertisingInterval(int bleAdvertisingInterval) {
		this.bleAdvertisingInterval = bleAdvertisingInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#getBleBondableInterval()
	 */
	@Override
	public int getBleBondableInterval() {
		return bleBondableInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#setBleBondableInterval(int)
	 */
	@Override
	public void setBleBondableInterval(int bleBondableInterval) {
		this.bleBondableInterval = bleBondableInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#isBleAdvertisingConditionAlways()
	 */
	@Override
	public boolean isBleAdvertisingConditionAlways() {
		return bleAdvertisingConditionAlways;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#setBleAdvertisingConditionAlways(boolean)
	 */
	@Override
	public void setBleAdvertisingConditionAlways(boolean bleAdvertisingConditionAlways) {
		this.bleAdvertisingConditionAlways = bleAdvertisingConditionAlways;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#getBleTxPower()
	 */
	@Override
	public int getBleTxPower() {
		return bleTxPower;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#setBleTxPower(int)
	 */
	@Override
	public void setBleTxPower(int bleTxPower) {
		this.bleTxPower = bleTxPower;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (modelKey != null)
			result += ", model: " + modelKey;
		result += ", bleAdvertisingInterval: " + bleAdvertisingInterval;
		result += ", bleBondableInterval: " + bleBondableInterval;
		result += ", bleAdvertisingConditionAlways: " + bleAdvertisingConditionAlways;
		result += ", bleTxPower: " + bleTxPower;
		if (lastModified != null)
			result += ", lastModified: " + lastModified;
		return result;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#getSensorConfigKeys()
	 */
	@Override
	public Set<String> getSensorConfigKeys() {
		return sensorConfigKeys;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenConfiguration#setSensorConfigKeys(java.util.Set)
	 */
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