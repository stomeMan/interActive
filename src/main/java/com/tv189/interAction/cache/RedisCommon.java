package com.tv189.interAction.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tv189.interAction.helper.CacheConfigHelper;

public class RedisCommon {
	private static final Logger logger = LoggerFactory.getLogger(RedisCommon.class);
	private static Map<String, Integer> timeMap = new HashMap<String, Integer>();
	private static Map<String, String> keyMap = new HashMap<String, String>();
	static {
		timeMap.put("Activity", CacheConfigHelper.getActivityTimeOut());
		timeMap.put("ActivityExt", CacheConfigHelper.getActivityExtTimeOut());
		timeMap.put("ActivityType", CacheConfigHelper.getActivityTypeTimeOut());
		timeMap.put("LiveProgramInfo", CacheConfigHelper.getLiveProgramInfoTimeOut());
		timeMap.put("UserWinning", CacheConfigHelper.getUserWinningTimeOut());
		timeMap.put("Commodity", CacheConfigHelper.getCommodityTimeOut());
		
		keyMap.put("Activity", "ACTIVITY_");
		keyMap.put("ActivityExt", "ACTIVITY_EXT_");
		keyMap.put("ActivityType", "ACTIVIRY_TYPE_");
		keyMap.put("LiveProgramInfo", "LIVE_PROGRAM_INFO_");
		keyMap.put("UserWinning", "USER_WINNING_");
		keyMap.put("Commodity", "COMMODITY_");
	}
	
	public static void setData(String key, String value, Object obj){
		String str = obj.getClass().getName();
		str = str.substring(str.lastIndexOf(".")+1, str.length());
		Integer seconds = timeMap.get(str) == null ? 3600 : timeMap.get(str);
		String keySec = keyMap.get(str) == null ? key : keyMap.get(str) + key;
//		logger.info(" set data redis key is :" + keySec);
		RedisAPI.setex(keySec, seconds, value);
	}
	
	public static void setData(String key, Object object,
			Object obj) {
		String value = JSON.toJSONString(object);
		String str = obj.getClass().getName();
		str = str.substring(str.lastIndexOf(".")+1, str.length());
		Integer seconds = timeMap.get(str) == null ? 3600 : timeMap.get(str);
		String keySec = keyMap.get(str) == null ? key : keyMap.get(str) + key;
//		logger.info(" set data redis key is :" + keySec);
		RedisAPI.setex(keySec, seconds, value);
	}
	
	public static String getData(String key, Object obj){
		String str = obj.getClass().getName();
		str = str.substring(str.lastIndexOf(".")+1, str.length());
		String keySec = keyMap.get(str) == null ? key : keyMap.get(str) + key;
//		logger.info(" get data redis key is :" + keySec);
		return RedisAPI.get(keySec);
	}

	
	
	
}
