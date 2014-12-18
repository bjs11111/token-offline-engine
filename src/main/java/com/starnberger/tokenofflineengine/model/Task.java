package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9221701354705230970L;
	@Id
	@GeneratedValue(generator = "task-uuid")
	@GenericGenerator(name = "task-uuid", strategy = "uuid2")
	private String id;
	@Version
	@Column(name = "version")
	private int version;
	@Column
	private TaskType type;
	@Column
	private String relatedId;
	@Column
	private Status status;
	@Column
	private Collection<String> parameters;
	@Column
	private Date created;
	@Column
	private Date completed;

	/**
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * @param version
	 */
	public void setVersion(final int version) {
		this.version = version;
	}

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
	 * @return
	 */
	public TaskType getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(TaskType type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public String getRelatedId() {
		return relatedId;
	}

	/**
	 * @param relatedId
	 */
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	/**
	 * @return
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return
	 */
	public Collection<String> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 */
	public void setParameters(Collection<String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return
	 */
	public Date getCompleted() {
		return completed;
	}

	/**
	 * @param completed
	 */
	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}