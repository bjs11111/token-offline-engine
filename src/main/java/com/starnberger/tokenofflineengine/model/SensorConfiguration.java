package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@Inheritance
@DiscriminatorColumn
public abstract class SensorConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5618490630270815352L;
	@Id
	@GeneratedValue(generator = "sc-uuid")
	@GenericGenerator(name = "sc-uuid", strategy = "uuid2")
	private String id;
	@Version
	@Column(name = "version")
	private int version;

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

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
		if (!(obj instanceof SensorConfiguration)) {
			return false;
		}
		SensorConfiguration other = (SensorConfiguration) obj;
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
}