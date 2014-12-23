package com.starnberger.tokenofflineengine.common;

import java.util.List;

/**
 * @author Roman Kaufmann
 *
 */
public interface ITokenModel extends ISyncEntity {

	public abstract List<String> getSensorKeys();

	public abstract void setSensorKeys(List<String> sensors);

	public abstract String getName();

	public abstract void setName(String name);

}