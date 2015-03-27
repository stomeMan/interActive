package com.tv189.interAction.mybatis.model;

import java.util.Date;

public class LiveActivityRel {
	private Integer id;
	private String liveId;
	private String activityDate;
	private String activityId;
	private String creater;
	private Date createTime;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	private String name;
	private Integer type;
	private Integer status;		//1可用，2不可用
	private String description;	//活动介绍
	private Date startTime;		//开始时间
	private Date endTime;		//结束时间
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLiveId() {
		return liveId;
	}
	
	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}
	
	public String getActivityDate() {
		return activityDate;
	}
	
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	
	public String getActivityId() {
		return activityId;
	}
	
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
