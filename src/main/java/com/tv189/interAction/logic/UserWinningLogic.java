package com.tv189.interAction.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tv189.interAction.cache.RedisCommon;
import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.helper.KafkaProducer;
import com.tv189.interAction.helper.StringHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.dao.ActivityDao;
import com.tv189.interAction.mybatis.dao.UserActionDao;
import com.tv189.interAction.mybatis.dao.UserWinningCloseTypeDao;
import com.tv189.interAction.mybatis.dao.UserWinningDao;
import com.tv189.interAction.mybatis.dao.UserWinningGuessThePriceDao;
import com.tv189.interAction.mybatis.dao.UserWinningGuessTheStockDao;
import com.tv189.interAction.mybatis.dao.UserWinningPraiseDao;
import com.tv189.interAction.mybatis.model.UserAction;
import com.tv189.interAction.mybatis.model.UserWinning;
import com.tv189.interAction.mybatis.model.UserWinningCloseType;
import com.tv189.interAction.mybatis.model.UserWinningGuessThePrice;
import com.tv189.interAction.mybatis.model.UserWinningGuessTheStock;
import com.tv189.interAction.mybatis.model.UserWinningPraise;
import com.tv189.interAction.util.ParametersUtil;

@Service("/auctionService")
public class UserWinningLogic {
	@Autowired
	UserWinningDao uwDao;
	@Autowired
	ActivityDao activityDao;
	@Autowired
	UserWinningCloseTypeDao uwCTDao;
	@Autowired
	UserWinningGuessThePriceDao uwGTPDao;
	@Autowired
	UserWinningGuessTheStockDao uwGTSDao;
	@Autowired
	UserWinningPraiseDao uwPDao;
	@Autowired
	UserActionDao uaDao;
	@Autowired
	KafkaProducer producer;
	
	/**
	 * 获取抢拍成功的用户名单
	 * @param activityId  活动ID
	 * @param date 
	 * @return 活动信息  用户名单信息
	 * 通过活动编号查询活动信息，再根据活动编号查询用户信息，
	 * 最后构造activityInfo  winningUserInfo 节点同级的json格式串
	 * 考虑到活动和用户 一对多对应但节点同级，暂时业务逻辑为调用两个dao
	 */
	public BasicResponse queryWinningUser(String activityId, String date) {
		List<UserWinning> winUsers = new ArrayList<UserWinning>();
		String winUserJson = RedisCommon.getData(date, new UserWinning());
		if(!StringHelper.isNullOrEmpty(winUserJson)){
			List<UserWinning> winUserList = JsonHelper.toObjectList(winUserJson, UserWinning.class);
			if(!StringHelper.isNullOrEmpty(activityId)){
				for (UserWinning userWinning : winUserList) {
					if(activityId.equals(userWinning.getActivityId())){
						winUsers.add(userWinning);
					}
				}
			}else{
				winUsers = winUserList;
			}
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("date", date);
			//map.put("activityId", activityId);
			/*
			 * p1：塞入activityId时会导致查询不全 此处只根据date查询所有信息
			 *  c1 如果条件有activityId，则遍历查询结果，取得activityId相等的值
			 *  c2 如果条件没有activityId，直接赋值给list返回
			 */
			List<UserWinning> winUserList = uwDao.getWinningUserList(map);
			String winUsersJson = null;
			if(winUserList != null && !winUserList.isEmpty()){
				if(!StringHelper.isNullOrEmpty(activityId)){
					for (UserWinning userWinning : winUserList) {
						if(activityId.equals(userWinning.getActivityId())){
							winUsers.add(userWinning);
						}
					}
				}else{
					winUsers = winUserList;
				}
				winUsersJson = JSON.toJSONString(winUsers);
			}else{
				winUsersJson= "";
			}
			
			RedisCommon.setData(date, winUsersJson, new UserWinning());
		}
		
		UserWinning winUser = new UserWinning(winUsers);
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(winUser);
		return result;
	}
	
	/**
	 *  获取用户的活动记录
	 * @param uid 用户id
	 * @return
	 * 根据uid查询用户竞拍成功status=2的记录 并返回
	 */
	public BasicResponse queryUserAction(String uid) {
		List<UserWinning> winUsers = new ArrayList<UserWinning>();
		winUsers = uwDao.getWinUsersByUID(uid);
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(winUsers);
		return result;
	}
	
