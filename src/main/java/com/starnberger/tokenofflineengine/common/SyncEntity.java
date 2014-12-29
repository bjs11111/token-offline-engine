/**
 * 
 */
package com.starnberger.tokenofflineengine.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.starnberger.tokenofflineengine.common.EntityState;
import com.starnberger.tokenofflineengine.common.ISyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
@MappedSuperclass
public abstract class SyncEntity extends TokenEntity implements ISyncEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3652993616169435806L;

	@Column
	protected Date lastModified = new Date();
	@Column
	protected boolean isDeleted = false;
	@Column
	protected EntityState state = EntityState.CREATED;

	@PreUpdate
	public void beforeUpdate() {
		state = EntityState.UPDATED;
		lastModified = new Date();
	}

	@PrePersist
	public void beforeInsert() {
		state = EntityState.CREATED;
		lastModified = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenengine.server.dao.ISyncEntity#getLastModified()
	 */
	@Override
	public Date getLastModified() {
		return lastModified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISyncEntity#setLastModified(java
	 * .util.Date)
	 */
	@Override
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starnberger.tokenengine.server.dao.ISyncEntity#isDeleted()
	 */
	@Override
	public boolean isDeleted() {
		return isDeleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISyncEntity#setDeleted(boolean)
	 */
	@Override
	public void setDeleted(boolean isDeleted) {
		this.state = EntityState.DELETED;
		this.isDeleted = isDeleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SyncEntity [lastModified=" + lastModified + ", isDeleted=" + isDeleted + ", state=" + state + "]";
	}

	/**
	 * @return the state
	 */
	public EntityState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(EntityState state) {
		this.state = state;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
