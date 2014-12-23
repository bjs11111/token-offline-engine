package com.starnberger.tokenofflineengine.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
		@NamedQuery(name = "Task.deleted", query = "SELECT g from Task g WHERE g.isDeleted = :isDeleted") })
public class Task extends SyncEntity implements ITask {

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

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#getType()
	 */
	@Override
	public TaskType getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#setType(com.starnberger.tokenengine.server.dao.TaskType)
	 */
	@Override
	public void setType(TaskType type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#getRelatedId()
	 */
	@Override
	public String getRelatedId() {
		return relatedId;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#setRelatedId(java.lang.String)
	 */
	@Override
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#getStatus()
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#setStatus(com.starnberger.tokenengine.server.dao.Status)
	 */
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#getParameters()
	 */
	@Override
	public List<String> getParameters() {
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#setParameters(java.util.List)
	 */
	@Override
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#getCreated()
	 */
	@Override
	public Date getCreated() {
		return created;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#setCreated(java.util.Date)
	 */
	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#getCompleted()
	 */
	@Override
	public Date getCompleted() {
		return completed;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.server.dao.ITask#setCompleted(java.util.Date)
	 */
	@Override
	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}