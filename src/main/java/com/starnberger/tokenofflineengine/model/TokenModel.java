package com.starnberger.tokenofflineengine.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.ITokenModel;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TokenModel.lastModified", query = "SELECT g from TokenModel g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "TokenModel.deleted", query = "SELECT g from TokenModel g WHERE g.isDeleted = :isDeleted"),
		@NamedQuery(name = "TokenModel.findMyWebKey", query = "select s from TokenModel s where s.webKey = :webKey") })
public class TokenModel extends SyncEntity implements ITokenModel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006668345961642825L;
	@Column
	protected String name;
	@Basic
	private List<String> sensorKeys;
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

	@Override
	public List<String> getSensorKeys() {
		return sensorKeys;
	}

	@Override
	public void setSensorKeys(List<String> sensors) {
		this.sensorKeys = sensors;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof TokenModel)
		{
			TokenModel token = (TokenModel) source;
			setWebKey(token.getWebKey());
			setName(token.getName());
			setSensorKeys(token.getSensorKeys());
		}
	}
}