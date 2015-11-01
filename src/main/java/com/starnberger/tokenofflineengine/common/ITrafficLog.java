package com.starnberger.tokenofflineengine.common;

import java.util.Date;

/**
 * @author Roman Kaufmann
 *
 */
public interface ITrafficLog extends ITokenEntity {

	public abstract Date getTimeStamp();

	public abstract void setTimeStamp(Date timeStamp);

	public abstract double getSentData();

	public abstract void setSentData(double sentData);

	public abstract double getReceivedData();

	public abstract void setReceivedData(double receivedData);

}