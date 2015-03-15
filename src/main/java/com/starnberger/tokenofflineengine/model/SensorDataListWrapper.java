/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

import com.starnberger.tokenofflineengine.model.SensorData;

/**
 * @author Roman Kaufmann
 *
 *         This ListWrapper is used to wrap any list of objects. For security
 *         reasons this should always be used, when a REST service returns a
 *         list of objects.
 * @param <T>
 */
public class SensorDataListWrapper implements ISensorDataListWrapper    {
	private List<SensorData> list;

	/**
	 * Default constructor.
	 */
	public SensorDataListWrapper() {
		this.list = new ArrayList<SensorData>();
	}

	/**
	 * @param list
	 */
	public SensorDataListWrapper(List<SensorData> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.shared.dao.list.ISensorDataListWrapper#getList()
	 */
	@Override
	public List<SensorData> getList() {
		return list;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenengine.shared.dao.list.ISensorDataListWrapper#setList(java.util.List)
	 */
	@Override
	public void setList(List<SensorData> list) {
		this.list = list;
	}

}
