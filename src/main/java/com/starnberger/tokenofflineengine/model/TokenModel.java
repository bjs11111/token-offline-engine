package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TokenModel implements Serializable {

	@Id
	@GeneratedValue(generator = "tm-uuid")
	@GenericGenerator(name = "tm-uuid", strategy = "uuid2")
	private String id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String name;

	@OneToMany
	private List<SensorType> sensors;

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

	public List<SensorType> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorType> sensors) {
		this.sensors = sensors;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TokenModel)) {
			return false;
		}
		TokenModel other = (TokenModel) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		return result;
	}
}