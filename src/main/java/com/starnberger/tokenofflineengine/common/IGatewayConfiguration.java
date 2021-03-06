package com.starnberger.tokenofflineengine.common;

/**
 * @author Roman Kaufmann
 *
 */
public interface IGatewayConfiguration extends ISyncEntity {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract Long getPartnerKey();

	public abstract void setPartnerKey(Long partner);

	public abstract DebugLevel getDebugLevel();

	public abstract void setDebugLevel(DebugLevel debugLevel);

	public abstract int getSyncInterval();

	public abstract void setSyncInterval(int syncInterval);

	public abstract int getStatusUpdateInterval();

	public abstract void setStatusUpdateInterval(int statusUpdateInterval);

	public abstract int getLogUpdateInterval();

	public abstract void setLogUpdateInterval(int logUpdateInterval);

	public abstract void setUploadAlarmImmediately(boolean uploadAlarmImmediately);

	public abstract boolean isUploadAlarmImmediately();

	public abstract void setConnectionTimeout3G(int connectionTimeout3G);

	public abstract int getConnectionTimeout3G();

}