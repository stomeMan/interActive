package com.tv189.interAction.auction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.dao.UserWinningDao;
import com.tv189.interAction.mybatis.model.UserWinning;
import com.tv189.interAction.util.ParametersUtil;

@Component
public class AuctionMyBatisThread extends Thread{
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	private static List<UserWinning> list = new ArrayList<UserWinning>();
	
	private static Queue<UserWinning> auctionQueue = new ConcurrentLinkedQueue<UserWinning>();
	
	public static BasicResponse addQueue(UserWinning winUser){
		String checkTimeAndPrice = ParametersUtil.checkTimeAndPrice(winUser.getActivityId(),winUser.getAuctionFee(),winUser.getAuctionTime());
		if(!"".equals(checkTimeAndPrice)){
			return new BasicResponse(101, checkTimeAndPrice, "竞拍失败！");
		}
		
		//判断list集合里是否重复的竞拍数据
		for (UserWinning uw : list) {
			if(uw.getActivityId().equals(winUser.getActivityId()) && uw.getUid().equals(winUser.getUid())){
				BasicResponse result = new BasicResponse();
				result.setCode(1);
				result.setMsg("每个用户只能竞拍一个活动");
				return result;
			}
		}
		
		auctionQueue.add(winUser); 
		return new BasicResponse(0, "OK", "已加入竞拍队列");
	}
	
	@Override
	public void run() {
		while (true) {
			Integer size = auctionQueue.size();
			for (Integer i = 0; i < size; i++) {
				UserWinning winUser = (UserWinning) auctionQueue.poll();
				if(winUser!=null){
					int flag = 0;
					for (UserWinning uw : list) {
						if(uw.getActivityId().equals(winUser.getActivityId()) && uw.getUid().equals(winUser.getUid())){
							flag = 1;
							break;
						}
					}
					if(flag != 0){
						continue;
					}
					list.add(winUser);
				}
			}
			
			if(list.size() > 0){
				SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
				UserWinningDao userWinningDao = session.getMapper(UserWinningDao.class);
				try {
					for (UserWinning uw : list){
						userWinningDao.insertUserWinning(uw);
					}
					session.commit();
					session.clearCache();
				} catch (Exception e) {
					session.rollback();
					e.printStackTrace();
				} finally {
					session.close();
					list.clear();
				}
			}
			
			Integer sleepTime = 2*1000;
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Integer num = 0;
	public void initThread() {
		if(num==0){
			Thread th = new AuctionMyBatisThread();
			th.start();
			num++;
		}
	}
	
}
