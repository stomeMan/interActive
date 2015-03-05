package com.tv189.interAction.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv189.interAction.cache.RedisCommon;
import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.helper.StringHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.dao.LiveProgramDao;
import com.tv189.interAction.mybatis.model.LiveProgramInfo;

@Service("/liveService")
public class LiveProgramLogic {
	private static final Logger logger = LoggerFactory.getLogger(LiveProgramLogic.class);
	@Autowired 
	LiveProgramDao liveProgramDao;
	
	/**
	 * 获取直播节目单
	 * @param liveId 节目编号
	 * @param date 日期  yyyymmdd
	 * @return 节目清单
	 * 首先读取缓存信息，key=liveId+date
	 * 如果获取到缓存信息则返回json串
	 * 没有获取到缓存则查询数据库并塞入缓存  查询条件liveId programListDate
	 */
	public BasicResponse queryScheduleInfo(String liveId, String date) {
		String key = liveId+"_"+date;
		List<LiveProgramInfo> lpiList = new ArrayList<LiveProgramInfo>();
//		String rlp = RedisLiveProgram.getLiveProgramInfo(key);
		String rlp = RedisCommon.getData(key, new LiveProgramInfo());
		if(rlp == null || "".equals(StringHelper.replaceBracket(rlp))){
			LiveProgramInfo lpi = new LiveProgramInfo();
			lpi.setLiveId(liveId);
			lpi.setProgramListDate(date);
			lpiList = liveProgramDao.getLiveProgramsByLiveDate(lpi);
			//RedisLiveProgram.setLiveProgram(key, lpiList);
			RedisCommon.setData(key, lpiList, new LiveProgramInfo());
		}else{
//			logger.info(" queryScheduleInfo get from redis"+rlp);
			lpiList = JsonHelper.toObjectList(rlp, LiveProgramInfo.class);
		}
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(lpiList);	
		return result;
	}
	
	/**
	 * 获取单个直播节目信息
	 * @param liveId 节目编号
	 * @param time 当前时间yyyymmddhhmmss
	 * @return 单个节目
	 * 首先读取缓存信息，key=liveId+"_"+time.substring(0, 8)
	 * 如果获取到则  解析缓存json数据，并判断 time是否在startTime end之间
	 * 如果找到记录则返回组织好的json
	 * 没有找到记录则根据liveId查询   time在开始时间和结束时间中间的节目单 并塞入到缓存中
	 */
	public BasicResponse queryNowLiveInfo(String liveId, String time) {
		String key = liveId+"_"+time.substring(0, 8);
		LiveProgramInfo liveProgramInfo = null;
		List<LiveProgramInfo> lpiList = new ArrayList<LiveProgramInfo>();
		//String rlp = RedisLiveProgram.getLiveProgramInfo(key);
		String rlp = RedisCommon.getData(key, new LiveProgramInfo());
		if(rlp == null || "".equals(StringHelper.replaceBracket(rlp))){
			Map<String, String> map = new HashMap<String, String>();
			map.put("liveId", liveId);
			map.put("time", time);
			lpiList = liveProgramDao.getLiveProgramByLiveDate(map);
			if(lpiList.size()>0){
				//lpiListResult = lpiList;
				liveProgramInfo = lpiList.get(0);
			}else{
				return new BasicResponse(1,"查无数据",null);
			}
		}else{
//			logger.info(" queryNowLiveInfo get from redis"+rlp);
			Date dateTime = new Date();
			try {
				dateTime = (new SimpleDateFormat("yyyyMMddHHmmss")).parse(time);
			} catch (ParseException e) {
				logger.info(" 日期格式化失败"+time);
				e.printStackTrace();
			}
			
			List<LiveProgramInfo> lpiListRedis = JsonHelper.toObjectList(rlp, LiveProgramInfo.class);
			for (LiveProgramInfo lpi : lpiListRedis) {
				if(dateTime.before(lpi.getEndTime()) && dateTime.after(lpi.getStartTime())){
//					logger.info(" get from liveProgramInfo redis in the whole day");
					//lpiListResult.add(lpi);
					liveProgramInfo = lpi;
				}
			}
		}
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(liveProgramInfo);	
		return result;
	}

	public BasicResponse queryChannelInfoByLive(String liveId) {
		
		//liveProgramDao.getChannelInfoByLive(liveId);
		
		return null;
	}

}
