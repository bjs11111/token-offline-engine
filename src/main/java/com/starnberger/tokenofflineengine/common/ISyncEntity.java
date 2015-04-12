package com.starnberger.tokenofflineengine.common;

import java.util.Date;

/**
 * @author Roman Kaufmann
 *
 */
public interface ISyncEntity extends ITokenEntity {

	/**
	 * @return the lastModified
	 */
	public abstract Date getLastModified();

	/**
	 * @param lastModified the lastModified to set
	 */
	public abstract void setLastModified(Date lastModified);

	/**
	 * @return the isDeleted
	 */
	public abstract boolean isDeleted();

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public abstract void setDeleted(boolean isDeleted);

	public abstract void setRemoteId(Long remoteId);

	public abstract Long getRemoteId();
	
	/**
	 * @return
	 */
	public abstract String toFilterString();

}