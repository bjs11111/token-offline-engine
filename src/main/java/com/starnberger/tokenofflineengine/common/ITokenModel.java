package com.starnberger.tokenofflineengine.common;

import java.util.List;

/**
 * @author Roman Kaufmann
 *
 */
public interface ITokenModel extends ISyncEntity {

	public abstract List<Long> getSensorKeys();

	public abstract void setSensorKeys(List<Long> sensors);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract void setSensorPositions(List<String> sensorPosition);

	public abstract List<String> getSensorPositions();

}