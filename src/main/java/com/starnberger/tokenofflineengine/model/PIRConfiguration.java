package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@DiscriminatorValue(value = "PIR")
public class PIRConfiguration extends SensorConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7589218183720412934L;

	@Column
	private int bleAdvertisingConditionMotionDetection;

	@Column
	private int bleNotAdvertisingConditionMotionDetection;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	public int getBleAdvertisingConditionMotionDetection() {
		return bleAdvertisingConditionMotionDetection;
	}

	public void setBleAdvertisingConditionMotionDetection(int bleAdvertisingConditionMotionDetection) {
		this.bleAdvertisingConditionMotionDetection = bleAdvertisingConditionMotionDetection;
	}

	public int getBleNotAdvertisingConditionMotionDetection() {
		return bleNotAdvertisingConditionMotionDetection;
	}

	public void setBleNotAdvertisingConditionMotionDetection(int bleNotAdvertisingConditionMotionDetection) {
		this.bleNotAdvertisingConditionMotionDetection = bleNotAdvertisingConditionMotionDetection;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PIRConfiguration)) {
			return false;
		}
		PIRConfiguration other = (PIRConfiguration) obj;
		if (getId() != null) {
			if (!getId().equals(other.getId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (getId() != null)
			result += "id: " + getId();
		result += ", version: " + getVersion();
		result += ", bleAdvertisingConditionMotionDetection: " + bleAdvertisingConditionMotionDetection;
		result += ", bleNotAdvertisingConditionMotionDetection: " + bleNotAdvertisingConditionMotionDetection;
		return result;
	}
}