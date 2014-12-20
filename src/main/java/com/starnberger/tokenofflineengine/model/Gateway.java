package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Gateway.findMe", query = "SELECT g FROM Gateway g") })
public class Gateway implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7244940530167510973L;
	@Id
	@GeneratedValue(generator = "g-uuid")
	@GenericGenerator(name = "g-uuid", strategy = "uuid2")
	@JsonIgnore
	private String id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String name;

	@Column
	private String uuid;

	@Column
	private boolean needsFirmwareUpgrade;

	@Column
	private boolean needsConfigUpgrade;

	@Column
	@JsonIgnore
	private String firmwareVersionKey;

	@ManyToOne
	@JsonIgnore
	private GatewayConfiguration gatewayConfig;
	@Column
	@JsonIgnore
	private String gatewayToken;
	@Column
	@JsonIgnore
	private Date lastSync;
	@Column
	@JsonIgnore
	private String password;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

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

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public boolean isNeedsFirmwareUpgrade() {
		return needsFirmwareUpgrade;
	}

	/**
	 * @param needsFirmwareUpgrade
	 */
	public void setNeedsFirmwareUpgrade(boolean needsFirmwareUpgrade) {
		this.needsFirmwareUpgrade = needsFirmwareUpgrade;
	}

	/**
	 * @return
	 */
	public boolean isNeedsConfigUpgrade() {
		return needsConfigUpgrade;
	}

	/**
	 * @param needsConfigUpgrade
	 */
	public void setNeedsConfigUpgrade(boolean needsConfigUpgrade) {
		this.needsConfigUpgrade = needsConfigUpgrade;
	}

	/**
	 * @return
	 */
	public String getFirmwareVersion() {
		return firmwareVersionKey;
	}

	/**
	 * @param firmwareVersion
	 */
	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersionKey = firmwareVersion;
	}

	/**
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	/**
	 * @return
	 */
	public GatewayConfiguration getGatewayConfig() {
		return this.gatewayConfig;
	}

	/**
	 * @param gatewayConfig
	 */
	public void setGatewayConfig(final GatewayConfiguration gatewayConfig) {
		this.gatewayConfig = gatewayConfig;
	}

	/**
	 * @return the firmwareVersionKey
	 */
	public String getFirmwareVersionKey() {
		return firmwareVersionKey;
	}

	/**
	 * @param firmwareVersionKey
	 *            the firmwareVersionKey to set
	 */
	public void setFirmwareVersionKey(String firmwareVersionKey) {
		this.firmwareVersionKey = firmwareVersionKey;
	}

	/**
	 * @return the gatewayToken
	 */
	public String getGatewayToken() {
		return gatewayToken;
	}

	/**
	 * @param gatewayToken
	 *            the gatewayToken to set
	 */
	public void setGatewayToken(String gatewayToken) {
		this.gatewayToken = gatewayToken;
	}

	/**
	 * @return the lastSync
	 */
	public Date getLastSync() {
		return lastSync;
	}

	/**
	 * @param lastSync
	 *            the lastSync to set
	 */
	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}