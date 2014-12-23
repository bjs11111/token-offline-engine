package com.starnberger.tokenofflineengine.common;

import java.util.Date;

/**
 * @author Roman Kaufmann
 *
 */
public interface IToken extends ISyncEntity {

	public abstract long getMac();

	public abstract void setMac(long mac);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getUuid();

	public abstract void setUuid(String uuid);

	public abstract long getMinor();

	public abstract void setMinor(long minor);

	public abstract long getMajor();

	public abstract void setMajor(long major);

	public abstract boolean isNeedsConfigUpdate();

	public abstract void setNeedsConfigUpdate(boolean needsConfigUpdate);

	public abstract String getModel();

	public abstract void setModel(String model);

	public abstract Date getLastSyncDate();

	public abstract void setLastSyncDate(Date lastSyncDate);

	/**
	 * @return the owner
	 */
	public abstract String getOwner();

	/**
	 * @param owner
	 *            the owner to set
	 */
	public abstract void setOwner(String owner);

}