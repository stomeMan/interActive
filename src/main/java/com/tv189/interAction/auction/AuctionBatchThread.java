package com.tv189.interAction.auction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tv189.interAction.helper.DynamicResource;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.model.UserWinning;

public class AuctionBatchThread extends Thread{
	private static final Logger logger = LoggerFactory.getLogger(AuctionBatchThread.class);
	
	private static final String configName = "config/jdbc.properties";
	private static final String url = DynamicResource.getResource(configName).getString("dbmaster.url");  
	private static final String name = "com.mysql.jdbc.Driver";  
	private static final String user = DynamicResource.getResource(configName).getString("dbmaster.username");  
	private static final String password = DynamicResource.getResource(configName).getString("dbmaster.password");  
	
	private static Integer exeBatch = 0;
	private static String date = "yyyy-MM-dd HH:mm:ss";
	private static String SQL = "insert into User_Winning(activityId,uid,commodityId,commodityName,auctionFee,auctionTime,status, orderId,couponCode,couponAmount,creater,createTime,lastUpdater,lastUpdateTime,accountNo,ip,channelId,appId,promotionChannel) "
			+ "values ( ?, ?, ?, ?, ?, ?, 1, null, ?, ?, null, now(), null, now(),?,?,?,?,?)";
	
	private static Connection conn = null;  
	private static PreparedStatement pst = null; 
	
	private static void getDBConn(){
		try {
			Class.forName(name);//指定连接类型  
			conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接  
	        pst = conn.prepareStatement(SQL);//准备执行语句  
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	private static Queue<UserWinning> auctionQueue = new ConcurrentLinkedQueue<UserWinning>();
    private static List<UserWinning> winUsers = new ArrayList<UserWinning>();
	public static BasicResponse addQueue(UserWinning winUser){
		//判断list集合里是否重复的竞拍数据
		for (UserWinning uw : winUsers) {
			if(uw.getActivityId().equals(winUser.getActivityId()) && uw.getUid().equals(winUser.getUid())){
				logger.info(" The same data in the batch! Auction failed");
				BasicResponse result = new BasicResponse();
				result.setCode(1);
				result.setMsg("每个用户只能竞拍一个活动");
				return result;
			}
		}
		//非重复数据时加入到对象集合中
		winUsers.add(winUser); 
		auctionQueue.add(winUser);
		return new BasicResponse(0, "OK", "已加入竞拍队列");
	}
	
	@Override
	public void run() {
		Calendar c;
		getDBConn();
		while (true) {
			Integer size = auctionQueue.size();
			for (Integer i = 0; i < size; i++) {
				UserWinning winUser = (UserWinning) auctionQueue.poll();
				if(winUser!=null){
					addWinUser(winUser);
				}
			}
			//5条数据时清空list
			if(exeBatch >= 5){
				winUsers.clear();
			}
			//获取Calendar 当前时间 
			c = Calendar.getInstance();
			//每5条数据执行一次batch 每隔7秒执行一次batch 
			if(exeBatch >= 5 || c.get(Calendar.SECOND) % 7 == 0){
				//logger.info("executeBatch exeBatch: " + exeBatch + " SECOND: "+c.get(Calendar.SECOND));
				try {
					pst.executeBatch();
					exeBatch = 0;
				} catch (SQLException e) {
					logger.error(" SQLException:  ", e);
				}
			}
			
			Integer sleepTime = 5*1000;
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				logger.error(" InterruptedException:  ", e);
			}
			
		}
	}
	
	private static Integer num = 0;
	public void initThread() {
		if(num==0){
			Thread th = new AuctionBatchThread();
			th.start();
			num++;
		}
	}
	
	public static void addWinUser(UserWinning winUser) {
		try {
			exeBatch++;
	        pst.setString(1, winUser.getActivityId());
			pst.setString(2, winUser.getUid());
			pst.setString(3, winUser.getCommodityId());
			pst.setString(4, winUser.getCommodityName());
			pst.setInt(5, winUser.getAuctionFee());
			pst.setString(6, new SimpleDateFormat(date).format(winUser.getAuctionTime()));
			pst.setString(7, winUser.getCouponCode());
			pst.setInt(8, winUser.getCouponAmount());
			
			pst.setString(9, winUser.getAccountNo());
			pst.setString(10, winUser.getIp());
			pst.setString(11, winUser.getChannelId());
			pst.setString(12, winUser.getAppId());
			pst.setString(13, winUser.getPromotionChannel());
			pst.addBatch();
		} catch (Exception e) {
			logger.error(" SQLException: ", e);
		}
		
	}
	
}
