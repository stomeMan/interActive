package com.tv189.interAction.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tv189.interAction.auction.AuctionBatchThread;
import com.tv189.interAction.auction.AuctionThread;
import com.tv189.interAction.cache.RedisCommon;
import com.tv189.interAction.helper.JsonHelper;
import com.tv189.interAction.helper.StringHelper;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.dao.ActivityDao;
import com.tv189.interAction.mybatis.dao.UserWinningDao;
import com.tv189.interAction.mybatis.model.UserWinning;

@Service("/auctionService")
public class UserWinningLogic {
	private static final Logger logger = LoggerFactory.getLogger(UserWinningLogic.class);
	@Autowired
	UserWinningDao uwDao;
	@Autowired
	ActivityDao activityDao;
	
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
//			logger.info(" Oh Yes! It is in the RedisUserWinning ! ");
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
//			logger.info(" Oh No! There is nothing in the RedisUserWinning!");
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
//		logger.info(" 查询结果"+JsonHelper.toJsonStr(winUser));
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
//		String key = "getUserByUID_" + uid;
//		//String winUsersJson = RedisUserWinning.getUserWinning(key);
//		String winUsersJson = RedisCommon.getData(key, "");
//		if(winUsersJson == null || "".equals(StringHelper.replaceBracket(winUsersJson))){
//			logger.info(" Oh No! There is nothing in the RedisUserWinning!");
			winUsers = uwDao.getWinUsersByUID(uid);
//			RedisCommon.setData(key, winUsers, "");
//		}else{
//			logger.info(" Oh Yes! It is in the RedisUserWinning ! It's key is:"+key);
//			winUsers = JsonHelper.toObjectList(winUsersJson, UserWinning.class);
//		}
		
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
		int count = uwDao.getCountByActAndUid(winUser);
		if(count>0){
			BasicResponse result = new BasicResponse();
			result.setCode(1);
			result.setMsg("每个用户只能竞拍一个活动");
			return result;
		}
		return AuctionThread.addQueue(winUser);
	}

	
	
}
