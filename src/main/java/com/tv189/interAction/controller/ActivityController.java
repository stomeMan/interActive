package com.tv189.interAction.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.logic.ActivityLogic;

@Controller
@RequestMapping("/activityService")
public class ActivityController {
	@Autowired
	ActivityLogic activityLogic;
	
	@RequestMapping("/queryActivityInfo")
	@ResponseBody
	public String queryActivityInfo(HttpServletRequest request){
		try {
			String activityIds = (String) request.getParameter("activityIds");
			String needExt = request.getParameter("needExt");
			if(needExt==null || "".equals(needExt)) {
				needExt = "0";
			}
			BasicResponse result = activityLogic.queryActivityInfo(activityIds, Integer.parseInt(needExt));
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/queryWinningInfo")
	@ResponseBody
	public String queryWinningInfo(HttpServletRequest request){
		try {
			String activityId = (String) request.getParameter("activityId");
			String type = (String) request.getParameter("type");
			String date = (String) request.getParameter("date");
			
			BasicResponse result = activityLogic.queryWinningInfo(activityId, type, date);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
