package com.starnberger.tokenofflineengine.common;

import java.util.Date;

/**
 * @author Roman Kaufmann
 *
 */
public interface ISensorData extends ITokenEntity {

	public abstract Date getTimestamp();

	public abstract void setTimestamp(Date timestamp);

	public abstract Long getSensorType();

	public abstract void setSensorType(Long sensorType);

	public abstract Long getGateway();

	public abstract void setGateway(Long gateway);

	public abstract double getValue1();

	public abstract void setValue1(double value1);

	public abstract double getValue2();

	public abstract void setValue2(double value2);

	public abstract double getValue3();

	public abstract void setValue3(double value3);

	public abstract Long getToken();

	public abstract void setToken(Long token);

}