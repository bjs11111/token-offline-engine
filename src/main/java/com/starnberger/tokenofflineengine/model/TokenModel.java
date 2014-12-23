package com.starnberger.tokenofflineengine.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ITokenModel;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "TokenModel.lastModified", query = "SELECT g from TokenModel g WHERE g.lastModified > :lastSyncDate"),
	@NamedQuery(name = "TokenModel.deleted", query = "SELECT g from TokenModel g WHERE g.isDeleted = :isDeleted")
})
public class TokenModel extends SyncEntity implements ITokenModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8268012678953045721L;

	@Column
	private String name;

	@ElementCollection
	// SensorType.webKey
	private List<String> sensorKeys;

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenModel#getSensorKeys()
	 */
	@Override
	public List<String> getSensorKeys() {
		return sensorKeys;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenModel#setSensorKeys(java.util.List)
	 */
	@Override
	public void setSensorKeys(List<String> sensors) {
		this.sensorKeys = sensors;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TokenModel)) {
			return false;
		}
		TokenModel other = (TokenModel) obj;
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

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenModel#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITokenModel#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
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
}