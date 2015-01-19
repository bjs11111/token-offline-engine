package com.starnberger.tokenofflineengine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.AbstractToken;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Token.ownedBy", query = "SELECT t from Token t WHERE t.owner = :owner and t.isDeleted = FALSE"),
		@NamedQuery(name = "Token.lastModifiedByOwner", query = "SELECT t from Token t WHERE t.owner = :owner and t.lastModified > :lastSyncDate"),
		@NamedQuery(name = "Token.lastModified", query = "SELECT t from Token t WHERE t.lastModified > :lastSyncDate"),
		@NamedQuery(name = "Token.deleted", query = "SELECT t from Token t WHERE t.isDeleted = :isDeleted"),
		@NamedQuery(name = "Token.findMyWebKey", query = "select s from Token s where s.webKey = :webKey") })
public class Token extends AbstractToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298223670658279856L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Token)) {
			return false;
		}
		Token other = (Token) obj;
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
		result += ", mac: " + mac;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		if (uuid != null && !uuid.trim().isEmpty())
			result += ", uuid: " + uuid;
		result += ", minor: " + minor;
		result += ", major: " + major;
		result += ", needsConfigUpdate: " + needsConfigUpdate;
		if (model != null)
			result += ", model: " + model;
		if (lastSyncDate != null)
			result += ", lastSyncDate: " + lastSyncDate;
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