	/**
	 * 用户竞拍  走线程 不走此业务
	 * @param winUser
	 * @return
	 */
	public BasicResponse auction(UserWinning winUser) {
		String checkTimeAndPrice = ParametersUtil.checkTimeAndPrice(winUser.getActivityId(), 
				winUser.getAuctionFee(), winUser.getAuctionTime());
		if(!"".equals(checkTimeAndPrice)){
			//return new BasicResponse(101, checkTimeAndPrice, "竞拍失败！");
		}
		int count = uwDao.getCountByActAndUid(winUser);
		if(count>0){
			BasicResponse result = new BasicResponse();
			result.setCode(1);
			result.setMsg("每个用户只能竞拍一个活动");
			//return result;
		}
		producer.push(ParametersUtil.TOPIC_AUCTION, winUser.getUid(), JSON.toJSONString(winUser));
		return new BasicResponse(0, "OK", "已加入竞拍队列");
	}

	public BasicResponse auctionCommon(Object obj) {
		String uid = "";
		String topic = "";
		
		if(obj instanceof UserWinningPraise){
			UserWinningPraise object = (UserWinningPraise) obj;
			uid = object.getUid();
			topic = ParametersUtil.TOPIC_PRAISE;
		}
		
		if(obj instanceof UserWinningGuessTheStock){
			UserWinningGuessTheStock object = (UserWinningGuessTheStock) obj;
			uid = object.getUid();
			topic = ParametersUtil.TOPIC_GUESSTHESTOCK;
		}
		
		producer.push(topic, uid, JSON.toJSONString(obj));
		return new BasicResponse(0, "OK", "已加入竞拍队列");
	}

	public BasicResponse addUserAction(UserAction ua) {
		String uid = ua.getUid();
		int flag = Integer.parseInt(uid.substring(uid.length()-1, uid.length())) % 3;
		if(flag == 0){
			uaDao.insertUserAction3(ua);
		}
		if(flag == 1){
			uaDao.insertUserAction1(ua);
		}
		if(flag == 2){
			uaDao.insertUserAction2(ua);
		}
		return new BasicResponse(0, "OK", null);
	}

	public BasicResponse queryUserActive(UserAction ua) {
		List<UserAction> userActions = new ArrayList<UserAction>();
		String uid = ua.getUid();
		int flag = Integer.parseInt(uid.substring(uid.length()-1, uid.length())) % 3;
		if(flag == 0){
			userActions = uaDao.selectUserActive3(ua);
		}
		if(flag == 1){
			userActions = uaDao.selectUserActive1(ua);
		}
		if(flag == 2){
			userActions = uaDao.selectUserActive2(ua);
		}
		
		BasicResponse result = new BasicResponse();
		result.setCode(0);
		result.setMsg("OK");
		result.setInfo(userActions);
		return result;
	}

	public BasicResponse auctionForCloseType(UserWinningCloseType winUserCT) {
		String checkTimeAndPrice = ParametersUtil.checkTimeAndPrice(winUserCT.getActivityId(), 
				winUserCT.getAuctionFee(), winUserCT.getAuctionTime());
		if(!"".equals(checkTimeAndPrice)){
			//return new BasicResponse(101, checkTimeAndPrice, "竞拍失败！");
		}
		
		int count = uwCTDao.getCountByActAndUid(winUserCT);
		if(count>0){
			BasicResponse result = new BasicResponse();
			result.setCode(1);
			result.setMsg("每个用户只能竞拍一个活动");
			//return result;
		}
		producer.push(ParametersUtil.TOPIC_CLOSETYPE, winUserCT.getUid(), JSON.toJSONString(winUserCT));
		return new BasicResponse(0, "OK", "已加入竞拍队列");
	}

	public BasicResponse auctionForGuessPrice(
			UserWinningGuessThePrice winUserGTP) {
		String checkTimeAndPrice = ParametersUtil.checkTimeAndPrice(winUserGTP.getActivityId(), 
				winUserGTP.getAuctionFee(), winUserGTP.getAuctionTime());
		if(!"".equals(checkTimeAndPrice)){
			//return new BasicResponse(101, checkTimeAndPrice, "竞拍失败！");
		}
		
		int count = uwGTPDao.getCountByActAndUid(winUserGTP);
		if(count>0){
			BasicResponse result = new BasicResponse();
			result.setCode(1);
			result.setMsg("每个用户只能竞拍一个活动");
			//return result;
		}
		producer.push(ParametersUtil.TOPIC_GUESSTHEPRICE, winUserGTP.getUid(), JSON.toJSONString(winUserGTP));
		return new BasicResponse(0, "OK", "已加入竞拍队列");
	}

	
	
}
