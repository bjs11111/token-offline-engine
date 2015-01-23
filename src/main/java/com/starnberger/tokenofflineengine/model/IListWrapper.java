package com.starnberger.tokenofflineengine.model;

import java.util.List;

import com.starnberger.tokenofflineengine.common.ITokenEntity;

public interface IListWrapper {

	/**
	 * @return the list
	 */
	public abstract List<ITokenEntity> getList();

	/**
	 * @param list
	 *            the list to set
	 */
	public abstract void setList(List<ITokenEntity> list);

}