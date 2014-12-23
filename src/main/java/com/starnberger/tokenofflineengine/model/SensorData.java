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
	private static final long serialVersionUID = 2780975611554420733L;

	@Column
	private Date timestamp;

	@Column
	// SensorType.webKey
	private String sensorType;

	@Column
	// Gateway.webKey
	private String gateway;

	@Column
	private double value1;

	@Column
	private double value2;

	@Column
	private double value3;

	@Column
	// Token.webKey
	private String token;

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

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#getTimestamp()
	 */
	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#setTimestamp(java.util.Date)
	 */
	@Override
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#getString()
	 */
	@Override
	public String getString() {
		return sensorType;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#setString(java.lang.String)
	 */
	@Override
	public void setString(String sensorType) {
		this.sensorType = sensorType;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#getGateway()
	 */
	@Override
	public String getGateway() {
		return gateway;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#setGateway(java.lang.String)
	 */
	@Override
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#getValue1()
	 */
	@Override
	public double getValue1() {
		return value1;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#setValue1(double)
	 */
	@Override
	public void setValue1(double value1) {
		this.value1 = value1;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#getValue2()
	 */
	@Override
	public double getValue2() {
		return value2;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#setValue2(double)
	 */
	@Override
	public void setValue2(double value2) {
		this.value2 = value2;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#getValue3()
	 */
	@Override
	public double getValue3() {
		return value3;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#setValue3(double)
	 */
	@Override
	public void setValue3(double value3) {
		this.value3 = value3;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#getToken()
	 */
	@Override
	public String getToken() {
		return token;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ISensorData#setToken(java.lang.String)
	 */
	@Override
	public void setToken(String token) {
		this.token = token;
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
}