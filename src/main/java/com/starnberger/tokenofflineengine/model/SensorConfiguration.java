package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorConfiguration;
import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorConfiguration.lastModified", query = "SELECT g from SensorConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorConfiguration.deleted", query = "SELECT g from SensorConfiguration g WHERE g.deleted = :isDeleted"),
		@NamedQuery(name = "SensorConfiguration.forPartner", query = "SELECT g from SensorConfiguration g, TokenConfiguration t WHERE g.ownerKey = t.id and g.deleted = FALSE and t.deleted = FALSE and t.partnerKey = :partner"),
		@NamedQuery(name = "SensorConfiguration.findMyWebKey", query = "select s from SensorConfiguration s where s.remoteId = :webKey") })
public class SensorConfiguration extends SyncEntity implements ISensorConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079529239682539322L;
	@Column
	private Long ownerKey;
	@Column
	private Long sensorTypeKey;
	@Column
	private String position;
	@Basic
	private List<Long> configValueKeys = new ArrayList<Long>();

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
	public Long getOwnerKey() {
		return ownerKey;
	}

	@Override
	public void setOwnerKey(Long owner) {
		this.ownerKey = owner;
	}

	@Override
	public Long getSensorTypeKey() {
		return sensorTypeKey;
	}

	@Override
	public void setSensorTypeKey(Long sensorType) {
		this.sensorTypeKey = sensorType;
	}

	@Override
	public List<Long> getConfigValueKeys() {
		return configValueKeys;
	}

	@Override
	public void setConfigValueKeys(List<Long> configValue) {
		this.configValueKeys = configValue;
	}

	/**
	 * @return the position
	 */
	@Override
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	@Override
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfiguration) {
			SensorConfiguration token = (SensorConfiguration) source;
			// setWebKey(token.getWebKey());
			setId(token.getId());
			setConfigValueKeys(token.getConfigValueKeys());
			setOwnerKey(token.getOwnerKey());
			setSensorTypeKey(token.getSensorTypeKey());
			setPosition(token.getPosition());
		}
	}

}