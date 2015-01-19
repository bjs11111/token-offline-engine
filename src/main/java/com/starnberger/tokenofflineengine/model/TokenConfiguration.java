package com.starnberger.tokenofflineengine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.AbstractTokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TokenConfiguration.lastModified", query = "SELECT g from TokenConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "TokenConfiguration.deleted", query = "SELECT g from TokenConfiguration g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "TokenConfiguration.findMyWebKey", query = "select s from TokenConfiguration s where s.webKey = :webKey") })
public class TokenConfiguration extends AbstractTokenConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7773718071451539938L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TokenConfiguration)) {
			return false;
		}
		TokenConfiguration other = (TokenConfiguration) obj;
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
		if (modelKey != null)
			result += ", model: " + modelKey;
		result += ", bleAdvertisingInterval: " + bleAdvertisingInterval;
		result += ", bleBondableInterval: " + bleBondableInterval;
		result += ", bleAdvertisingConditionAlways: " + bleAdvertisingConditionAlways;
		result += ", bleTxPower: " + bleTxPower;
		if (lastModified != null)
			result += ", lastModified: " + lastModified;
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