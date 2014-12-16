package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
public class Gateway implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7244940530167510973L;
	@Id
	@GeneratedValue(generator = "g-uuid")
	@GenericGenerator(name = "g-uuid", strategy = "uuid2")
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
	private String firmwareVersionKey;

	@ManyToOne
	private GatewayConfiguration gatewayConfig;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNeedsFirmwareUpgrade() {
		return needsFirmwareUpgrade;
	}

	public void setNeedsFirmwareUpgrade(boolean needsFirmwareUpgrade) {
		this.needsFirmwareUpgrade = needsFirmwareUpgrade;
	}

	public boolean isNeedsConfigUpgrade() {
		return needsConfigUpgrade;
	}

	public void setNeedsConfigUpgrade(boolean needsConfigUpgrade) {
		this.needsConfigUpgrade = needsConfigUpgrade;
	}

	public String getFirmwareVersion() {
		return firmwareVersionKey;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersionKey = firmwareVersion;
	}

	public String getUuid() {
		return uuid;
	}

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

	public GatewayConfiguration getGatewayConfig() {
		return this.gatewayConfig;
	}

	public void setGatewayConfig(final GatewayConfiguration gatewayConfig) {
		this.gatewayConfig = gatewayConfig;
	}
}