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

	private final EntityManager em;

	/**
	 * Private default constructor
	 */
	private TokenModelManager() {
		this.em = EMF.get().createEntityManager();
	}

	/**
	 * @param id
	 * @return
	 */
	public TokenModel findById(Long id) {
		return this.em.find(TokenModel.class, id);
	}

	/**
	 * @param id
	 * @return
	 */
	public SensorType findSensorById(Long id) {
		return this.em.find(SensorType.class, id);
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
