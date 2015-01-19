package com.starnberger.tokenofflineengine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.AbstractGatewayConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "GatewayConfiguration.lastModified", query = "SELECT g from GatewayConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "GatewayConfiguration.deleted", query = "SELECT g from GatewayConfiguration g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "GatewayConfiguration.findMyWebKey", query = "select s from GatewayConfiguration s where s.webKey = :webKey") })
public class GatewayConfiguration extends AbstractGatewayConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5393538827784408703L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GatewayConfiguration)) {
			return false;
		}
		GatewayConfiguration other = (GatewayConfiguration) obj;
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