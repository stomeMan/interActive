package com.tv189.interAction.logic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tv189.interAction.cache.RedisCommon;
import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.helper.StringHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.dao.CommodityDao;
import com.tv189.interAction.mybatis.model.Commodity;

@Service("/cmmodityService")
public class CommodityLogic {
	private static final Logger logger = LoggerFactory.getLogger(CommodityLogic.class);
	@Autowired
	CommodityDao commodityDao;
	
	public BasicResponse queryCommodityByIds(String commodityIds) {
		String[] commodityIdArr = commodityIds.split(StringHelper.COMMA);
		List<String> idList = new ArrayList<String>();
		List<Commodity> commodityListFromRedis = new ArrayList<Commodity>();
		List<Commodity> commodityListFromDB = new ArrayList<Commodity>();
		for (String commodityId : commodityIdArr) {
			String commodity = RedisCommon.getData(commodityId, new Commodity());
			if(commodity == null || "".equals(commodity)){
				idList.add(commodityId);
			}else{
				commodityListFromRedis.add(JsonHelper.toJSONObject(commodity, Commodity.class));
			}
		}
//		logger.info(" Commodity Redis Size is"+commodityListFromRedis.size());
		if(idList.size()>0){
			commodityListFromDB = commodityDao.getCommodityByIds((String[]) idList.toArray(new String[idList.size()]));
			for (Commodity commodity : commodityListFromDB) {
				RedisCommon.setData(commodity.getCommodityId(), JSON.toJSON(commodity), new Commodity());
			}
		}
//		logger.info(" Commodity DB Size is"+commodityListFromDB.size());
		commodityListFromRedis.addAll(commodityListFromDB);
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(commodityListFromRedis);
		return result;
	}

}
