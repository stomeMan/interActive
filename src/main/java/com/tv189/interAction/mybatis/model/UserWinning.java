package com.tv189.interAction.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserWinning  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String activityId;			//活动id
	private String uid;					//用户id
	private String commodityId;			//商品id
	private String commodityName;		//用户id
	private Integer auctionFee;			//价格
	private Date auctionTime;			//拍中时间
	private Integer status;				//状态：1竞拍成功；2生成订购成功
	private String orderId;				//订单号
	private String couponCode;
	private Integer couponAmount;
	private String creater;
	private Date createTime;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	private Activity activityInfo;
	private List<UserWinning> winningUserInfo;
	private String activityName;
	
	private String accountNo;
	private String appId;
	private String channelId;
	private String ip;
	private String promotionChannel;
	private String picPath;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Integer getAuctionFee() {
		return auctionFee;
	}

	public void setAuctionFee(Integer auctionFee) {
		this.auctionFee = auctionFee;
	}

	public Date getAuctionTime() {
		return auctionTime;
	}

	public void setAuctionTime(Date auctionTime) {
		this.auctionTime = auctionTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(Integer couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Activity getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(Activity activityInfo) {
		this.activityInfo = activityInfo;
	}

	public List<UserWinning> getWinningUserInfo() {
		return winningUserInfo;
	}

	public void setWinningUserInfo(List<UserWinning> winningUserInfo) {
		this.winningUserInfo = winningUserInfo;
	}
	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getPromotionChannel() {
		return promotionChannel;
	}

	public void setPromotionChannel(String promotionChannel) {
		this.promotionChannel = promotionChannel;
	}
	
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public UserWinning(){
		
	}
	
	public UserWinning(Activity activityInfo, List<UserWinning> winningUserInfo) {
		super();
		this.activityInfo = activityInfo;
		this.winningUserInfo = winningUserInfo;
	}
	
	public UserWinning( List<UserWinning> winningUserInfo) {
		super();
		this.winningUserInfo = winningUserInfo;
	}
}
