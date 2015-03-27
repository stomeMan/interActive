package com.tv189.interAction.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.logic.LiveProgramLogic;

@Controller
@RequestMapping("/liveService")
public class LiveProgramController {
	@Autowired
	LiveProgramLogic liveProgramLogic;
	
	@RequestMapping("/queryScheduleInfo")
	@ResponseBody
	public String queryScheduleInfo(HttpServletRequest request){
		try {
			String liveId = (String) request.getParameter("liveId");
			String date = (String) request.getParameter("date");
			BasicResponse result = liveProgramLogic.queryScheduleInfo(liveId, date);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/queryNowLiveInfo")
	@ResponseBody
	public String queryNowLiveInfo(HttpServletRequest request){
		try {
			String liveId = (String) request.getParameter("liveId");
			String time = (String) request.getParameter("time");
			BasicResponse result = liveProgramLogic.queryNowLiveInfo(liveId, time);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping("/queryChannelInfoByLive")
	@ResponseBody
	public String queryChannelInfoByLive(HttpServletRequest request){
		try {
			String liveId = (String) request.getParameter("liveId");
			BasicResponse result = liveProgramLogic.queryChannelInfoByLive(liveId);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/queryActivityByDay")
	@ResponseBody
	public String queryActivityByDay(HttpServletRequest request){
		try {
			String liveId = (String) request.getParameter("liveId");
			String date = (String) request.getParameter("activityDate");
			BasicResponse result = liveProgramLogic.queryActivityByDay(liveId, date);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
