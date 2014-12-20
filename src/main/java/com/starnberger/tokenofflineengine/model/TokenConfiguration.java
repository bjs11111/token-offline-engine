package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
public class TokenConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2405928684566736499L;
	@Id
	@GeneratedValue(generator = "tc-uuid")
	@GenericGenerator(name = "tc-uuid", strategy = "uuid2")
	@Column(name = "ID")
	private String id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String webKey;

	@Column
	private TokenModel model;

	@Column
	private int bleAdvertisingInterval;

	@Column
	private int bleBondableInterval;

	@Column
	private boolean bleAdvertisingConditionAlways;

	@Column
	private int bleTxPower;

	@Column
	@Temporal(TemporalType.DATE)
	private Date lastModified;

	@OneToMany
	private Set<SensorConfiguration> sensorConfigs = new HashSet<SensorConfiguration>();

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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TokenConfiguration)) {
			return false;
		}
		TokenConfiguration other = (TokenConfiguration) obj;
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

	public TokenModel getModel() {
		return model;
	}

	public void setModel(TokenModel model) {
		this.model = model;
	}

	public int getBleAdvertisingInterval() {
		return bleAdvertisingInterval;
	}

	public void setBleAdvertisingInterval(int bleAdvertisingInterval) {
		this.bleAdvertisingInterval = bleAdvertisingInterval;
	}

	public int getBleBondableInterval() {
		return bleBondableInterval;
	}

	public void setBleBondableInterval(int bleBondableInterval) {
		this.bleBondableInterval = bleBondableInterval;
	}

	public boolean isBleAdvertisingConditionAlways() {
		return bleAdvertisingConditionAlways;
	}

	public void setBleAdvertisingConditionAlways(boolean bleAdvertisingConditionAlways) {
		this.bleAdvertisingConditionAlways = bleAdvertisingConditionAlways;
	}

	public int getBleTxPower() {
		return bleTxPower;
	}

	public void setBleTxPower(int bleTxPower) {
		this.bleTxPower = bleTxPower;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (model != null)
			result += ", model: " + model;
		result += ", bleAdvertisingInterval: " + bleAdvertisingInterval;
		result += ", bleBondableInterval: " + bleBondableInterval;
		result += ", bleAdvertisingConditionAlways: " + bleAdvertisingConditionAlways;
		result += ", bleTxPower: " + bleTxPower;
		if (lastModified != null)
			result += ", lastModified: " + lastModified;
		return result;
	}

	/**
	 * @return
	 */
	public Set<SensorConfiguration> getSensorConfigs() {
		return this.sensorConfigs;
	}

	/**
	 * @return the webKey
	 */
	public String getWebKey() {
		return webKey;
	}

	/**
	 * @param webKey
	 *            the webKey to set
	 */
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param sensorConfigs
	 */
	public void setSensorConfigs(final Set<SensorConfiguration> sensorConfigs) {
		this.sensorConfigs = sensorConfigs;
	}
}