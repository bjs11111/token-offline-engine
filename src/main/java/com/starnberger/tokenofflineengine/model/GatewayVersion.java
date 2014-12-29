package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.starnberger.tokenofflineengine.common.IGatewayVersion;
import com.starnberger.tokenofflineengine.common.ISyncEntity;
import com.starnberger.tokenofflineengine.common.SyncEntity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
public class GatewayVersion extends SyncEntity implements IGatewayVersion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -693090586746867736L;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String file;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GatewayVersion)) {
			return false;
		}
		GatewayVersion other = (GatewayVersion) obj;
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getFile() {
		return file;
	}

	@Override
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		if (file != null && !file.trim().isEmpty())
			result += ", file: " + file;
		return result;
	}

	@Override
	public void copyValues(ISyncEntity source) {
		if (source == null)
			return;
		if (source instanceof GatewayVersion)
		{
			GatewayVersion token = (GatewayVersion) source;
			setWebKey(token.getWebKey());
			setDescription(token.getDescription());
			setFile(token.getFile());
			setName(token.getName());
		}
	}
}