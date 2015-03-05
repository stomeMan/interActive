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
import com.tv189.interAction.mybatis.dao.ActivityDao;
import com.tv189.interAction.mybatis.dao.ActivityExtDao;
import com.tv189.interAction.mybatis.model.Activity;
import com.tv189.interAction.mybatis.model.ActivityExt;

@Service("/activityService")
public class ActivityLogic {
	private static final Logger logger = LoggerFactory.getLogger(ActivityLogic.class);
	@Autowired
	ActivityDao activityDao;
	@Autowired
	ActivityExtDao actExtDao;
	/**
	 * 获取活动信息
	 * @param activityIds 活动编号
	 * @param needExt 是否需要拓展信息 1：是
	 * @return 活动信息json字符串
	 * 主要逻辑：
	 * 1 获取redis缓存数据 key=activityIds
			如果没有则查询数据库获取activity 并塞入缓存中
			如果缓存存在则直接返回
	 * 
	 */
	public BasicResponse queryActivityInfo(String activityIds, int needExt) {
		List<Activity> activityListFromRedis = new ArrayList<Activity>();
		List<Activity> activityListFromDB = new ArrayList<Activity>();
		String[] activityIdArr = activityIds.split(StringHelper.COMMA);
		List<String> activityIdList = new ArrayList<String>();
		for (String activityId : activityIdArr) {
			String activityJson = RedisCommon.getData(activityId, new Activity());
			if(activityJson==null || "".equals(activityJson)){
				activityIdList.add(activityId);
			}else{
				activityListFromRedis.add(JsonHelper.toJSONObject(activityJson, Activity.class));
			}
		}
		
		if(activityIdList.size()>0){
			activityListFromDB = activityDao.selectByActivityIds((String[]) activityIdList.toArray(new String[activityIdList.size()]));
		}
		
		if(needExt != 0){
			//需要Ext信息 
			for (Activity activity : activityListFromDB) {
				RedisCommon.setData(activity.getActivityId(), JSON.toJSON(activity), new Activity());
				activity.setExt(getExtByActId(activity.getActivityId()));
			}
			for (Activity activity : activityListFromRedis) {
				activity.setExt(getExtByActId(activity.getActivityId()));
			}
		}else{
			for (Activity act : activityListFromDB) {
				RedisCommon.setData(act.getActivityId(), JSON.toJSON(act), new Activity());
			}
		}
		activityListFromRedis.addAll(activityListFromDB);
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(activityListFromRedis);
		return result;
	}
	
	public BasicResponse queryActivityInfos(String activityIds, int needExt) {
		List<Activity> activityList = new ArrayList<Activity>();
		//String activitys = RedisActivity.getActivity(activityIds);
		String activitys = RedisCommon.getData(activityIds, new Activity());
		String[] actIdArr = activityIds.split(StringHelper.COMMA);
		if(needExt != 0){
			//需要Ext信息 
			if(activitys == null || "".equals(StringHelper.replaceBracket(activitys))){
//				logger.info(" There is no activitys in the redis ");
				activityList = activityDao.selectByActivityIds(actIdArr);
				RedisCommon.setData(activityIds, activityList, new Activity());
				for (Activity act : activityList) {
					String actExt = RedisCommon.getData(act.getActivityId(), new ActivityExt());
					if(actExt==null || "".equals(actExt)){
//						logger.info(" There is no acitvityExts in the redis ");
						actExt = actExtDao.getExtByActId(act.getActivityId());
//						logger.info(" actExt from dao: "+actExt);
						RedisCommon.setData(act.getActivityId(), actExt, new ActivityExt());
					}else{
//						logger.info(" AcitvityExts: "+actExt);
//						logger.info(" AcitvityExts are in the redis ! ");
					}
					act.setExt(actExt);
				}
			}else{
//				logger.info(" Activitys: "+activitys);
//				logger.info(" Activitys are in the redis ! The key is: "+activityIds);
				List<Activity> list = JsonHelper.toObjectList(activitys, Activity.class);
				for (Activity act : list) {
					String actExt = RedisCommon.getData(act.getActivityId(), new ActivityExt());
					if(actExt==null || "".equals(actExt)){
//						logger.info(" There is no acitvityExts in the redis ");
						actExt = actExtDao.getExtByActId(act.getActivityId());
//						logger.info(" actExt from dao: "+actExt);
						RedisCommon.setData(act.getActivityId(), actExt, new ActivityExt());
					}else{
//						logger.info(" AcitvityExts: "+actExt);
//						logger.info(" AcitvityExts are in the redis ");
					}
					act.setExt(actExt);
				}
				activityList = list;
			}
			
		}else{
			if(activitys == null || "".equals(StringHelper.replaceBracket(activitys))){
//				logger.info(" No Exts get activity from database");
				activityList = activityDao.selectByActivityIds(actIdArr);
				RedisCommon.setData(activityIds, activityList, new Activity());
			}else{
//				logger.info(" No Exts get activity from redis: "+ activitys);
//				logger.info(" redis key is " +activityIds);
				activityList = JsonHelper.toObjectList(activitys, Activity.class);
			}
			
		}
		
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(activityList);
		return result;
	}
	
	public String getExtByActId(String activityId){
		String actExt = RedisCommon.getData(activityId, new ActivityExt());
		if(actExt==null || "".equals(actExt)){
//			logger.info(" There is no acitvityExts in the redis ");
			actExt = actExtDao.getExtByActId(activityId);
//			logger.info(" actExt from dao: "+actExt);
			RedisCommon.setData(activityId, actExt, new ActivityExt());
		}else{
//			logger.info(" AcitvityExts: "+actExt);
//			logger.info(" AcitvityExts are in the redis ! ");
		}
		return actExt;
	}
}
