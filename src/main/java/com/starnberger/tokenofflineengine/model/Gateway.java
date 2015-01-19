package com.starnberger.tokenofflineengine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.AbstractGateway;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Gateway.findCpuId", query = "SELECT g from Gateway g WHERE g.uuid = :cpuid and g.isDeleted = FALSE"),
		@NamedQuery(name = "Gateway.findToken", query = "SELECT g from Gateway g WHERE g.gatewayToken = :gatewayToken and g.isDeleted = FALSE"),
		@NamedQuery(name = "Gateway.lastModified", query = "SELECT g from Gateway g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "Gateway.deleted", query = "SELECT g from Gateway g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "Gateway.findMe", query = "SELECT g FROM Gateway g"),
		@NamedQuery(name = "Gateway.findMyWebKey", query = "select s from Gateway s where s.webKey = :webKey") })
public class Gateway extends AbstractGateway {

	/**
	 * 
	 */
	private static final long serialVersionUID = -450125878269432651L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Gateway)) {
			return false;
		}
		Gateway other = (Gateway) obj;
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
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		result += ", uuid: " + uuid;
		result += ", needsFirmwareUpgrade: " + needsFirmwareUpgrade;
		result += ", needsConfigUpgrade: " + needsConfigUpgrade;
		if (firmwareVersionKey != null)
			result += ", firmwareVersion: " + firmwareVersionKey;
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