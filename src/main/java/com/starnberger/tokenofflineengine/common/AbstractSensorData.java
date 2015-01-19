package com.starnberger.tokenofflineengine.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractSensorData extends TokenEntity implements ISensorData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2780975611554420733L;
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

	public AbstractSensorData() {
		super();
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