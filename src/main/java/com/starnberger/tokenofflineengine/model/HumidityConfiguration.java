package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@DiscriminatorValue(value = "HUM")
public class HumidityConfiguration extends SensorConfiguration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1743403325416095053L;

	@Column
	private int bleNotAdvertisingConditionHumidityAlarm;

	@Column
	private int bleAdvertisingConditionHumidityAlarm;

	@Column
	private double sensorHumidityAlarmHigh;

	@Column
	private double sensorHumidityAlarmLow;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HumidityConfiguration)) {
			return false;
		}
		HumidityConfiguration other = (HumidityConfiguration) obj;
		if (getId() != null) {
			if (!getId().equals(other.getId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	public int getBleNotAdvertisingConditionHumidityAlarm() {
		return bleNotAdvertisingConditionHumidityAlarm;
	}

	public void setBleNotAdvertisingConditionHumidityAlarm(int bleNotAdvertisingConditionHumidityAlarm) {
		this.bleNotAdvertisingConditionHumidityAlarm = bleNotAdvertisingConditionHumidityAlarm;
	}

	public int getBleAdvertisingConditionHumidityAlarm() {
		return bleAdvertisingConditionHumidityAlarm;
	}

	public void setBleAdvertisingConditionHumidityAlarm(int bleAdvertisingConditionHumidityAlarm) {
		this.bleAdvertisingConditionHumidityAlarm = bleAdvertisingConditionHumidityAlarm;
	}

	public double getSensorHumidityAlarmHigh() {
		return sensorHumidityAlarmHigh;
	}

	public void setSensorHumidityAlarmHigh(double sensorHumidityAlarmHigh) {
		this.sensorHumidityAlarmHigh = sensorHumidityAlarmHigh;
	}

	public double getSensorHumidityAlarmLow() {
		return sensorHumidityAlarmLow;
	}

	public void setSensorHumidityAlarmLow(double sensorHumidityAlarmLow) {
		this.sensorHumidityAlarmLow = sensorHumidityAlarmLow;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (getId() != null)
			result += "id: " + getId();
		result += ", version: " + getVersion();
		result += ", bleNotAdvertisingConditionHumidityAlarm: " + bleNotAdvertisingConditionHumidityAlarm;
		result += ", bleAdvertisingConditionHumidityAlarm: " + bleAdvertisingConditionHumidityAlarm;
		result += ", sensorHumidityAlarmHigh: " + sensorHumidityAlarmHigh;
		result += ", sensorHumidityAlarmLow: " + sensorHumidityAlarmLow;
		return result;
	}
}