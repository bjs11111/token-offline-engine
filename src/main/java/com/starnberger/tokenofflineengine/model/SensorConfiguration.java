package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorConfiguration.lastModified", query = "SELECT g from SensorConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorConfiguration.deleted", query = "SELECT g from SensorConfiguration g WHERE g.isDeleted = :isDeleted") })
public class SensorConfiguration extends SyncEntity implements ISensorConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6382431230433111245L;

	@Column
	// TokenConfiguration.webKey
	private String ownerKey;

	@Column
	// SensorType.webKey
	private String sensorTypeKey;

	@ElementCollection
	// SensorConfigValue
	private List<String> configValueKeys = new ArrayList<String>();

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SensorConfiguration)) {
			return false;
		}
		SensorConfiguration other = (SensorConfiguration) obj;
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
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfiguration#getOwnerKey()
	 */
	@Override
	public String getOwnerKey() {
		return ownerKey;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfiguration#setOwnerKey(java.lang.String)
	 */
	@Override
	public void setOwnerKey(String owner) {
		this.ownerKey = owner;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfiguration#getSensorTypeKey()
	 */
	@Override
	public String getSensorTypeKey() {
		return sensorTypeKey;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfiguration#setSensorTypeKey(java.lang.String)
	 */
	@Override
	public void setSensorTypeKey(String sensorType) {
		this.sensorTypeKey = sensorType;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfiguration#getConfigValueKeys()
	 */
	@Override
	public List<String> getConfigValueKeys() {
		return configValueKeys;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfiguration#setConfigValueKeys(java.util.List)
	 */
	@Override
	public void setConfigValueKeys(List<String> configValue) {
		this.configValueKeys = configValue;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}