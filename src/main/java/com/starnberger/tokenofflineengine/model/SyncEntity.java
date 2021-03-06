/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

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
	@Column(name = "deleted")
	protected boolean deleted = false;
	@Column
	protected EntityState state = EntityState.CREATED;
	@Column
	protected Long remoteId = null;

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
		return deleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.server.dao.ISyncEntity#setDeleted(boolean)
	 */
	@Override
	public void setDeleted(boolean isDeleted) {
		if (isDeleted)
			this.state = EntityState.DELETED;
		this.deleted = isDeleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SyncEntity [lastModified=" + lastModified + ", isDeleted=" + deleted + ", state=" + state + "]";
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

	/**
	 * @return the remoteId
	 */
	@Override
	public Long getRemoteId() {
		return remoteId;
	}

	/**
	 * @param remoteId
	 *            the remoteId to set
	 */
	@Override
	public void setRemoteId(Long remoteId) {
		this.remoteId = remoteId;
	}

}
