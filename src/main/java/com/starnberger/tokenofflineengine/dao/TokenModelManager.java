/**
 * 
 */
package com.starnberger.tokenofflineengine.dao;

import javax.persistence.EntityManager;

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
	public SensorType findSensorById(Long id) {
		EntityManager em = EMF.get().createEntityManager();
		SensorType find = em.find(SensorType.class, id);
		em.close();
		return find;
	}
	
	/**
	 * @param modelId
	 * @param sensorTypeId
	 * @return
	 */
	public String getSensorPosition(Long modelId, Long sensorTypeId) {
		TokenModel model = findById(modelId);
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
		TokenModel model = findById(id);
		if (model == null)
			return null;
		int index = model.getSensorPositions().indexOf(position);
		if (index == -1)
			return null;
		Long sensorId = model.getSensorKeys().get(index);
		if (id == null)
			return null;
		return findSensorById(sensorId);
	}
}
