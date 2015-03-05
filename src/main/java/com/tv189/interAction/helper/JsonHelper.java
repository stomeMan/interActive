package com.tv189.interAction.helper;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonHelper {
	private static Logger logger = Logger.getLogger(JsonHelper.class);
	
	public static String toJsonStr(Object obj) {
		try{
			return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
		}catch(Exception e){
			logger.error(obj+" toJsonStr fail"+e.getMessage());
			return null;
		}
	}

	public static <T> T toJSONObject(String jsonStr,Class<T> type){
		try{
			return JSON.parseObject(jsonStr,type);
		}catch(Exception e){
			logger.error(jsonStr +","+type+" toJSONObject fail"+e.getMessage());
			return null;
		}
		
	}
	
	public static  <T> List<T> toObjectList(String jsonStr,Class<T> type){
		try{
			return JSON.parseArray(jsonStr, type);
		}catch(Exception e){
			logger.error(jsonStr +","+type+" toObjectList fail"+e.getMessage());
			return null;
		}
	}

	public static <T> T toJSONObjectSlash(String jsonStr,Class<T> type){
		String reInfo = StringHelper.Empty;
		if(StringHelper.isNotEmpty(jsonStr)){
			String temp = jsonStr.replaceAll("\\\\", StringHelper.Empty);
			reInfo = temp.substring(1, temp.length()-1);
		}
		return JSON.parseObject(reInfo,type);
	}
	
}
