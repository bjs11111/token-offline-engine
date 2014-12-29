package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorType;
import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.SyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorType.lastModified", query = "SELECT g from SensorType g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorType.deleted", query = "SELECT g from SensorType g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "SensorType.findMyWebKey", query = "select s from SensorType s where s.webKey = :webKey") })
public class SensorType extends SyncEntity implements ISensorType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3532010611671709056L;

	@Column
	private String description;
	@Column
	private String unit;
	@Column
	private int numberOfValues;
	@ElementCollection
	// SensorConfigParameter.webKey
	private List<String> configValues = new ArrayList<String>();

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

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#getUnit()
	 */
	@Override
	public String getUnit() {
		return unit;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#setUnit(java.lang.String)
	 */
	@Override
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#getNumberOfValues()
	 */
	@Override
	public int getNumberOfValues() {
		return numberOfValues;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#setNumberOfValues(int)
	 */
	@Override
	public void setNumberOfValues(int numberOfValues) {
		this.numberOfValues = numberOfValues;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#getConfigValues()
	 */
	@Override
	public List<String> getConfigValues() {
		return configValues;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorType#setConfigValues(java.util.List)
	 */
	@Override
	public void setConfigValues(List<String> configValues) {
		this.configValues = configValues;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorType)
		{
			SensorType token = (SensorType) source;
			setWebKey(token.getWebKey());
			setConfigValues(token.getConfigValues());
			setDescription(token.getDescription());
			setNumberOfValues(token.getNumberOfValues());
			setUnit(token.getUnit());
		}
	}

}