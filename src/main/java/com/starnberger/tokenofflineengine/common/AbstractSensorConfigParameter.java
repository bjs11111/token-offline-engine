package com.starnberger.tokenofflineengine.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.SensorConfigParameter;

@MappedSuperclass
public abstract class AbstractSensorConfigParameter extends SyncEntity implements ISensorConfigParameter  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6324178668476664387L;
	@Column
	protected String key;
	@Column
	protected String description;
	@Column
	protected Class<?> type;
	@Column
	protected int sequence;

	public AbstractSensorConfigParameter() {
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Class<?> getType() {
		return type;
	}

	@Override
	public void setType(Class<?> type) {
		this.type = type;
	}

	@Override
	public int getSequence() {
		return sequence;
	}

	@Override
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorConfigParameter)
		{
			SensorConfigParameter token = (SensorConfigParameter) source;
			setWebKey(token.getWebKey());
			setDescription(token.getDescription());
			setKey(token.getKey());
			setSequence(token.getSequence());
			setType(token.getType());
		}
	}

}