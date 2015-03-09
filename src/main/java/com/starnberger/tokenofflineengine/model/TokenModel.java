package com.starnberger.tokenofflineengine.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ITokenEntity;
import com.starnberger.tokenofflineengine.common.ITokenModel;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "TokenModel.lastModified", query = "SELECT g from TokenModel g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "TokenModel.deleted", query = "SELECT g from TokenModel g WHERE g.deleted = :isDeleted  ORDER BY g.name"),
		@NamedQuery(name = "TokenModel.findMyWebKey", query = "select s from TokenModel s where s.id = :webKey") })
public class TokenModel extends SyncEntity implements ITokenModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006668345961642825L;
	@Column
	protected String name;
	@Basic
	private List<Long> sensorKeys;
	@Column
	private List<String> sensorPosition;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenModel [name=" + name + ", sensorKeys=" + sensorKeys + ", sensorPosition=" + sensorPosition + "]";
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
	public List<Long> getSensorKeys() {
		return sensorKeys;
	}

	@Override
	public void setSensorKeys(List<Long> sensors) {
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

	/**
	 * @return the sensorPosition
	 */
	@Override
	public List<String> getSensorPositions() {
		return sensorPosition;
	}

	/**
	 * @param sensorPosition
	 *            the sensorPosition to set
	 */
	@Override
	public void setSensorPositions(List<String> sensorPosition) {
		this.sensorPosition = sensorPosition;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof TokenModel) {
			ITokenModel token = (ITokenModel) source;
			// setWebKey(token.getWebKey());
			setId(token.getId());
			setName(token.getName());
			setSensorKeys(token.getSensorKeys());
		}
	}
}