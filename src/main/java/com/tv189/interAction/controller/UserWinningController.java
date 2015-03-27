package com.tv189.interAction.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.logic.UserWinningLogic;
import com.tv189.interAction.mybatis.model.UserAction;
import com.tv189.interAction.mybatis.model.UserWinning;
import com.tv189.interAction.mybatis.model.UserWinningCloseType;
import com.tv189.interAction.mybatis.model.UserWinningGuessThePrice;
import com.tv189.interAction.mybatis.model.UserWinningGuessTheStock;
import com.tv189.interAction.mybatis.model.UserWinningPraise;

@Controller
@RequestMapping("/auctionService")
public class UserWinningController {
	@Autowired
	UserWinningLogic uwLogic;
	
	@RequestMapping("/queryWinningUser")
	@ResponseBody
	public String queryWinningUser(HttpServletRequest request){
		try {
			String date = request.getParameter("date");
			String activityId = request.getParameter("activityId");
			activityId = activityId == null ? "" : activityId;
			BasicResponse result = uwLogic.queryWinningUser(activityId, date);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/auction")
	@ResponseBody
	public String auction(HttpServletRequest request){
		try {
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
				e.printStackTrace();
			}
			winUser.setCouponCode(couponCode);
			winUser.setCouponAmount(Integer.parseInt(couponAmount));
			winUser.setCommodityId(commodityId);
			winUser.setCommodityName(commodityName);
			winUser.setAccountNo(accountNo);
			winUser.setIp(ip);
			winUser.setChannelId(Integer.parseInt(channelId));
			winUser.setAppId(appId);
			winUser.setPromotionChannel(promotionChannel);
			BasicResponse result = uwLogic.auction(winUser);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/queryUserAction")
	@ResponseBody
	public String queryUserAction(HttpServletRequest request){
		try {
			String uid = (String) request.getParameter("uid");
			BasicResponse result = uwLogic.queryUserAction(uid);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping("/auctionForCloseType")
	@ResponseBody
	public String auctionForCloseType(HttpServletRequest request){
		try {
			String activityId = request.getParameter("activityId");
			String uid = request.getParameter("uid");
			String auctionTime = request.getParameter("auctionTime");
			String auctionFee = request.getParameter("auctionFee");
			String commodityId = request.getParameter("commodityId");
			String commodityName = request.getParameter("commodityName");
			
			String accountNo = request.getParameter("accountNo");
			String appId = request.getParameter("appId");
			String channelId = request.getParameter("channelId");
			String ip = request.getParameter("ip");
			String promotionChannel = request.getParameter("promotionChannel");
			
			UserWinningCloseType winUserCT = new UserWinningCloseType();
			winUserCT.setActivityId(activityId);
			winUserCT.setUid(uid);
			winUserCT.setAuctionFee(Integer.parseInt(auctionFee));
			try {
				winUserCT.setAuctionTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(auctionTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			winUserCT.setCommodityId(commodityId);
			winUserCT.setCommodityName(commodityName);
			winUserCT.setAccountNo(accountNo);
			winUserCT.setIp(ip);
			winUserCT.setChannelId(Integer.parseInt(channelId));
			winUserCT.setAppId(appId);
			winUserCT.setPromotionChannel(promotionChannel);
			winUserCT.setStatus(1);
			BasicResponse result = uwLogic.auctionForCloseType(winUserCT);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping("/auctionForGuessPrice")
	@ResponseBody
	public String auctionForGuessPrice(HttpServletRequest request){
		try {
			String activityId = request.getParameter("activityId");
			String uid = request.getParameter("uid");
			String auctionTime = request.getParameter("auctionTime");
			String auctionFee = request.getParameter("auctionFee");
			String commodityId = request.getParameter("commodityId");
			String commodityName = request.getParameter("commodityName");
			
			String accountNo = request.getParameter("accountNo");
			String appId = request.getParameter("appId");
			String ip = request.getParameter("ip");
			
			UserWinningGuessThePrice winUserGTP = new UserWinningGuessThePrice();
			winUserGTP.setActivityId(activityId);
			winUserGTP.setUid(uid);
			winUserGTP.setAuctionFee(Integer.parseInt(auctionFee));
			try {
				winUserGTP.setAuctionTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(auctionTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			winUserGTP.setCommodityId(commodityId);
			winUserGTP.setCommodityName(commodityName);
			winUserGTP.setAccountNo(accountNo);
			winUserGTP.setIp(ip);
			winUserGTP.setAppId(appId);
			winUserGTP.setStatus(1);
			BasicResponse result = uwLogic.auctionForGuessPrice(winUserGTP);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping("/auctionForPraise")
	@ResponseBody
	public String auctionForPraise(HttpServletRequest request){
		try {
			String activityId = request.getParameter("activityId");
			String uid = request.getParameter("uid");
			String commodityId = request.getParameter("commodityId");
			String commodityName = request.getParameter("commodityName");
			
			String accountNo = request.getParameter("accountNo");
			String appId = request.getParameter("appId");
			String ip = request.getParameter("ip");
			String praiseCount = request.getParameter("praiseCount");
			String channelId = request.getParameter("channelId");
			String promotionChannel = request.getParameter("promotionChannel");
			
			UserWinningPraise winUserP = new UserWinningPraise();
			winUserP.setActivityId(activityId);
			winUserP.setUid(uid);
			winUserP.setCommodityId(commodityId);
			winUserP.setCommodityName(commodityName);
			winUserP.setAccountNo(accountNo);
			winUserP.setIp(ip);
			winUserP.setAppId(appId);
			winUserP.setPraiseCount(Integer.parseInt(praiseCount));
			winUserP.setChannelId(Integer.parseInt(channelId));
			winUserP.setPromotionChannel(promotionChannel);
			winUserP.setStatus(1);
			BasicResponse result = uwLogic.auctionCommon(winUserP);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping("/guessTheStock")
	@ResponseBody
	public String guessTheStock(HttpServletRequest request){
		try {
			String activityId = request.getParameter("activityId");
			String uid = request.getParameter("uid");
			String commodityId = request.getParameter("commodityId");
			String commodityName = request.getParameter("commodityName");
			String guessInfo = request.getParameter("guessInfo");
			String guessTime = request.getParameter("guessTime");
			String accountNo = request.getParameter("accountNo");
			String appId = request.getParameter("appId");
			String ip = request.getParameter("ip");
			
			UserWinningGuessTheStock winUserGTS = new UserWinningGuessTheStock();
			winUserGTS.setActivityId(activityId);
			winUserGTS.setUid(uid);
			winUserGTS.setCommodityId(commodityId);
			winUserGTS.setCommodityName(commodityName);
			winUserGTS.setAccountNo(accountNo);
			winUserGTS.setIp(ip);
			winUserGTS.setAppId(appId);
			winUserGTS.setGuessInfo(guessInfo);
			winUserGTS.setStatus(1);
			try {
				winUserGTS.setGuessTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(guessTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			BasicResponse result = uwLogic.auctionCommon(winUserGTS);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping("/addUserAction")
	@ResponseBody
	public String addUserAction(HttpServletRequest request){
		try {
			String uid = request.getParameter("uid");
			//String activityId = request.getParameter("activityId");
			String appId = request.getParameter("appId");
			String actionInfo = request.getParameter("actionInfo");
			UserAction ua = new UserAction();
			ua.setAppId(appId);
			ua.setUid(uid);
			ua.setStatus(1);
			ua.setExt(new String(actionInfo.getBytes("iso-8859-1"), "UTF-8"));
			BasicResponse result = uwLogic.addUserAction(ua);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	@RequestMapping("/queryUserActive")
	@ResponseBody
	public String queryUserActive(HttpServletRequest request){
		try {
			String uid = request.getParameter("uid");
			//String activityId = request.getParameter("activityId");
			String appId = request.getParameter("appId");
			UserAction ua = new UserAction();
			ua.setAppId(appId);
			ua.setUid(uid);
			BasicResponse result = uwLogic.queryUserActive(ua);
			return JsonHelper.toJsonStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
