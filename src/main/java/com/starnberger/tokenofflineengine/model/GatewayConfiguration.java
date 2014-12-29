package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.DebugLevel;
import com.starnberger.tokenofflineengine.common.IGatewayConfiguration;
import com.starnberger.tokenofflineengine.common.SyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "GatewayConfiguration.lastModified", query = "SELECT g from GatewayConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "GatewayConfiguration.deleted", query = "SELECT g from GatewayConfiguration g WHERE g.isDeleted = :isDeleted") })
public class GatewayConfiguration extends SyncEntity implements IGatewayConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3017416369270290673L;

	@Column
	private String name;
	@Column
	private DebugLevel debugLevel;
	@Column
	private int syncInterval;
	@Column
	private int statusUpdateInterval;
	@Column
	private int logUpdateInterval;

	@Column
	// Partner.webKey
	private String partnerKey;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GatewayConfiguration)) {
			return false;
		}
		GatewayConfiguration other = (GatewayConfiguration) obj;
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
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		return result;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#getPartnerKey()
	 */
	@Override
	public String getPartnerKey() {
		return this.partnerKey;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#setPartnerKey(java.lang.String)
	 */
	@Override
	public void setPartnerKey(final String partner) {
		this.partnerKey = partner;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#getDebugLevel()
	 */
	@Override
	public DebugLevel getDebugLevel() {
		return debugLevel;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#setDebugLevel(com.starnberger.tokenengine.server.dao.DebugLevel)
	 */
	@Override
	public void setDebugLevel(DebugLevel debugLevel) {
		this.debugLevel = debugLevel;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#getSyncInterval()
	 */
	@Override
	public int getSyncInterval() {
		return syncInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#setSyncInterval(int)
	 */
	@Override
	public void setSyncInterval(int syncInterval) {
		this.syncInterval = syncInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#getStatusUpdateInterval()
	 */
	@Override
	public int getStatusUpdateInterval() {
		return statusUpdateInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#setStatusUpdateInterval(int)
	 */
	@Override
	public void setStatusUpdateInterval(int statusUpdateInterval) {
		this.statusUpdateInterval = statusUpdateInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#getLogUpdateInterval()
	 */
	@Override
	public int getLogUpdateInterval() {
		return logUpdateInterval;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.IGatewayConfiguration#setLogUpdateInterval(int)
	 */
	@Override
	public void setLogUpdateInterval(int logUpdateInterval) {
		this.logUpdateInterval = logUpdateInterval;
	}

}