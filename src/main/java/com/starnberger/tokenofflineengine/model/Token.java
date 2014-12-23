package com.starnberger.tokenofflineengine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
		@NamedQuery(name = "Token.deleted", query = "SELECT t from Token t WHERE t.isDeleted = :isDeleted") })
public class Token extends SyncEntity implements IToken  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7760988891447305334L;
	
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
	// TokenModel.webKey
	private String model;

	@Column
	private Date lastSyncDate;

	@Column
	// User.webKey
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

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getMac()
	 */
	@Override
	public long getMac() {
		return mac;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setMac(long)
	 */
	@Override
	public void setMac(long mac) {
		this.mac = mac;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getUuid()
	 */
	@Override
	public String getUuid() {
		return uuid;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setUuid(java.lang.String)
	 */
	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getMinor()
	 */
	@Override
	public long getMinor() {
		return minor;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setMinor(long)
	 */
	@Override
	public void setMinor(long minor) {
		this.minor = minor;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getMajor()
	 */
	@Override
	public long getMajor() {
		return major;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setMajor(long)
	 */
	@Override
	public void setMajor(long major) {
		this.major = major;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#isNeedsConfigUpdate()
	 */
	@Override
	public boolean isNeedsConfigUpdate() {
		return needsConfigUpdate;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setNeedsConfigUpdate(boolean)
	 */
	@Override
	public void setNeedsConfigUpdate(boolean needsConfigUpdate) {
		this.needsConfigUpdate = needsConfigUpdate;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getModel()
	 */
	@Override
	public String getModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setModel(java.lang.String)
	 */
	@Override
	public void setModel(String model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getLastSyncDate()
	 */
	@Override
	public Date getLastSyncDate() {
		return lastSyncDate;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setLastSyncDate(java.util.Date)
	 */
	@Override
	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#getOwner()
	 */
	@Override
	public String getOwner() {
		return owner;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IToken#setOwner(java.lang.String)
	 */
	@Override
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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