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

	public abstract Double getValue1();

	public abstract void setValue1(Double value1);

	public abstract Double getValue2();

	public abstract void setValue2(Double value2);

	public abstract Double getValue3();

	public abstract void setValue3(Double value3);

	public abstract Long getToken();

	public abstract void setToken(Long token);

	public abstract void setAlarm(boolean isAlarm);

	public abstract boolean isAlarm();

}