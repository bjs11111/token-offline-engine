package com.starnberger.tokenofflineengine.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.GatewayVersion;

@MappedSuperclass
public abstract class AbstractGatewayVersion extends SyncEntity implements IGatewayVersion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -693090586746867736L;
	@Column
	protected String name;
	@Column
	protected String description;
	@Column
	protected String file;

	public AbstractGatewayVersion() {
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getFile() {
		return file;
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof GatewayVersion)
		{
			GatewayVersion token = (GatewayVersion) source;
			setWebKey(token.getWebKey());
			setDescription(token.getDescription());
			setFile(token.getFile());
			setName(token.getName());
		}
	}

}