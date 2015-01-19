package com.starnberger.tokenofflineengine.common;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;

import com.starnberger.tokenofflineengine.model.Task;

@MappedSuperclass
public abstract class AbstractTask extends SyncEntity implements ITask  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9221701354705230970L;
	@Column
	private TaskType type;
	@Column
	private String relatedId;
	@Column
	private Status status;
	@ElementCollection
	private List<String> parameters;
	@Column
	private Date created;
	@Column
	private Date completed;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public TaskType getType() {
		return type;
	}

	@Override
	public void setType(TaskType type) {
		this.type = type;
	}

	@Override
	public String getRelatedId() {
		return relatedId;
	}

	@Override
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public List<String> getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public Date getCompleted() {
		return completed;
	}

	@Override
	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public AbstractTask() {
		super();
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof Task)
		{
			Task token = (Task) source;
			setWebKey(token.getWebKey());
			setCompleted(token.getCompleted());
			setCreated(token.getCreated());
			setParameters(token.getParameters());
			setRelatedId(token.getRelatedId());
			setStatus(token.getStatus());
			setType(token.getType());
		}
	}

}