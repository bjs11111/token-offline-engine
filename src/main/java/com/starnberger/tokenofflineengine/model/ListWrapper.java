/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import java.util.ArrayList;
import java.util.List;

import com.starnberger.tokenofflineengine.common.ITokenEntity;

/**
 * @author Roman Kaufmann
 *
 *         This ListWrapper is used to wrap any list of objects. For security
 *         reasons this should always be used, when a REST service returns a
 *         list of objects.
 * @param <T>
 */
public class ListWrapper implements IListWrapper {
	private List<? extends ITokenEntity> list;

	/**
	 * Default constructor.
	 */
	public ListWrapper() {
		this.list = new ArrayList<ITokenEntity>();
	}
	/**
	 * @param list
	 */
	public ListWrapper(List<? extends ITokenEntity> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenofflineengine.model.IListWrapper#getList()
	 */
	@Override
	public List<ITokenEntity> getList() {
		return (List<ITokenEntity>) list;
	}

	/* (non-Javadoc)
	 * @see com.starnberger.tokenofflineengine.model.IListWrapper#setList(java.util.List)
	 */
	@Override
	public void setList(List<ITokenEntity> list) {
		this.list = list;
	}

}
