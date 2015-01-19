package com.starnberger.tokenofflineengine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.AbstractSensorConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorConfiguration.lastModified", query = "SELECT g from SensorConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorConfiguration.deleted", query = "SELECT g from SensorConfiguration g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "SensorConfiguration.findMyWebKey", query = "select s from SensorConfiguration s where s.webKey = :webKey") })
public class SensorConfiguration extends AbstractSensorConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079529239682539322L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;

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

}