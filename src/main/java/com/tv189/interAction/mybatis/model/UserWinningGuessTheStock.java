package com.tv189.interAction.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class UserWinningGuessTheStock implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String activityId;

    private String uid;

    private String accountNo;

    private String appId;

    private String ip;

    private String commodityId;

    private String commodityName;

    private Date guessTime;

    private Integer status;

    private String creater;

    private Date createTime;

    private String lastUpdater;

    private Date lastUpdateTime;

    private String guessInfo;

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
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Date getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(Date guessTime) {
        this.guessTime = guessTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
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
        this.lastUpdater = lastUpdater == null ? null : lastUpdater.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getGuessInfo() {
        return guessInfo;
    }

    public void setGuessInfo(String guessInfo) {
        this.guessInfo = guessInfo == null ? null : guessInfo.trim();
    }
}