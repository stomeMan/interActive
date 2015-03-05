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
import com.tv189.interAction.logic.ActivityTypeLogic;

@Controller
@RequestMapping("/activityTypeService")
public class ActivityTypeController {
	private static final Logger logger = LoggerFactory.getLogger(ActivityTypeController.class);
	
	@Autowired
	ActivityTypeLogic actTypeLogic;
	
	@RequestMapping("/queryActivityTypeInfo")
	@ResponseBody
	public String queryActivityTypeInfo(HttpServletRequest request){
		String activityIds = (String) request.getParameter("activityIds");
		BasicResponse result = actTypeLogic.queryActivityTypeInfo(activityIds);
//		logger.info("------result-----"+JsonHelper.toJsonStr(result));
		return JsonHelper.toJsonStr(result);
	}
}
