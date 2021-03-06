package com.starnberger.tokenofflineengine.model.configConversion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;
import com.starnberger.tokenofflineengine.model.TokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenConfigStructure {
	private final static Map<String, SensorConfigValue> EMPTY_MAP = new HashMap<String, SensorConfigValue>();
	private BleGeneral bleGeneral; // Offset 0, Size 5
	private TemperatureSensorStructure temperature1 = new TemperatureSensorStructure(EMPTY_MAP); // Offset 5, Size 17
	private TemperatureSensorStructure temperature2 = new TemperatureSensorStructure(EMPTY_MAP); // Offset 22, Size 17
	private TemperatureSensorStructure temperature3 = new TemperatureSensorStructure(EMPTY_MAP); // Offset 39, Size 17
	private HumiditySensorStructure humidity = new HumiditySensorStructure(EMPTY_MAP); // Offset 56, Size 19
	private PressureSensorStructure pressure = new PressureSensorStructure(EMPTY_MAP); // Offset 75, Size 21
	private OrientationSensorStructure orientation = new OrientationSensorStructure(EMPTY_MAP); // Offset 96, Size 27
	private PIRSensorStructure pir= new PIRSensorStructure(EMPTY_MAP); // Offset 123, Size 9
	private MotionSensorStructure motion = new MotionSensorStructure(EMPTY_MAP); // Offset 132, Size 12
	private ShockSensorStructure mechanicalShock = new ShockSensorStructure(EMPTY_MAP); // Offset 144, Size 15
	private byte[] filler = new byte[14]; 

	/**
	 * @param tokenConfiguration
	 * @param sensorConfigValuesPerPosition
	 */
	public TokenConfigStructure(TokenConfiguration tokenConfiguration,
			Map<String, Map<String, SensorConfigValue>> sensorConfigValuesPerPosition) {
		bleGeneral = new BleGeneral(tokenConfiguration);
		Iterator<String> sensorPositionIterator = sensorConfigValuesPerPosition.keySet().iterator();
		while (sensorPositionIterator.hasNext()) {
			String sensorPosition = (String) sensorPositionIterator.next();
			Map<String, SensorConfigValue> configValues = sensorConfigValuesPerPosition.get(sensorPosition);
			switch (sensorPosition) {
			case "1":
				temperature1 = new TemperatureSensorStructure(configValues);
				break;
			case "2":
				temperature2 = new TemperatureSensorStructure(configValues);
				break;
			case "3":
				temperature3 = new TemperatureSensorStructure(configValues);
				break;
			case "4":
				humidity = new HumiditySensorStructure(configValues);
				break;
			case "5":
				pressure = new PressureSensorStructure(configValues);
				break;
			case "6":
				orientation = new OrientationSensorStructure(configValues);
				break;
			case "7":
				pir = new PIRSensorStructure(configValues);
				break;
			case "8":
				motion = new MotionSensorStructure(configValues);
				break;
			case "9":
				mechanicalShock = new ShockSensorStructure(configValues);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @param isBigEndian
	 * @return
	 */
	public byte[] toByteArray(boolean isBigEndian) {
		byte[] result = ArrayUtils.EMPTY_BYTE_ARRAY;
		result = ArrayUtils.addAll(result, bleGeneral.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, temperature1.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, temperature2.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, temperature3.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, humidity.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, pressure.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, orientation.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, pir.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, motion.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, mechanicalShock.toByteArray(isBigEndian));
		result = ArrayUtils.addAll(result, filler);
		return result;
	}
}
