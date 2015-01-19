package com.starnberger.tokenofflineengine.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.model.Gateway;

@MappedSuperclass
public abstract class AbstractGateway extends SyncEntity implements IGateway {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3226217939937580439L;
	@Column
	protected String name;
	@Column
	protected String uuid;
	@Column
	protected boolean needsFirmwareUpgrade;
	@Column
	protected boolean needsConfigUpgrade;
	@Column
	protected String firmwareVersionKey;
	@Column
	private String gatewayConfigKey;
	@Column
	@JsonIgnore
	private String gatewayToken;
	@Column
	private String associatedUserKey;
	@Column
	@JsonIgnore
	private String password;
	@Column
	private Date lastSync;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isNeedsFirmwareUpgrade() {
		return needsFirmwareUpgrade;
	}

	@Override
	public void setNeedsFirmwareUpgrade(boolean needsFirmwareUpgrade) {
		this.needsFirmwareUpgrade = needsFirmwareUpgrade;
	}

	@Override
	public boolean isNeedsConfigUpgrade() {
		return needsConfigUpgrade;
	}

	@Override
	public void setNeedsConfigUpgrade(boolean needsConfigUpgrade) {
		this.needsConfigUpgrade = needsConfigUpgrade;
	}

	@Override
	public String getFirmwareVersionKey() {
		return firmwareVersionKey;
	}

	@Override
	public void setFirmwareVersionKey(String firmwareVersion) {
		this.firmwareVersionKey = firmwareVersion;
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String getGatewayConfigKey() {
		return this.gatewayConfigKey;
	}

	@Override
	public void setGatewayConfigKey(final String gatewayConfig) {
		this.gatewayConfigKey = gatewayConfig;
	}

	@Override
	public String getGatewayToken() {
		return gatewayToken;
	}

	@Override
	public void setGatewayToken(String gatewayToken) {
		this.gatewayToken = gatewayToken;
	}

	public AbstractGateway() {
		super();
	}

	@Override
	public String getAssociatedUserKey() {
		return associatedUserKey;
	}

	@Override
	public void setAssociatedUserKey(String associatedUser) {
		this.associatedUserKey = associatedUser;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the lastSync
	 */
	@Override
	public Date getLastSync() {
		return lastSync;
	}

	/**
	 * @param lastSync
	 *            the lastSync to set
	 */
	@Override
	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof Gateway)
		{
			Gateway sourceGate = (Gateway) source;
			setAssociatedUserKey(sourceGate.getAssociatedUserKey());
			setFirmwareVersionKey(sourceGate.getFirmwareVersionKey());
			setGatewayConfigKey(sourceGate.getGatewayConfigKey());
			setName(sourceGate.getName());
			setUuid(sourceGate.getUuid());
			setWebKey(sourceGate.getWebKey());
			setNeedsConfigUpgrade(sourceGate.isNeedsConfigUpgrade());
			setNeedsFirmwareUpgrade(sourceGate.isNeedsFirmwareUpgrade());
		}
	}

}