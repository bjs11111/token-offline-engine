package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorConfiguration;
import com.starnberger.tokenofflineengine.common.ISyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorConfiguration.lastModified", query = "SELECT g from SensorConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorConfiguration.deleted", query = "SELECT g from SensorConfiguration g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "SensorConfiguration.findMyWebKey", query = "select s from SensorConfiguration s where s.webKey = :webKey") })
public class SensorConfiguration extends SyncEntity implements ISensorConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079529239682539322L;
	@Column
	private String ownerKey;
	@Column
	private String sensorTypeKey;
	@Basic
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
	public String getOwnerKey() {
		return ownerKey;
	}

	@Override
	public void setOwnerKey(String owner) {
		this.ownerKey = owner;
	}

	@Override
	public String getSensorTypeKey() {
		return sensorTypeKey;
	}

	@Override
	public void setSensorTypeKey(String sensorType) {
		this.sensorTypeKey = sensorType;
	}

	@Override
	public List<String> getConfigValueKeys() {
		return configValueKeys;
	}

	@Override
	public void setConfigValueKeys(List<String> configValue) {
		this.configValueKeys = configValue;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfiguration) {
			SensorConfiguration token = (SensorConfiguration) source;
			setWebKey(token.getWebKey());
			setConfigValueKeys(token.getConfigValueKeys());
			setOwnerKey(token.getOwnerKey());
			setSensorTypeKey(token.getSensorTypeKey());
		}
	}

}