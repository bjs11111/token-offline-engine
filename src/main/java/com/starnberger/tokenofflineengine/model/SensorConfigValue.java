/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorConfigValue;
import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorConfigValue.lastModified", query = "SELECT g from SensorConfigValue g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorConfigValue.deleted", query = "SELECT g from SensorConfigValue g WHERE g.deleted = :isDeleted"),
		@NamedQuery(name = "SensorConfigValue.findMyWebKey", query = "select s from SensorConfigValue s where s.remoteId = :webKey") })
public class SensorConfigValue extends SyncEntity implements ISensorConfigValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8579889330329040246L;
	@Column
	protected String configKey;
	@Column
	protected String value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((configKey == null) ? 0 : configKey.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + version;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorConfigValue other = (SensorConfigValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (configKey == null) {
			if (other.configKey != null)
				return false;
		} else if (!configKey.equals(other.configKey))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SensorConfigValue [id=" + id + ", version=" + version + ", key=" + configKey + ", value=" + value + "]";
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
	public String getConfigKey() {
		return configKey;
	}

	@Override
	public void setConfigKey(String key) {
		this.configKey = key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfigValue) {
			SensorConfigValue token = (SensorConfigValue) source;
			//setId(token.getId());
			setConfigKey(token.getConfigKey());
			setValue(token.getValue());
		}
	}

	@Override
	public String toFilterString() {
		return String.valueOf(configKey) + " " + String.valueOf(value);
	}

}
