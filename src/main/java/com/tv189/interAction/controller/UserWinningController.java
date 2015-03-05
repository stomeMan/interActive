package com.tv189.interAction.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import com.tv189.interAction.logic.UserWinningLogic;
import com.tv189.interAction.mybatis.model.UserWinning;

@Controller
@RequestMapping("/auctionService")
public class UserWinningController {
	private static final Logger logger = LoggerFactory.getLogger(UserWinningController.class);
	@Autowired
	UserWinningLogic uwLogic;
	
	@RequestMapping("/queryWinningUser")
	@ResponseBody
	public String queryWinningUser(HttpServletRequest request){
		String date = request.getParameter("date");
		String activityId = request.getParameter("activityId");
		activityId = activityId == null ? "" : activityId;
		BasicResponse result = uwLogic.queryWinningUser(activityId, date);
//		logger.info(JsonHelper.toJsonStr(result));
		return JsonHelper.toJsonStr(result);
	}
	
	@RequestMapping("/auction")
	@ResponseBody
	public String auction(HttpServletRequest request){
		String activityId = request.getParameter("activityId");
		String uid = request.getParameter("uid");
		String auctionTime = request.getParameter("auctionTime");
		String auctionFee = request.getParameter("auctionFee");
		String commodityId = request.getParameter("commodityId");
		String commodityName = request.getParameter("commodityName");
		String couponCode = request.getParameter("couponCode");
		String couponAmount = request.getParameter("couponAmount");
		
		String accountNo = request.getParameter("accountNo");
		String appId = request.getParameter("appId");
		String channelId = request.getParameter("channelId");
		String ip = request.getParameter("ip");
		String promotionChannel = request.getParameter("promotionChannel");
		
		UserWinning winUser = new UserWinning();
		winUser.setActivityId(activityId);
		winUser.setUid(uid);
		winUser.setAuctionFee(Integer.parseInt(auctionFee));
		try {
			winUser.setAuctionTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(auctionTime));
		} catch (ParseException e) {
			logger.error(" date format error: " + auctionTime);
			e.printStackTrace();
		}
		winUser.setCouponCode(couponCode);
		winUser.setCouponAmount(Integer.parseInt(couponAmount));
		winUser.setCommodityId(commodityId);
		winUser.setCommodityName(commodityName);
		winUser.setAccountNo(accountNo);
		winUser.setIp(ip);
		winUser.setChannelId(channelId);
		winUser.setAppId(appId);
		winUser.setPromotionChannel(promotionChannel);
		//BasicResponse result = AuctionThread.addQueue(winUser);
		BasicResponse result = uwLogic.auction(winUser);
//		logger.info(JsonHelper.toJsonStr(result));
		return JsonHelper.toJsonStr(result);
	}
	
	@RequestMapping("/queryUserAction")
	@ResponseBody
	public String queryUserAction(HttpServletRequest request){
		String uid = (String) request.getParameter("uid");
		BasicResponse result = uwLogic.queryUserAction(uid);
//		logger.info(JsonHelper.toJsonStr(result));
		return JsonHelper.toJsonStr(result);
	}
	
	
}
