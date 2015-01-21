package com.starnberger.tokenofflineengine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.ITokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TokenConfiguration.lastModified", query = "SELECT g from TokenConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "TokenConfiguration.deleted", query = "SELECT g from TokenConfiguration g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "TokenConfiguration.findMyWebKey", query = "select s from TokenConfiguration s where s.webKey = :webKey") })
public class TokenConfiguration extends SyncEntity implements ITokenConfiguration  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7773718071451539938L;
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
	@Basic
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