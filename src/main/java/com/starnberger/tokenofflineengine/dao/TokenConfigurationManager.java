/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;
import com.starnberger.tokenofflineengine.model.SensorConfiguration;
import com.starnberger.tokenofflineengine.model.Token;
import com.starnberger.tokenofflineengine.model.TokenConfiguration;
import com.starnberger.tokenofflineengine.model.configConversion.TokenConfigStructure;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenConfigurationManager {
	private static final TokenConfigurationManager _INSTANCE = new TokenConfigurationManager();

	/**
	 * @return
	 */
	public static final TokenConfigurationManager getInstance() {
		return _INSTANCE;
	}

	/**
	 * Private default constructor
	 */
	private TokenConfigurationManager() {
	}

	/**
	 * @param id
	 * @return
	 */
	public TokenConfiguration findById(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TokenConfiguration tokenConfiguration = em.find(TokenConfiguration.class, id);
		em.close();
		return tokenConfiguration;
	}

	/**
	 * @param id
	 * @return
	 */
	public TokenConfiguration findByRemoteId(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<TokenConfiguration> query = em.createNamedQuery("TokenConfiguration.findMyWebKey",
				TokenConfiguration.class);
		query.setParameter("webKey", id);
		List<TokenConfiguration> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	/**
	 * @param tokenConfiguration
	 * @param token
	 * @return
	 */
	public byte[] generateByteArrayFromConfig(TokenConfiguration tokenConfiguration, Token token) {
		EntityManager em = EMF.get().createEntityManager();
		Map<String, Map<String, SensorConfigValue>> sensorConfigValuesPerPosition = loadSensorConfigValues(
				tokenConfiguration, token, em);
		em.close();
		TokenConfigStructure tokenConfigStructure = new TokenConfigStructure(tokenConfiguration,
				sensorConfigValuesPerPosition);
		// Generate byte array in Little Endian format
		return tokenConfigStructure.toByteArray(false);
	}

	/**
	 * @param tokenConfiguration
	 * @param token
	 * @param em
	 * @return
	 */
	public Map<String, Map<String, SensorConfigValue>> loadSensorConfigValues(TokenConfiguration tokenConfiguration,
			Token token, EntityManager em) {
		Map<String, Map<String, SensorConfigValue>> sensorConfigValuesPerPosition = new HashMap<String, Map<String, SensorConfigValue>>();
		Iterator<Long> iterator = tokenConfiguration.getSensorConfigKeys().iterator();
		while (iterator.hasNext()) {
			Long sensorConfigId = (Long) iterator.next();
			SensorConfiguration sensorConfiguration = SensorConfigurationManager.getInstance().findByRemoteId(
					sensorConfigId);
			if (sensorConfiguration != null && sensorConfiguration.getConfigValueKeys() != null) {
				Map<String, SensorConfigValue> sensorConfigValues = new HashMap<String, SensorConfigValue>();
				Iterator<Long> valueIterator = sensorConfiguration.getConfigValueKeys().iterator();
				while (valueIterator.hasNext()) {
					Long valueId = (Long) valueIterator.next();
					SensorConfigValue configValue = SensorConfigValueManager.getInstance().findByRemoteId(valueId);
					sensorConfigValues.put(configValue.getConfigKey(), configValue);
				}
				sensorConfigValuesPerPosition.put(sensorConfiguration.getPosition(), sensorConfigValues);
			}
		}
		return sensorConfigValuesPerPosition;
	}
}
