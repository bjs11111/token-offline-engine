package com.starnberger.tokenofflineengine.common;

import java.util.Date;

/**
 * @author Roman Kaufmann
 *
 */
public interface ISensorData extends ITokenEntity {

	public abstract Date getTimestamp();

	public abstract void setTimestamp(Date timestamp);

	public abstract String getString();

	public abstract void setString(String sensorType);

	public abstract String getGateway();

	public abstract void setGateway(String gateway);

	public abstract double getValue1();

	public abstract void setValue1(double value1);

	public abstract double getValue2();

	public abstract void setValue2(double value2);

	public abstract double getValue3();

	public abstract void setValue3(double value3);

	public abstract String getToken();

	public abstract void setToken(String token);

}