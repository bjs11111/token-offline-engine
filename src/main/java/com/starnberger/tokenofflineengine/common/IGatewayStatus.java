package com.starnberger.tokenofflineengine.common;

import java.util.Date;

/**
 * @author Roman Kaufmann
 *
 */
public interface IGatewayStatus extends ITokenEntity {

	public abstract boolean isCellAntennaEnabled();

	public abstract void setCellAntennaEnabled(boolean cellAntennaEnabled);

	public abstract Date getLastStartTime();

	public abstract void setLastStartTime(Date lastStartTime);

	public abstract double getFreeMem();

	public abstract void setFreeMem(double freeMem);

	public abstract double getSystemTime();

	public abstract void setSystemTime(double systemTime);

}