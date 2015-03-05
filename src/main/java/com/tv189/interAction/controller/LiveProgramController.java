package com.tv189.interAction.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.logic.LiveProgramLogic;

@Controller
@RequestMapping("/liveService")
public class LiveProgramController {
	private static final Logger logger = LoggerFactory.getLogger(LiveProgramController.class);
	@Autowired
	LiveProgramLogic liveProgramLogic;
	
	@RequestMapping("/queryScheduleInfo")
	@ResponseBody
	public String queryScheduleInfo(HttpServletRequest request){
		String liveId = (String) request.getParameter("liveId");
		String date = (String) request.getParameter("date");
//		logger.info(" queryScheduleInfo HttpServletRequest params date:"+date);
		BasicResponse result = liveProgramLogic.queryScheduleInfo(liveId, date);
		return JsonHelper.toJsonStr(result);
	}
	
	@RequestMapping("/queryNowLiveInfo")
	@ResponseBody
	public String queryNowLiveInfo(HttpServletRequest request){
		String liveId = (String) request.getParameter("liveId");
		String time = (String) request.getParameter("time");
		BasicResponse result = liveProgramLogic.queryNowLiveInfo(liveId, time);
		return JsonHelper.toJsonStr(result);
	}
	
	
	@RequestMapping("/queryChannelInfoByLive")
	@ResponseBody
	public String queryChannelInfoByLive(HttpServletRequest request){
		String liveId = (String) request.getParameter("liveId");
		BasicResponse result = liveProgramLogic.queryChannelInfoByLive(liveId);
		return JsonHelper.toJsonStr(result);
	}
}
