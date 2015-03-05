package com.tv189.interAction.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.logic.CommodityLogic;

@Controller
@RequestMapping("/commodityService")
public class CommodityController {
	@Autowired
	CommodityLogic commodityLogic;
	
	@RequestMapping("/queryCommodityById")
	@ResponseBody
	public String queryCommodityById(HttpServletRequest request){
		String commodityIds = (String) request.getParameter("commodityIds");
		BasicResponse result = commodityLogic.queryCommodityByIds(commodityIds);
		return JsonHelper.toJsonStr(result);
	}
}
