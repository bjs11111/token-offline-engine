package com.starnberger.tokenofflineengine.common;

import java.util.Date;
import java.util.List;

/**
 * @author Roman Kaufmann
 *
 */
public interface ITask extends ISyncEntity {

	public abstract TaskType getType();

	public abstract void setType(TaskType type);

	public abstract Long getRelatedId();

	public abstract void setRelatedId(Long relatedId);

	public abstract Status getStatus();

	public abstract void setStatus(Status status);

	public abstract List<String> getParameters();

	public abstract void setParameters(List<String> parameters);

	public abstract Date getCreated();

	public abstract void setCreated(Date created);

	public abstract Date getCompleted();

	public abstract void setCompleted(Date completed);

}