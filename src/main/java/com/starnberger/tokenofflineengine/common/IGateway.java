package com.starnberger.tokenofflineengine.common;

import java.util.Date;

/**
 * @author Roman Kaufmann
 *
 */
public interface IGateway extends ISyncEntity {

	/**
	 * @return
	 */
	public abstract String getName();

	/**
	 * @param name
	 */
	public abstract void setName(String name);

	/**
	 * @return
	 */
	public abstract boolean isNeedsFirmwareUpgrade();

	/**
	 * @param needsFirmwareUpgrade
	 */
	public abstract void setNeedsFirmwareUpgrade(boolean needsFirmwareUpgrade);

	/**
	 * @return
	 */
	public abstract boolean isNeedsConfigUpgrade();

	/**
	 * @param needsConfigUpgrade
	 */
	public abstract void setNeedsConfigUpgrade(boolean needsConfigUpgrade);

	/**
	 * @return
	 */
	public abstract String getFirmwareVersionKey();

	/**
	 * @param firmwareVersion
	 */
	public abstract void setFirmwareVersionKey(String firmwareVersion);

	/**
	 * @return
	 */
	public abstract String getUuid();

	/**
	 * @param uuid
	 */
	public abstract void setUuid(String uuid);

	/**
	 * @return
	 */
	public abstract String getGatewayConfigKey();

	/**
	 * @param gatewayConfig
	 */
	public abstract void setGatewayConfigKey(String gatewayConfig);

	/**
	 * @return the gatewayToken
	 */
	public abstract String getGatewayToken();

	/**
	 * @param gatewayToken
	 *            the gatewayToken to set
	 */
	public abstract void setGatewayToken(String gatewayToken);

	/**
	 * @return the associatedUser
	 */
	public abstract String getAssociatedUserKey();

	/**
	 * @param associatedUser
	 *            the associatedUser to set
	 */
	public abstract void setAssociatedUserKey(String associatedUser);

	/**
	 * @return the password
	 */
	public abstract String getPassword();

	/**
	 * @param password
	 *            the password to set
	 */
	public abstract void setPassword(String password);

	public abstract void setLastSync(Date lastSync);

	public abstract Date getLastSync();

}