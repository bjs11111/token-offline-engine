package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Token implements Serializable {

	@Id
	@GeneratedValue(generator = "t-uuid")
	@GenericGenerator(name = "t-uuid", strategy = "uuid2")
	private String id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private long mac;

	@Column
	private String name;

	@Column
	private String uuid;

	@Column
	private long minor;

	@Column
	private long major;

	@Column
	private boolean needsConfigUpdate;

	@Column
	private TokenModel model;

	@Column
	private Date lastSyncDate;

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
		if (!(obj instanceof Token)) {
			return false;
		}
		Token other = (Token) obj;
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

	public long getMac() {
		return mac;
	}

	public void setMac(long mac) {
		this.mac = mac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getMinor() {
		return minor;
	}

	public void setMinor(long minor) {
		this.minor = minor;
	}

	public long getMajor() {
		return major;
	}

	public void setMajor(long major) {
		this.major = major;
	}

	public boolean isNeedsConfigUpdate() {
		return needsConfigUpdate;
	}

	public void setNeedsConfigUpdate(boolean needsConfigUpdate) {
		this.needsConfigUpdate = needsConfigUpdate;
	}

	public TokenModel getModel() {
		return model;
	}

	public void setModel(TokenModel model) {
		this.model = model;
	}

	public Date getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		result += ", mac: " + mac;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		if (uuid != null && !uuid.trim().isEmpty())
			result += ", uuid: " + uuid;
		result += ", minor: " + minor;
		result += ", major: " + major;
		result += ", needsConfigUpdate: " + needsConfigUpdate;
		if (model != null)
			result += ", model: " + model;
		if (lastSyncDate != null)
			result += ", lastSyncDate: " + lastSyncDate;
		return result;
	}
}