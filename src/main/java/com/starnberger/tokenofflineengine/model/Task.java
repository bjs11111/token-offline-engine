package com.starnberger.tokenofflineengine.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.ITask;
import com.starnberger.tokenofflineengine.common.Status;
import com.starnberger.tokenofflineengine.common.TaskType;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Task.lastModified", query = "SELECT g from Task g WHERE g.lastModified > :lastSyncDate"),
		@NamedQuery(name = "Task.deleted", query = "SELECT g from Task g WHERE g.deleted = :isDeleted"),
		@NamedQuery(name = "Task.findMyWebKey", query = "select s from Task s where s.webKey = :webKey") })
public class Task extends SyncEntity implements ITask  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 710400088815734767L;
	@Column
	private TaskType type;
	@Column
	private String relatedId;
	@Column
	private Status status;
	@Basic
	private List<String> parameters;
	@Column
	private Date created;
	@Column
	private Date completed;
	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Task)) {
			return false;
		}
		Task other = (Task) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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