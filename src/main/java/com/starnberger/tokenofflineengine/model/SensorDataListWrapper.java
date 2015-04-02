/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Kaufmann
 *
 *         This ListWrapper is used to wrap any list of objects. For security
 *         reasons this should always be used, when a REST service returns a
 *         list of objects.
 * @param <T>
 */
public class SensorDataListWrapper implements ISensorDataListWrapper {
	private List<SensorData> list;
	private List<Token> tokens = new ArrayList<Token>();
	private List<Gateway> gateways = new ArrayList<Gateway>();
	private List<SensorType> sensorTypes = new ArrayList<SensorType>();
	private List<TokenModel> models = new ArrayList<TokenModel>();

	/**
	 * Default constructor.
	 */
	public SensorDataListWrapper() {
		this.list = new ArrayList<SensorData>();
	}

	/**
	 * @param list
	 */
	public SensorDataListWrapper(List<SensorData> list) {
		this.list = list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.shared.dao.list.ISensorDataListWrapper#getList
	 * ()
	 */
	@Override
	public List<SensorData> getList() {
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.starnberger.tokenengine.shared.dao.list.ISensorDataListWrapper#setList
	 * (java.util.List)
	 */
	@Override
	public void setList(List<SensorData> list) {
		this.list = list;
	}

	/**
	 * @return the tokens
	 */
	public List<Token> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens
	 *            the tokens to set
	 */
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	/**
	 * @return the gateways
	 */
	public List<Gateway> getGateways() {
		return gateways;
	}

	/**
	 * @param gateways
	 *            the gateways to set
	 */
	public void setGateways(List<Gateway> gateways) {
		this.gateways = gateways;
	}

	/**
	 * @return the sensorTypes
	 */
	public List<SensorType> getSensorTypes() {
		return sensorTypes;
	}

	/**
	 * @param sensorTypes
	 *            the sensorTypes to set
	 */
	public void setSensorTypes(List<SensorType> sensorTypes) {
		this.sensorTypes = sensorTypes;
	}

	/**
	 * @return the models
	 */
	public List<TokenModel> getModels() {
		return models;
	}

	/**
	 * @param models
	 *            the models to set
	 */
	public void setModels(List<TokenModel> models) {
		this.models = models;
	}

}
