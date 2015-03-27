package com.tv189.interAction.auction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tv189.interAction.cache.RedisCommon;
import com.tv189.interAction.helper.DynamicResource;
import com.tv189.interAction.httpRes.BasicResponse;
import com.tv189.interAction.mybatis.model.ActivityExt;
import com.tv189.interAction.mybatis.model.UserWinning;

public class AuctionThread extends Thread{
	private static final Logger logger = LoggerFactory.getLogger(AuctionThread.class);
	
	private static final String configName = "config/jdbc.properties";
	public static final String url = DynamicResource.getResource(configName).getString("dbmaster.url");  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = DynamicResource.getResource(configName).getString("dbmaster.username");  
    public static final String password = DynamicResource.getResource(configName).getString("dbmaster.password");  

    private static Queue<UserWinning> auctionQueue = new ConcurrentLinkedQueue<UserWinning>();
	public static BasicResponse addQueue(UserWinning winUser){
		//add by huanghua 20150309  新增竞拍时间和价格校验
		String checkTimeAndPrice = checkTimeAndPrice(winUser);
		if(!"".equals(checkTimeAndPrice)){
			return new BasicResponse(101, checkTimeAndPrice, "竞拍失败！");
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
					addWinUser(winUser);
				}
			}
			Integer sleepTime = 2*1000;
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
			Thread th = new AuctionThread();
			th.start();
			num++;
		}
	}
	
	private static String date = "yyyy-MM-dd HH:mm:ss";
	private static String SQL = "insert into User_Winning(activityId,uid,commodityId,commodityName,auctionFee,auctionTime,status, orderId,couponCode,couponAmount,creater,createTime,lastUpdater,lastUpdateTime,accountNo,ip,channelId,appId,promotionChannel) "
			+ "values ( ?, ?, ?, ?, ?, ?, 1, null, ?, ?, null, now(), null, now(),?,?,?,?,?)";
	
	public static void addWinUser(UserWinning winUser) {
		Connection conn = null;  
	    PreparedStatement pst = null; 
		try {
			Class.forName(name);//指定连接类型  
	        conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接  
	        pst = conn.prepareStatement(SQL);//准备执行语句  
	        
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
			pst.setInt(11, winUser.getChannelId());
			pst.setString(12, winUser.getAppId());
			pst.setString(13, winUser.getPromotionChannel());
			
			pst.execute();
			close(conn, pst);
		} catch (Exception e) {
			close(conn, pst);
			logger.error(" SQLException: ", e);
		}
		
	}
	
	private static void close(Connection conn, PreparedStatement pst) {
		try {  
        	if(conn!=null){
	            conn.close();  
	            pst.close();  
        	}
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
	}
	
	/**
	 * 判断竞拍时间和竞拍价格
	 * @param winUser 竞拍用户
	 * @return
	 * 竞拍时间是系统当前时间3s内误差
	 * 竞拍价格是活动扩展信息价格属性3s内误差
	 */
	private static String checkTimeAndPrice(UserWinning winUser) {
		Date auctionTime = winUser.getAuctionTime();
		Date now = new Date();
		long second = (auctionTime.getTime() - now.getTime()) / 1000;
		if(second > 3 || second < -3){
			return "竞拍时间不符合活动要求";
		}
		
		Integer auctionFee = winUser.getAuctionFee();
		String actExt = RedisCommon.getData(winUser.getActivityId(), new ActivityExt());
		JSONObject jsonObj = JSON.parseObject(actExt);
		Integer frequency = jsonObj.getInteger("frequency");
		Integer stepSize = jsonObj.getInteger("stepSize");
		Integer price = JSON.parseObject(jsonObj.getString("commodityInfo")).getInteger("price");
		Integer min = price - stepSize / frequency * 3;
		Integer max = price + stepSize / frequency * 3;
		
		if(auctionFee > max || auctionFee < 0 || auctionFee < min){
			return "竞拍价格有误";
		}
		
		return "";
	}
	
}
