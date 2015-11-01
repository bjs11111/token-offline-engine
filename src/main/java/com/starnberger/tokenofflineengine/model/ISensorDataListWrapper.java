package com.starnberger.tokenofflineengine.model;

import java.util.List;

import com.starnberger.tokenofflineengine.model.SensorData;

public interface ISensorDataListWrapper {

	public abstract List<SensorData> getList();

	public abstract void setList(List<SensorData> list);

}