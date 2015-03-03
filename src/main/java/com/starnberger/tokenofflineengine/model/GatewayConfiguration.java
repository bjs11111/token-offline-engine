package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.DebugLevel;
import com.starnberger.tokenofflineengine.common.IGatewayConfiguration;
import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "GatewayConfiguration.lastModified", query = "SELECT g from GatewayConfiguration g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "GatewayConfiguration.deleted", query = "SELECT g from GatewayConfiguration g WHERE g.deleted = :isDeleted"),
		@NamedQuery(name = "GatewayConfiguration.findMyWebKey", query = "select s from GatewayConfiguration s where s.id = :webKey") })
public class GatewayConfiguration extends SyncEntity implements IGatewayConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5393538827784408703L;
	@Column
	protected String name;
	@Column
	private DebugLevel debugLevel;
	@Column
	private int syncInterval;
	@Column
	private int statusUpdateInterval;
	@Column
	private int logUpdateInterval;
	@Column
	private Long partnerKey;
	@Column
	private boolean uploadAlarmImmediately;
	
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getPartnerKey() {
		return this.partnerKey;
	}

	@Override
	public void setPartnerKey(final Long partner) {
		this.partnerKey = partner;
	}

	@Override
	public DebugLevel getDebugLevel() {
		return debugLevel;
	}

	@Override
	public void setDebugLevel(DebugLevel debugLevel) {
		this.debugLevel = debugLevel;
	}

	@Override
	public int getSyncInterval() {
		return syncInterval;
	}

	@Override
	public void setSyncInterval(int syncInterval) {
		this.syncInterval = syncInterval;
	}

	@Override
	public int getStatusUpdateInterval() {
		return statusUpdateInterval;
	}

	@Override
	public void setStatusUpdateInterval(int statusUpdateInterval) {
		this.statusUpdateInterval = statusUpdateInterval;
	}

	@Override
	public int getLogUpdateInterval() {
		return logUpdateInterval;
	}

	@Override
	public void setLogUpdateInterval(int logUpdateInterval) {
		this.logUpdateInterval = logUpdateInterval;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof GatewayConfiguration) {
			GatewayConfiguration token = (GatewayConfiguration) source;
			//setWebKey(token.getWebKey());
			setId(token.getId());
			setName(token.getName());
			setDebugLevel(token.getDebugLevel());
			setLogUpdateInterval(token.getLogUpdateInterval());
			setPartnerKey(token.getPartnerKey());
			setStatusUpdateInterval(token.getStatusUpdateInterval());
			setSyncInterval(token.getSyncInterval());
			setUploadAlarmImmediately(token.isUploadAlarmImmediately());
		}
	}

	/**
	 * @return the uploadAlarmImmediately
	 */
	@Override
	public boolean isUploadAlarmImmediately() {
		return uploadAlarmImmediately;
	}

	/**
	 * @param uploadAlarmImmediately the uploadAlarmImmediately to set
	 */
	@Override
	public void setUploadAlarmImmediately(boolean uploadAlarmImmediately) {
		this.uploadAlarmImmediately = uploadAlarmImmediately;
	}

}