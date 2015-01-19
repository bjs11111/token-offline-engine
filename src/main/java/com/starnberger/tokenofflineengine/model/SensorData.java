package com.starnberger.tokenofflineengine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.AbstractSensorData;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
public class SensorData extends AbstractSensorData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751327066679701819L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SensorData)) {
			return false;
		}
		SensorData other = (SensorData) obj;
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
		if (timestamp != null)
			result += ", timestamp: " + timestamp;
		if (sensorType != null)
			result += ", sensorType: " + sensorType;
		if (gateway != null)
			result += ", gateway: " + gateway;
		result += ", value1: " + value1;
		result += ", value2: " + value2;
		result += ", value3: " + value3;
		if (token != null)
			result += ", token: " + token;
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
}