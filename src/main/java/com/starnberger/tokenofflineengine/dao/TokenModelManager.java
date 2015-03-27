/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.starnberger.tokenofflineengine.model.SensorType;
import com.starnberger.tokenofflineengine.model.TokenModel;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenModelManager {
	private static final TokenModelManager _INSTANCE = new TokenModelManager();

	/**
	 * @return
	 */
	public static final TokenModelManager getInstance() {
		return _INSTANCE;
	}

	/**
	 * Private default constructor
	 */
	private TokenModelManager() {
	}

	/**
	 * @param id
	 * @return
	 */
	public TokenModel findById(Long id) {
		EntityManager em = EMF.get().createEntityManager();
		TokenModel find = em.find(TokenModel.class, id);
		em.close();
		return find;
	}

	/**
	 * @param id
	 * @return
	 */
	public TokenModel findByRemoteId(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<TokenModel> query = em.createNamedQuery("TokenModel.findMyWebKey", TokenModel.class);
		query.setParameter("webKey", id);
		List<TokenModel> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorType findSensorById(Long id) {
		EntityManager em = EMF.get().createEntityManager();
		SensorType find = em.find(SensorType.class, id);
		em.close();
		return find;
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorType findSensorByRemoteId(Long id) {
		if (id == null)
			return null;
		EntityManager em = EMF.get().createEntityManager();
		TypedQuery<SensorType> query = em.createNamedQuery("SensorType.findMyWebKey", SensorType.class);
		query.setParameter("webKey", id);
		List<SensorType> resultList = query.getResultList();
		if (resultList == null || resultList.isEmpty())
			return null;
		return resultList.get(0);
	}

	/**
	 * @param modelId
	 * @param sensorTypeId
	 * @return
	 */
	public String getSensorPosition(Long modelId, Long sensorTypeId) {
		TokenModel model = findByRemoteId(modelId);
		if (model == null)
			return null;
		int index = model.getSensorKeys().indexOf(sensorTypeId);
		if (index == -1)
			return null;
		return model.getSensorPositions().get(index);
	}

	/**
	 * @param id
	 * @param position
	 * @return
	 */
	public SensorType findSensorTypeByPosition(Long id, String position) {
		TokenModel model = findByRemoteId(id);
		if (model == null)
			return null;
		int index = model.getSensorPositions().indexOf(position);
		if (index == -1)
			return null;
		Long sensorId = model.getSensorKeys().get(index);
		if (id == null)
			return null;
		return findSensorByRemoteId(sensorId);
	}

	/**
	 * @param modelId
	 * @return
	 */
	public Map<String, SensorType> listSensorTypesForModel(Long modelId) {
		Map<String, SensorType> sensorTypes = new HashMap<String, SensorType>();
		TokenModel model = findByRemoteId(modelId);
		List<Long> sensorKeys = model.getSensorKeys();

		for (int i=0; i<sensorKeys.size();i++) {
			Long sensorTypeId = model.getSensorKeys().get(i);
			String position = model.getSensorPositions().get(i);
			SensorType sensorType = findSensorByRemoteId(sensorTypeId);
			sensorTypes.put(position, sensorType);
		}
		return sensorTypes;
	}
}
