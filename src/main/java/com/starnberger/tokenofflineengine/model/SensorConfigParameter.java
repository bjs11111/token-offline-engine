/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorConfigParameter;
import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.SyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorConfigParameter.lastModified", query = "SELECT g from SensorConfigParameter g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorConfigParameter.deleted", query = "SELECT g from SensorConfigParameter g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "SensorConfigParameter.findMyWebKey", query = "select s from SensorConfigParameter s where s.webKey = :webKey") })
public class SensorConfigParameter extends SyncEntity implements ISensorConfigParameter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6324178668476664387L;

	@Column
	private String key;
	@Column
	private String description;
	@Column
	private Class<?> type;
	@Column
	private int sequence;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#getKey()
	 */
	@Override
	public String getKey() {
		return key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#setKey(
	 * java.lang.String)
	 */
	@Override
	public void setKey(String key) {
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#getDescription
	 * ()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#setDescription
	 * (java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#getType()
	 */
	@Override
	public Class<?> getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#setType
	 * (java.lang.Class)
	 */
	@Override
	public void setType(Class<?> type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#getSequence
	 * ()
	 */
	@Override
	public int getSequence() {
		return sequence;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISensorConfigParameter#setSequence
	 * (int)
	 */
	@Override
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

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
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
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
		return "SensorConfigParameter [id=" + id + ", version=" + version + ", key=" + key + ", description="
				+ description + ", type=" + type + ", sequence=" + sequence + "]";
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfigParameter)
		{
			SensorConfigParameter token = (SensorConfigParameter) source;
			setWebKey(token.getWebKey());
			setDescription(token.getDescription());
			setKey(token.getKey());
			setSequence(token.getSequence());
			setType(token.getType());
		}
	}

}
