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
	public abstract Long getFirmwareVersionKey();

	/**
	 * @param firmwareVersion
	 */
	public abstract void setFirmwareVersionKey(Long firmwareVersion);

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
	public abstract Long getGatewayConfigKey();

	/**
	 * @param gatewayConfig
	 */
	public abstract void setGatewayConfigKey(Long gatewayConfig);

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
	public abstract Long getAssociatedUserKey();

	/**
	 * @param associatedUser
	 *            the associatedUser to set
	 */
	public abstract void setAssociatedUserKey(Long associatedUser);

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

	public abstract void setSimCardNumber(String simCardNumber);

	public abstract String getSimCardNumber();

	public abstract void setLastUpload(Date lastUpload);

	public abstract Date getLastUpload();

}