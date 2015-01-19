package com.starnberger.tokenofflineengine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.AbstractSensorType;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorType.lastModified", query = "SELECT g from SensorType g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorType.deleted", query = "SELECT g from SensorType g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "SensorType.findMyWebKey", query = "select s from SensorType s where s.webKey = :webKey") })
public class SensorType extends AbstractSensorType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529277605413179550L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;

	/**
	 * Default constructor.
	 */
	public SensorType() {
		// Add default sensor config parameter values
		SensorConfigParameter broadCastEnabled = new SensorConfigParameter();
		broadCastEnabled.setKey("broadCastEnabled");
		broadCastEnabled.setType(Boolean.class);
		SensorConfigParameter readInterval = new SensorConfigParameter();
		readInterval.setKey("readInterval");
		readInterval.setType(Integer.class);
		SensorConfigParameter loggingInterval = new SensorConfigParameter();
		loggingInterval.setKey("loggingInterval");
		loggingInterval.setType(Integer.class);
		SensorConfigParameter loggingIntervalAlarm = new SensorConfigParameter();
		loggingIntervalAlarm.setKey("loggingIntervalAlarm");
		loggingIntervalAlarm.setType(Integer.class);
		configValues.add(broadCastEnabled.getWebKey());
		configValues.add(readInterval.getWebKey());
		configValues.add(loggingInterval.getWebKey());
		configValues.add(loggingIntervalAlarm.getWebKey());
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
		result = prime * result + ((configValues == null) ? 0 : configValues.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + numberOfValues;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		SensorType other = (SensorType) obj;
		if (configValues == null) {
			if (other.configValues != null)
				return false;
		} else if (!configValues.equals(other.configValues))
			return false;
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
		if (numberOfValues != other.numberOfValues)
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
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
		return "SensorType [id=" + id + ", version=" + version + ", description=" + description + ", unit=" + unit
				+ ", numberOfValues=" + numberOfValues + ", configValues=" + configValues + "]";
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