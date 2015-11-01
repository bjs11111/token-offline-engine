package com.starnberger.tokenofflineengine.common;

import com.starnberger.tokenofflineengine.common.ISyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
public interface IGatewayVersion extends ISyncEntity {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract String getFile();

	public abstract void setFile(String file);

}