package com.starnberger.tokenofflineengine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.IToken;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Token.ownedBy", query = "SELECT t from Token t WHERE t.owner = :owner and t.isDeleted = FALSE"),
		@NamedQuery(name = "Token.lastModifiedByOwner", query = "SELECT t from Token t WHERE t.owner = :owner and t.lastModified > :lastSyncDate"),
		@NamedQuery(name = "Token.lastModified", query = "SELECT t from Token t WHERE t.lastModified > :lastSyncDate"),
		@NamedQuery(name = "Token.deleted", query = "SELECT t from Token t WHERE t.isDeleted = :isDeleted"),
		@NamedQuery(name = "Token.findMyWebKey", query = "select s from Token s where s.webKey = :webKey") })
public class Token extends SyncEntity implements IToken   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298223670658279856L;
	@Column
	protected long mac;
	@Column
	protected String name;
	@Column
	protected String uuid;
	@Column
	protected long minor;
	@Column
	protected long major;
	@Column
	protected boolean needsConfigUpdate;
	@Column
	protected String model;
	@Column
	protected Date lastSyncDate;
	@Column
	private String owner;
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public long getMac() {
		return mac;
	}

	@Override
	public void setMac(long mac) {
		this.mac = mac;
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
	public String getUuid() {
		return uuid;
	}

	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public long getMinor() {
		return minor;
	}

	@Override
	public void setMinor(long minor) {
		this.minor = minor;
	}

	@Override
	public long getMajor() {
		return major;
	}

	@Override
	public void setMajor(long major) {
		this.major = major;
	}

	@Override
	public boolean isNeedsConfigUpdate() {
		return needsConfigUpdate;
	}

	@Override
	public void setNeedsConfigUpdate(boolean needsConfigUpdate) {
		this.needsConfigUpdate = needsConfigUpdate;
	}

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public Date getLastSyncDate() {
		return lastSyncDate;
	}

	@Override
	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	@Override
	public String getOwner() {
		return owner;
	}

	@Override
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof Token)
		{
			Token token = (Token) source;
			setWebKey(token.getWebKey());
			setName(token.getName());
			setLastSyncDate(token.getLastSyncDate());
			setMac(token.getMac());
			setMajor(token.getMajor());
			setMinor(token.getMinor());
			setModel(token.getModel());
			setNeedsConfigUpdate(token.isNeedsConfigUpdate());
			setOwner(token.getOwner());
			setUuid(token.getUuid());
		}
	}
}