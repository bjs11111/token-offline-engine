/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

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

	@PreUpdate
	@PrePersist
	public void beforeUpdate() {
		lastModified = new Date();
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISyncEntity#getLastModified()
	 */
	@Override
	public Date getLastModified() {
		return lastModified;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISyncEntity#setLastModified(java.util.Date)
	 */
	@Override
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISyncEntity#isDeleted()
	 */
	@Override
	public boolean isDeleted() {
		return isDeleted;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISyncEntity#setDeleted(boolean)
	 */
	@Override
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SyncEntity [lastModified=" + lastModified + ", isDeleted=" + isDeleted + "]";
	}
	
}
