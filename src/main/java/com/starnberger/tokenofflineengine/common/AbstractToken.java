package com.starnberger.tokenofflineengine.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.Token;

@MappedSuperclass
public abstract class AbstractToken extends SyncEntity implements IToken   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7760988891447305334L;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public AbstractToken() {
		super();
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