/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.starnberger.tokenofflineengine.common.SensorTypeUtility;
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
	private Map<Long, TokenInfoStructure> tokenInfoByRemoteId = new HashMap<Long, TokenInfoStructure>();

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
			addTokenToMaps(tokenInfo);
		}
		return tokenInfo;
	}
	
	/**
	 * @param tokenInfo
	 */
	private void addTokenToMaps(TokenInfoStructure tokenInfo) {
		tokenInfoByMac.put(tokenInfo.token.getMac(), tokenInfo);
		tokenInfoByRemoteId.put(tokenInfo.token.getRemoteId(), tokenInfo);
	}
	
	
	/**
	 * @param remoteId
	 * @return
	 */
	public TokenInfoStructure getTokenInfo(Long remoteId) {
		TokenInfoStructure tokenInfo = tokenInfoByRemoteId.get(remoteId);
		if (tokenInfo != null)
			return tokenInfo;

		// Load token info from database
		Token token = TokenManager.getInstance().findByRemoteId(remoteId);
		if (token != null) {
			tokenInfo = addTokenToCache(token);
			addTokenToMaps(tokenInfo);
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
		tokenInfoByRemoteId.remove(token.getRemoteId());
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
			tokenInfo.sensorTypeToId.put(SensorTypeUtility.getSensorTypeForPosition(position), type.getRemoteId());
		}
		return tokenInfo;
	}

}
