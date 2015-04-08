package com.starnberger.tokenofflineengine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starnberger.tokenofflineengine.common.IGateway;
import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Gateway.findCpuId", query = "SELECT g from Gateway g WHERE g.uuid = :cpuid and g.deleted = FALSE"),
		@NamedQuery(name = "Gateway.findToken", query = "SELECT g from Gateway g WHERE g.gatewayToken = :gatewayToken and g.deleted = FALSE"),
		@NamedQuery(name = "Gateway.lastModified", query = "SELECT g from Gateway g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "Gateway.deleted", query = "SELECT g from Gateway g WHERE g.deleted = :isDeleted ORDER BY g.name"),
		@NamedQuery(name = "Gateway.findMe", query = "SELECT g FROM Gateway g"),
		@NamedQuery(name = "Gateway.findByOwner", query = "SELECT g FROM Gateway g WHERE g.deleted = false and g.associatedUserKey = :owner ORDER BY g.name"),
		@NamedQuery(name = "Gateway.findMyWebKey", query = "select s from Gateway s where s.remoteId = :webKey") })
public class Gateway extends SyncEntity implements IGateway {

	/**
	 * 
	 */
	private static final long serialVersionUID = -450125878269432651L;
	@Column
	protected String name;
	@Column
	protected String uuid;
	@Column
	protected boolean needsFirmwareUpgrade;
	@Column
	protected boolean needsConfigUpgrade;
	@Column
	protected Long firmwareVersionKey;
	@Column
	private Long gatewayConfigKey;
	@Column
	@JsonIgnore
	private String gatewayToken;
	@Column
	private Long associatedUserKey;
	@Column
	@JsonIgnore
	private String password;
	@Column
	private Date lastSync;
	@Column
	private Date lastUpload;
	@Column
	private String simCardNumber;
	@Column
	private Long assignedCustomerLocation;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Gateway)) {
			return false;
		}
		Gateway other = (Gateway) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		result += ", uuid: " + uuid;
		result += ", needsFirmwareUpgrade: " + needsFirmwareUpgrade;
		result += ", needsConfigUpgrade: " + needsConfigUpgrade;
		if (firmwareVersionKey != null)
			result += ", firmwareVersion: " + firmwareVersionKey;
		return result;
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
	public Long getFirmwareVersionKey() {
		return firmwareVersionKey;
	}

	@Override
	public void setFirmwareVersionKey(Long firmwareVersion) {
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
	public Long getGatewayConfigKey() {
		return this.gatewayConfigKey;
	}

	@Override
	public void setGatewayConfigKey(final Long gatewayConfig) {
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

	@Override
	public Long getAssociatedUserKey() {
		return associatedUserKey;
	}

	@Override
	public void setAssociatedUserKey(Long associatedUser) {
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

	/**
	 * @return the simCardNumber
	 */
	@Override
	public String getSimCardNumber() {
		return simCardNumber;
	}

	/**
	 * @param simCardNumber
	 *            the simCardNumber to set
	 */
	@Override
	public void setSimCardNumber(String simCardNumber) {
		this.simCardNumber = simCardNumber;
	}

	/**
	 * @return the lastUpload
	 */
	@Override
	public Date getLastUpload() {
		return lastUpload;
	}

	/**
	 * @param lastUpload the lastUpload to set
	 */
	@Override
	public void setLastUpload(Date lastUpload) {
		this.lastUpload = lastUpload;
	}

	/**
	 * @return the assignedCustomerLocation
	 */
	public Long getAssignedCustomerLocation() {
		return assignedCustomerLocation;
	}

	/**
	 * @param assignedCustomerLocation the assignedCustomerLocation to set
	 */
	public void setAssignedCustomerLocation(Long assignedCustomerLocation) {
		this.assignedCustomerLocation = assignedCustomerLocation;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof Gateway) {
			Gateway sourceGate = (Gateway) source;
			setAssociatedUserKey(sourceGate.getAssociatedUserKey());
			setFirmwareVersionKey(sourceGate.getFirmwareVersionKey());
			setGatewayConfigKey(sourceGate.getGatewayConfigKey());
			setName(sourceGate.getName());
			setUuid(sourceGate.getUuid());
			setNeedsConfigUpgrade(sourceGate.isNeedsConfigUpgrade());
			setNeedsFirmwareUpgrade(sourceGate.isNeedsFirmwareUpgrade());
			setSimCardNumber(sourceGate.getSimCardNumber());
			setLastSync(sourceGate.getLastSync());
			setLastUpload(sourceGate.getLastUpload());
			setAssignedCustomerLocation(sourceGate.getAssignedCustomerLocation());
		}
	}
}