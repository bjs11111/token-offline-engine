package com.starnberger.tokenofflineengine.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.TokenModel;

@MappedSuperclass
public abstract class AbstractTokenModel extends SyncEntity implements ITokenModel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8268012678953045721L;
	@Column
	protected String name;
	@ElementCollection
	private List<String> sensorKeys;

	public AbstractTokenModel() {
		super();
	}

	@Override
	public List<String> getSensorKeys() {
		return sensorKeys;
	}

	@Override
	public void setSensorKeys(List<String> sensors) {
		this.sensorKeys = sensors;
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
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof TokenModel)
		{
			TokenModel token = (TokenModel) source;
			setWebKey(token.getWebKey());
			setName(token.getName());
			setSensorKeys(token.getSensorKeys());
		}
	}

}