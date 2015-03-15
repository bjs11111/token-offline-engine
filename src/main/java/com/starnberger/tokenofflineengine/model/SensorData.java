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
	@NamedQuery(name = "SensorData.findSinceLastSync", query = "SELECT s from SensorData s WHERE s.timestamp >= :lastSync")
})
public class SensorData extends TokenEntity implements ISensorData {

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

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (timestamp != null)
			result += ", timestamp: " + timestamp;
		if (sensorType != null)
			result += ", sensorType: " + sensorType;
		if (gateway != null)
			result += ", gateway: " + gateway;
		result += ", value1: " + value1;
		result += ", value2: " + value2;
		result += ", value3: " + value3;
		if (token != null)
			result += ", token: " + token;
		return result;
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

}