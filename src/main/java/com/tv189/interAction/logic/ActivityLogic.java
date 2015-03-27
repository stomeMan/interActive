package com.tv189.interAction.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tv189.interAction.cache.RedisCommon;
import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.helper.StringHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.dao.ActivityDao;
import com.tv189.interAction.mybatis.dao.ActivityExtDao;
import com.tv189.interAction.mybatis.dao.UserActionDao;
import com.tv189.interAction.mybatis.dao.UserWinningCloseTypeDao;
import com.tv189.interAction.mybatis.dao.UserWinningDao;
import com.tv189.interAction.mybatis.dao.UserWinningGuessThePriceDao;
import com.tv189.interAction.mybatis.dao.UserWinningGuessTheStockDao;
import com.tv189.interAction.mybatis.dao.UserWinningPraiseDao;
import com.tv189.interAction.mybatis.model.Activity;
import com.tv189.interAction.mybatis.model.ActivityExt;
import com.tv189.interAction.mybatis.model.UserWinning;
import com.tv189.interAction.mybatis.model.UserWinningCloseType;
import com.tv189.interAction.mybatis.model.UserWinningGuessThePrice;
import com.tv189.interAction.mybatis.model.UserWinningGuessTheStock;
import com.tv189.interAction.mybatis.model.UserWinningPraise;

@Service("/activityService")
public class ActivityLogic {
	@Autowired
	ActivityDao activityDao;
	@Autowired
	ActivityExtDao actExtDao;
	@Autowired
	UserWinningDao uwDao;
	@Autowired
	UserWinningCloseTypeDao uwCTDao;
	@Autowired
	UserWinningGuessThePriceDao uwGTPDao;
	@Autowired
	UserWinningGuessTheStockDao uwGTSDao;
	@Autowired
	UserWinningPraiseDao uwPDao;
	@Autowired
	UserActionDao uaDao;
	
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
		String activitys = RedisCommon.getData(activityIds, new Activity());
		String[] actIdArr = activityIds.split(StringHelper.COMMA);
		if(needExt != 0){
			//需要Ext信息 
			if(activitys == null || "".equals(StringHelper.replaceBracket(activitys))){
				activityList = activityDao.selectByActivityIds(actIdArr);
				RedisCommon.setData(activityIds, activityList, new Activity());
				for (Activity act : activityList) {
					String actExt = RedisCommon.getData(act.getActivityId(), new ActivityExt());
					if(actExt==null || "".equals(actExt)){
						actExt = actExtDao.getExtByActId(act.getActivityId());
						RedisCommon.setData(act.getActivityId(), actExt, new ActivityExt());
					}
					act.setExt(actExt);
				}
			}else{
				List<Activity> list = JsonHelper.toObjectList(activitys, Activity.class);
				for (Activity act : list) {
					String actExt = RedisCommon.getData(act.getActivityId(), new ActivityExt());
					if(actExt==null || "".equals(actExt)){
						actExt = actExtDao.getExtByActId(act.getActivityId());
						RedisCommon.setData(act.getActivityId(), actExt, new ActivityExt());
					}
					act.setExt(actExt);
				}
				activityList = list;
			}
			
		}else{
			if(activitys == null || "".equals(StringHelper.replaceBracket(activitys))){
				activityList = activityDao.selectByActivityIds(actIdArr);
				RedisCommon.setData(activityIds, activityList, new Activity());
			}else{
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
			actExt = actExtDao.getExtByActId(activityId);
			RedisCommon.setData(activityId, actExt, new ActivityExt());
		}else{
		}
		return actExt;
	}

	public BasicResponse queryWinningInfo(String activityId, String type,
			String date) {
		//1荷兰式竞拍，2闭式竞拍，3价格竞猜，4点赞狂魔，5指数竞猜，6闯关竞答
		type = type=="" ? "0" : type;
		int flag = Integer.parseInt(type);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("activityId", activityId);
		map.put("date", date);
		
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		
		if(flag == 1){
			List<UserWinning> list = uwDao.getWinningUserList(map);
			result.setInfo(list);
		}
		if(flag == 2){
			List<UserWinningCloseType> list = uwCTDao.getWinningUserList(map);
			result.setInfo(list);
		}
		if(flag == 3){
			List<UserWinningGuessThePrice> list = uwGTPDao.getWinningUserList(map);
			result.setInfo(list);
		}
		if(flag == 4){
			List<UserWinningPraise> list = uwPDao.getWinningUserList(map);
			result.setInfo(list);
		}
		
		if(flag == 5){
			List<UserWinningGuessTheStock> list = uwGTSDao.getWinningUserList(map);
			result.setInfo(list);
		}
		return result;
	}
}
