package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorType;
import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorType.lastModified", query = "SELECT g from SensorType g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorType.deleted", query = "SELECT g from SensorType g WHERE g.deleted = :isDeleted ORDER BY g.description"),
		@NamedQuery(name = "SensorType.findMyWebKey", query = "select s from SensorType s where s.remoteId = :webKey") })
public class SensorType extends SyncEntity implements ISensorType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529277605413179550L;
	@Column
	protected String description;
	@Column
	protected String unit;
	@Column
	protected int numberOfValues = 1;
	@Column
	protected String webComponentName;
	@Column
	protected String webComponentPath;
	@Basic
	protected List<Long> configValues = new ArrayList<Long>();

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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getUnit() {
		return unit;
	}

	@Override
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public int getNumberOfValues() {
		return numberOfValues;
	}

	@Override
	public void setNumberOfValues(int numberOfValues) {
		this.numberOfValues = numberOfValues;
	}

	@Override
	public List<Long> getConfigValues() {
		return configValues;
	}

	@Override
	public void setConfigValues(List<Long> configValues) {
		this.configValues = configValues;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorType) {
			SensorType token = (SensorType) source;
			// setWebKey(token.getWebKey());
			// setId(token.getId());
			setConfigValues(token.getConfigValues());
			setDescription(token.getDescription());
			setNumberOfValues(token.getNumberOfValues());
			setUnit(token.getUnit());
			setWebComponentName(token.getWebComponentName());
			setWebComponentPath(token.getWebComponentPath());
		}
	}

	/**
	 * @return the webComponentName
	 */
	@Override
	public String getWebComponentName() {
		return webComponentName;
	}

	/**
	 * @param webComponentName
	 *            the webComponentName to set
	 */
	@Override
	public void setWebComponentName(String webComponentName) {
		this.webComponentName = webComponentName;
	}

	/**
	 * @return the webComponentPath
	 */
	@Override
	public String getWebComponentPath() {
		return webComponentPath;
	}

	/**
	 * @param webComponentPath
	 *            the webComponentPath to set
	 */
	@Override
	public void setWebComponentPath(String webComponentPath) {
		this.webComponentPath = webComponentPath;
	}

	@Override
	public String toFilterString() {
		return description + " " + unit + " " + numberOfValues + " " + webComponentName + " " + webComponentPath;
	}

	@Override
	public String toString() {
		return "SensorType [description=" + description + ", unit=" + unit + ", numberOfValues=" + numberOfValues
				+ ", webComponentName=" + webComponentName + ", webComponentPath=" + webComponentPath
				+ ", configValues=" + configValues + "]";
	}

}