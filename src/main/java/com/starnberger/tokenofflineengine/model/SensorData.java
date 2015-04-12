package com.starnberger.tokenofflineengine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISensorData;
import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "SensorData.findSinceLastSync", query = "SELECT s from SensorData s WHERE s.timestamp >= :lastSync"),
		@NamedQuery(name = "SensorData.lastModified", query = "SELECT s from SensorData s WHERE s.lastModified > :lastSyncDate"),
		@NamedQuery(name = "SensorData.deleteUploadedData", query = "DELETE from SensorData s WHERE s.lastModified < :deleteUntil"),
		@NamedQuery(name = "SensorData.findLastSyncForGateway", query = "SELECT s from SensorData s WHERE s.gateway = :gateway ORDER BY s.timestamp DESC"),
		@NamedQuery(name = "SensorData.findLastSyncForToken", query = "SELECT s from SensorData s WHERE s.token = :token ORDER BY s.timestamp DESC"),
		@NamedQuery(name = "SensorData.findDataBySensorTypes", query = "SELECT s from SensorData s WHERE s.sensorType in (:typeList) ORDER BY s.timestamp DESC"),
		@NamedQuery(name = "SensorData.listDataDescending", query = "SELECT s from SensorData s ORDER BY s.timestamp DESC"),
		@NamedQuery(name = "SensorData.listDataForTokenByType", query = "SELECT s from SensorData s WHERE s.token = :token ORDER BY s.timestamp DESC, s.sensorType ASC"),
		@NamedQuery(name = "SensorData.listForChar", query = "SELECT s from SensorData s WHERE s.token = :token and s.sensorType = :sensorType and s.timestamp >= :fromRange and s.timestamp <= :toRange ORDER BY s.timestamp ASC") })
public class SensorData extends SyncEntity implements ISensorData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751327066679701819L;
	@Column
	protected Date timestamp;
	@Column
	protected Long sensorType;
	@Column
	protected Long gateway;
	@Column
	protected Double value1;
	@Column
	protected Double value2;
	@Column
	protected Double value3;
	// Token.id
	@Column
	protected Long token;
	@Column
	protected boolean isAlarm;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SensorData)) {
			return false;
		}
		SensorData other = (SensorData) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SensorData [timestamp=" + timestamp + ", sensorType=" + sensorType + ", gateway=" + gateway
				+ ", value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + ", token=" + token
				+ ", isAlarm=" + isAlarm + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public Long getSensorType() {
		return sensorType;
	}

	@Override
	public void setSensorType(Long sensorType) {
		this.sensorType = sensorType;
	}

	@Override
	public Long getGateway() {
		return gateway;
	}

	@Override
	public void setGateway(Long gateway) {
		this.gateway = gateway;
	}

	@Override
	public Double getValue1() {
		return value1;
	}

	@Override
	public void setValue1(Double value1) {
		this.value1 = value1;
	}

	@Override
	public Double getValue2() {
		return value2;
	}

	@Override
	public void setValue2(Double value2) {
		this.value2 = value2;
	}

	@Override
	public Double getValue3() {
		return value3;
	}

	@Override
	public void setValue3(Double value3) {
		this.value3 = value3;
	}

	@Override
	public Long getToken() {
		return token;
	}

	@Override
	public void setToken(Long token) {
		this.token = token;
	}

	@Override
	public void copyValues(ITokenEntity source) {
		if (source == null)
			return;
		if (source instanceof SensorData) {
			SensorData sourceSensorData = (SensorData) source;
			setGateway(sourceSensorData.getGateway());
			setSensorType(sourceSensorData.getSensorType());
			setTimestamp(sourceSensorData.getTimestamp());
			setToken(sourceSensorData.getToken());
			setValue1(sourceSensorData.getValue1());
			setValue2(sourceSensorData.getValue2());
			setValue3(sourceSensorData.getValue3());
			setAlarm(sourceSensorData.isAlarm());
		}
	}

	/**
	 * @return the isAlarm
	 */
	@Override
	public boolean isAlarm() {
		return isAlarm;
	}

	/**
	 * @param isAlarm
	 *            the isAlarm to set
	 */
	@Override
	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

	@Override
	public String toFilterString() {
		return timestamp.toString() + " " + String.valueOf(value1) + " " + String.valueOf(value2) + " "
				+ String.valueOf(value3) + " " + String.valueOf(isAlarm);
	}

}