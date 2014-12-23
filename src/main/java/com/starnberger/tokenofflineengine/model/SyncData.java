/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Kaufmann
 *
 */
public class SyncData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3514984342099377219L;

	private List<Token> updatedTokens = new ArrayList<Token>();
	private List<TokenConfiguration> updatedTokenConfigurations = new ArrayList<TokenConfiguration>();
	private List<TokenModel> updatedTokenModels = new ArrayList<TokenModel>();
	private List<SensorConfiguration> updatedSensorConfigurations = new ArrayList<SensorConfiguration>();
	private List<SensorType> updatedSensorTypes = new ArrayList<SensorType>();
	private List<GatewayConfiguration> updatedGatewayConfigurations = new ArrayList<GatewayConfiguration>();
	private List<Task> updatedTasks = new ArrayList<Task>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SyncData [updatedTokens=" + updatedTokens + ", updatedTokenConfigurations="
				+ updatedTokenConfigurations + ", updatedTokenModels=" + updatedTokenModels
				+ ", updatedSensorConfigurations=" + updatedSensorConfigurations + ", updatedSensorTypes="
				+ updatedSensorTypes + ", updatedGatewayConfigurations=" + updatedGatewayConfigurations
				+ ", updatedTasks=" + updatedTasks + "]";
	}

	/**
	 * @return the updatedTokens
	 */
	public List<Token> getUpdatedTokens() {
		return updatedTokens;
	}

	/**
	 * @param updatedTokens
	 *            the updatedTokens to set
	 */
	public void setUpdatedTokens(List<Token> updatedTokens) {
		this.updatedTokens = updatedTokens;
	}

	/**
	 * @return the updatedTokenConfigurations
	 */
	public List<TokenConfiguration> getUpdatedTokenConfigurations() {
		return updatedTokenConfigurations;
	}

	/**
	 * @param updatedTokenConfigurations
	 *            the updatedTokenConfigurations to set
	 */
	public void setUpdatedTokenConfigurations(List<TokenConfiguration> updatedTokenConfigurations) {
		this.updatedTokenConfigurations = updatedTokenConfigurations;
	}

	/**
	 * @return the updatedTokenModels
	 */
	public List<TokenModel> getUpdatedTokenModels() {
		return updatedTokenModels;
	}

	/**
	 * @param updatedTokenModels
	 *            the updatedTokenModels to set
	 */
	public void setUpdatedTokenModels(List<TokenModel> updatedTokenModels) {
		this.updatedTokenModels = updatedTokenModels;
	}

	/**
	 * @return the updatedSensorConfigurations
	 */
	public List<SensorConfiguration> getUpdatedSensorConfigurations() {
		return updatedSensorConfigurations;
	}

	/**
	 * @param updatedSensorConfigurations
	 *            the updatedSensorConfigurations to set
	 */
	public void setUpdatedSensorConfigurations(List<SensorConfiguration> updatedSensorConfigurations) {
		this.updatedSensorConfigurations = updatedSensorConfigurations;
	}

	/**
	 * @return the updatedSensorTypes
	 */
	public List<SensorType> getUpdatedSensorTypes() {
		return updatedSensorTypes;
	}

	/**
	 * @param updatedSensorTypes
	 *            the updatedSensorTypes to set
	 */
	public void setUpdatedSensorTypes(List<SensorType> updatedSensorTypes) {
		this.updatedSensorTypes = updatedSensorTypes;
	}

	/**
	 * @return the updatedGatewayConfigurations
	 */
	public List<GatewayConfiguration> getUpdatedGatewayConfigurations() {
		return updatedGatewayConfigurations;
	}

	/**
	 * @param updatedGatewayConfigurations
	 *            the updatedGatewayConfigurations to set
	 */
	public void setUpdatedGatewayConfigurations(List<GatewayConfiguration> updatedGatewayConfigurations) {
		this.updatedGatewayConfigurations = updatedGatewayConfigurations;
	}

	/**
	 * @return the updatedTasks
	 */
	public List<Task> getUpdatedTasks() {
		return updatedTasks;
	}

	/**
	 * @param updatedTasks
	 *            the updatedTasks to set
	 */
	public void setUpdatedTasks(List<Task> updatedTasks) {
		this.updatedTasks = updatedTasks;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
