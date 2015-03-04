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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenengine.server.dao.ITokenEntity#getWebKey()
	 */
	/*@Override
	public String getWebKey() {
		if (webKey == null) {
			calculateWebKey();
		}
		return webKey;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ITokenEntity#setWebKey(java.lang
	 * .String)
	 */
	/*@Override
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}*/

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		//result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + version;
		//result = prime * result + ((webKey == null) ? 0 : webKey.hashCode());
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
		/*if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;*/
		if (version != other.version)
			return false;
		/*if (webKey == null) {
			if (other.webKey != null)
				return false;
		} else if (!webKey.equals(other.webKey))
			return false;*/
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//return "TokenEntity [id=" + id + ", version=" + version + ", webKey=" + webKey + "]";
		//return "TokenEntity [version=" + version + ", webKey=" + webKey + "]";
		return "TokenEntity [version=" + version + ", id=" + id + "]";
	}
	
	/*@PostPersist
	public void beforePersist() {
		calculateWebKey();
	}*/
	
	
	/**
	 * Default implementation does not calculate a webkey. Can be overriden in child classes. 
	 */
	/*public void calculateWebKey() {
		if (id != null)
			setWebKey(String.valueOf(getId()));
	}*/

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