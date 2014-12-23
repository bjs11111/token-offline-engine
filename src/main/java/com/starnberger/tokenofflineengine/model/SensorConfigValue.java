/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.starnberger.tokenofflineengine.common.ISensorConfigValue;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
public class SensorConfigValue extends SyncEntity implements ISensorConfigValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2454959884417555325L;
	
	@Column
	private String key;
	@Column
	private Serializable value;

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfigValue#getKey()
	 */
	@Override
	public String getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfigValue#setKey(java.lang.String)
	 */
	@Override
	public void setKey(String key) {
		this.key = key;
	}
	
	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfigValue#getValue()
	 */
	@Override
	public Serializable getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorConfigValue#setValue(java.io.Serializable)
	 */
	@Override
	public void setValue(Serializable value) {
		this.value = value;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
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
		return "SensorConfigValue [id=" + id + ", version=" + version + ", key=" + key + ", value=" + value + "]";
	}

}
