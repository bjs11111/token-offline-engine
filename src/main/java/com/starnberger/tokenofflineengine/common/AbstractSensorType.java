package com.starnberger.tokenofflineengine.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.SensorType;

@MappedSuperclass
public abstract class AbstractSensorType extends SyncEntity implements ISensorType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3532010611671709056L;
	@Column
	protected String description;
	@Column
	protected String unit;
	@Column
	protected int numberOfValues;
	@ElementCollection
	protected List<String> configValues = new ArrayList<String>();

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getUnit() {
		return unit;
	}

	@Override
	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public int getNumberOfValues() {
		return numberOfValues;
	}

	@Override
	public void setNumberOfValues(int numberOfValues) {
		this.numberOfValues = numberOfValues;
	}

	@Override
	public List<String> getConfigValues() {
		return configValues;
	}

	@Override
	public void setConfigValues(List<String> configValues) {
		this.configValues = configValues;
	}

	public AbstractSensorType() {
		super();
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorType)
		{
			SensorType token = (SensorType) source;
			setWebKey(token.getWebKey());
			setConfigValues(token.getConfigValues());
			setDescription(token.getDescription());
			setNumberOfValues(token.getNumberOfValues());
			setUnit(token.getUnit());
		}
	}

}