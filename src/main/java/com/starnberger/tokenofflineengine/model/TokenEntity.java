package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.starnberger.tokenofflineengine.common.ITokenEntity;

@MappedSuperclass
public abstract class TokenEntity implements Serializable, ITokenEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5249132126930352893L;
	@Version
	@Column(name = "version")
	protected int version;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenengine.server.dao.ITokenEntity#getVersion()
	 */
	@Override
	public int getVersion() {
		return version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenengine.server.dao.ITokenEntity#setVersion(int)
	 */
	@Override
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TokenEntity other = (TokenEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenEntity [version=" + version + ", id=" + id + "]";
	}

}