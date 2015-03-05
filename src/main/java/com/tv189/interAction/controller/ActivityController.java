package com.tv189.interAction.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	@Autowired
	ActivityLogic activityLogic;
	
	@RequestMapping("/queryActivityInfo")
	@ResponseBody
	public String queryActivityInfo(HttpServletRequest request){
		String activityIds = (String) request.getParameter("activityIds");
		String needExt = request.getParameter("needExt");
		if(needExt==null || "".equals(needExt)) {
			needExt = "0";
		}
//		logger.info("queryActivityInfo needExt is:"+needExt);
		BasicResponse result = activityLogic.queryActivityInfo(activityIds, Integer.parseInt(needExt));
		return JsonHelper.toJsonStr(result);
	}
	
}
