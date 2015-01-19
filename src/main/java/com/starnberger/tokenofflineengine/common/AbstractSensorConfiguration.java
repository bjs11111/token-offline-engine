package com.starnberger.tokenofflineengine.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.SensorConfiguration;

@MappedSuperclass
public abstract class AbstractSensorConfiguration extends SyncEntity implements ISensorConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6382431230433111245L;
	@Column
	private String ownerKey;
	@Column
	private String sensorTypeKey;
	@ElementCollection
	private List<String> configValueKeys = new ArrayList<String>();

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getOwnerKey() {
		return ownerKey;
	}

	@Override
	public void setOwnerKey(String owner) {
		this.ownerKey = owner;
	}

	@Override
	public String getSensorTypeKey() {
		return sensorTypeKey;
	}

	@Override
	public void setSensorTypeKey(String sensorType) {
		this.sensorTypeKey = sensorType;
	}

	@Override
	public List<String> getConfigValueKeys() {
		return configValueKeys;
	}

	@Override
	public void setConfigValueKeys(List<String> configValue) {
		this.configValueKeys = configValue;
	}

	public AbstractSensorConfiguration() {
		super();
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfiguration)
		{
			SensorConfiguration token = (SensorConfiguration) source;
			setWebKey(token.getWebKey());
			setConfigValueKeys(token.getConfigValueKeys());
			setOwnerKey(token.getOwnerKey());
			setSensorTypeKey(token.getSensorTypeKey());
		}
	}

}