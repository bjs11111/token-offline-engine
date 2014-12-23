package com.starnberger.tokenofflineengine.common;

/**
 * @author Roman Kaufmann
 *
 */
public interface IGatewayConfiguration extends ISyncEntity {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getPartnerKey();

	public abstract void setPartnerKey(String partner);

	public abstract DebugLevel getDebugLevel();

	public abstract void setDebugLevel(DebugLevel debugLevel);

	public abstract int getSyncInterval();

	public abstract void setSyncInterval(int syncInterval);

	public abstract int getStatusUpdateInterval();

	public abstract void setStatusUpdateInterval(int statusUpdateInterval);

	public abstract int getLogUpdateInterval();

	public abstract void setLogUpdateInterval(int logUpdateInterval);

}