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
import com.tv189.interAction.mybatis.dao.ActivityTypeDao;
import com.tv189.interAction.mybatis.model.ActivityType;

@Service("/activityTypeService")
public class ActivityTypeLogic {
	private static final Logger logger = LoggerFactory.getLogger(ActivityTypeLogic.class);
	@Autowired
	ActivityTypeDao actTypeDao;
	
	/**
	 * 获取活动类型数据
	 * @param activityIds 活动编号
	 * @return 活动类型json串
	 * 获取缓存数据 key="activity_type_"+activityIds
	 * 如果获取到缓存则返回json  
	 * 没有获取到缓存信息 查询数据并塞入缓存
	 */
	public BasicResponse queryActivityTypeInfo(String activityIds) {
		List<ActivityType> actTypeList = new ArrayList<ActivityType>();
		String actType = RedisCommon.getData(activityIds, new ActivityType());
		if(actType == null || "".equals(StringHelper.replaceBracket(actType))){
			if(activityIds.indexOf(StringHelper.COMMA) != -1){
				String[] actIdArr = activityIds.split(StringHelper.COMMA);
				actTypeList = actTypeDao.getActivityTypeByActIds(actIdArr);
			}else{
				actTypeList.add(actTypeDao.getActivityTypeByActId(activityIds));
			}
			RedisCommon.setData(activityIds, JSON.toJSONString(actTypeList), new ActivityType());
		}else{
			logger.info(" get from redis: "+actType);
			actTypeList = JsonHelper.toObjectList(actType, ActivityType.class);
		}
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(actTypeList);
		return result;
	}
	
	
}
