package com.starnberger.tokenofflineengine.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;

@MappedSuperclass
public abstract class AbstractSensorConfigValue extends SyncEntity implements ISensorConfigValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2454959884417555325L;
	@Column
	protected String key;
	@Column
	protected Serializable value;

	public AbstractSensorConfigValue() {
		super();
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public Serializable getValue() {
		return value;
	}

	@Override
	public void setValue(Serializable value) {
		this.value = value;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfigValue) {
			SensorConfigValue token = (SensorConfigValue) source;
			setWebKey(token.getWebKey());
			setKey(token.getKey());
			setValue(token.getValue());
		}
	}

}