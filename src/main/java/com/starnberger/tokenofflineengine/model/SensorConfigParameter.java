/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorConfigParameter;
import com.starnberger.tokenofflineengine.common.ITokenEntity;
import com.starnberger.tokenofflineengine.common.SensorConfigType;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorConfigParameter.lastModified", query = "SELECT g from SensorConfigParameter g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorConfigParameter.deleted", query = "SELECT g from SensorConfigParameter g WHERE g.deleted = :isDeleted"),
		@NamedQuery(name = "SensorConfigParameter.findMyWebKey", query = "select s from SensorConfigParameter s where s.id = :webKey") })
public class SensorConfigParameter extends SyncEntity implements ISensorConfigParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7551471925190574334L;
	@Column
	protected String configKey;
	@Column
	protected String description;
	@Column
	protected SensorConfigType type;
	@Column
	protected int sequence;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((configKey == null) ? 0 : configKey.hashCode());
		result = prime * result + sequence;
		result = prime * result + version;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#equals(
	 * java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorConfigParameter other = (SensorConfigParameter) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
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
		if (sequence != other.sequence)
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
		return "SensorConfigParameter [id=" + id + ", version=" + version + ", key=" + configKey + ", description="
				+ description + ", type=" + type + ", sequence=" + sequence + "]";
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public SensorConfigType getType() {
		return type;
	}

	@Override
	public void setType(SensorConfigType type) {
		this.type = type;
	}

	@Override
	public int getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfigParameter) {
			SensorConfigParameter token = (SensorConfigParameter) source;
			//setWebKey(token.getWebKey());
			setId(token.getId());
			setDescription(token.getDescription());
			setConfigKey(token.getConfigKey());
			setSequence(token.getSequence());
			setType(token.getType());
		}
	}

}
