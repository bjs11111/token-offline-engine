/**
 * 
 */
package com.starnberger.tokenofflineengine.common;

import com.starnberger.tokenengine.connector.parser.SensorValue;

/**
 * @author Roman Kaufmann
 *
 */
public final class SensorTypeUtility {
	/**
	 * Returns the fixed sensor type position to determine the correct sensor
	 * configuration or to correctly interpret a sensor data value
	 * 
	 * @param type
	 * @return
	 */
	public static String getPositionForSensorType(
			com.starnberger.tokenengine.connector.parser.SensorValue.SensorType type) {
		switch (type) {
		case TEMP1:
			return "1";
		case TEMP2:
			return "2";
		case TEMP3:
			return "3";
		case HUM:
			return "4";
		case BAR:
			return "5";
		case ORIENT:
			return "6";
		case PIR:
			return "7";
		case MOTION:
			return "8";
		case SHOCK:
			return "9";
		case BATT:
			return "A";
		default:
			return null;
		}
	}

	/**
	 * @param position
	 * @return
	 */
	public static com.starnberger.tokenengine.connector.parser.SensorValue.SensorType getSensorTypeForPosition(
			String position) {
		switch (position) {
		case "1":
			return SensorValue.SensorType.TEMP1;
		case "2":
			return SensorValue.SensorType.TEMP2;
		case "3":
			return SensorValue.SensorType.TEMP3;
		case "4":
			return SensorValue.SensorType.HUM;
		case "5":
			return SensorValue.SensorType.BAR;
		case "6":
			return SensorValue.SensorType.ORIENT;
		case "7":
			return SensorValue.SensorType.PIR;
		case "8":
			return SensorValue.SensorType.MOTION;
		case "9":
			return SensorValue.SensorType.SHOCK;
		case "A":
			return SensorValue.SensorType.BATT;
		default:
			return null;
		}
	}

}
