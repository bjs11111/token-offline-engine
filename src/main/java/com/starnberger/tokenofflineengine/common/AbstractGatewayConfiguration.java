package com.starnberger.tokenofflineengine.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.GatewayConfiguration;

@MappedSuperclass
public abstract class AbstractGatewayConfiguration extends SyncEntity implements IGatewayConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3017416369270290673L;
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
	private String partnerKey;

	public AbstractGatewayConfiguration() {
		super();
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
	public String getPartnerKey() {
		return this.partnerKey;
	}

	@Override
	public void setPartnerKey(final String partner) {
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
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof GatewayConfiguration) {
			GatewayConfiguration token = (GatewayConfiguration) source;
			setWebKey(token.getWebKey());
			setName(token.getName());
			setDebugLevel(token.getDebugLevel());
			setLogUpdateInterval(token.getLogUpdateInterval());
			setPartnerKey(token.getPartnerKey());
			setStatusUpdateInterval(token.getStatusUpdateInterval());
			setSyncInterval(token.getSyncInterval());
		}
	}

}