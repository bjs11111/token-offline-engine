package com.starnberger.tokenofflineengine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.starnberger.tokenofflineengine.common.ISensorData;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
public class SensorData extends TokenEntity implements ISensorData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751327066679701819L;
	@Column
	protected Date timestamp;
	@Column
	protected String sensorType;
	@Column
	protected String gateway;
	@Column
	protected double value1;
	@Column
	protected double value2;
	@Column
	protected double value3;
	@Column
	protected String token;
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
	public String getString() {
		return sensorType;
	}

	@Override
	public void setString(String sensorType) {
		this.sensorType = sensorType;
	}

	@Override
	public String getGateway() {
		return gateway;
	}

	@Override
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	@Override
	public double getValue1() {
		return value1;
	}

	@Override
	public void setValue1(double value1) {
		this.value1 = value1;
	}

	@Override
	public double getValue2() {
		return value2;
	}

	@Override
	public void setValue2(double value2) {
		this.value2 = value2;
	}

	@Override
	public double getValue3() {
		return value3;
	}

	@Override
	public void setValue3(double value3) {
		this.value3 = value3;
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}
}