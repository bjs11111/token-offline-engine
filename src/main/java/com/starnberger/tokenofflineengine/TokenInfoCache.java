/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.starnberger.tokenengine.connector.parser.SensorValue;
import com.starnberger.tokenofflineengine.dao.TokenManager;
import com.starnberger.tokenofflineengine.dao.TokenModelManager;
import com.starnberger.tokenofflineengine.model.SensorType;
import com.starnberger.tokenofflineengine.model.Token;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenInfoCache {
	private Map<String, TokenInfoStructure> tokenInfoByMac = new HashMap<String, TokenInfoStructure>();
	
	/**
	 * Default constructor.
	 */
	public TokenInfoCache() {
		
	}
	
	/**
	 * @param mac
	 * @return
	 */
	public TokenInfoStructure getTokenInfo(String mac) {
		TokenInfoStructure tokenInfo = tokenInfoByMac.get(mac);
		if (tokenInfo != null)
			return tokenInfo;

		// Load token info from database
		Token token = TokenManager.getInstance().findByMac(mac);
		if (token != null) {
			tokenInfo = addTokenToCache(token);
			tokenInfoByMac.put(tokenInfo.token.getMac(), tokenInfo);
		}
		return tokenInfo;
	}
	
	/**
	 * @param token
	 */
	public void removeTokenFromCache(Token token) {
		if (token == null)
			return;
		tokenInfoByMac.remove(token.getMac());
	}

	
	/**
	 * @param token
	 * @return
	 */
	public TokenInfoStructure addTokenToCache(Token token) {
		if (token == null)
			return null;
		TokenInfoStructure tokenInfo = new TokenInfoStructure();
		tokenInfo.token = token;
		tokenInfo.sensorTypesByPosition = TokenModelManager.getInstance().listSensorTypesForModel(token.getModel());
		Iterator<String> typeIterator = tokenInfo.sensorTypesByPosition.keySet().iterator();
		while (typeIterator.hasNext()) {
			String position = (String) typeIterator.next();
			SensorType type = tokenInfo.sensorTypesByPosition.get(position);
			tokenInfo.sensorTypeToId.put(getPositionForSensorType(position), type.getRemoteId());
		}
		return tokenInfo;
	}
	
	/**
	 * @param position
	 * @return
	 */
	private com.starnberger.tokenengine.connector.parser.SensorValue.SensorType getPositionForSensorType(String position) {
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
