package com.tv189.interAction.mybatis.dao;

import java.util.List;

import com.tv189.interAction.mybatis.model.Commodity;

public interface CommodityDao extends ISqlDao {

	public List<Commodity> getCommodityByIds(String[] commodityIdArr);
	
}
