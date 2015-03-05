package com.tv189.interAction.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class Activity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String activityId;	//活动编号
	private Integer type;		//表示活动类型，参照活动类型表
	private Integer status;		//1可用，2不可用
	private String description;	//活动介绍
	private Date startTime;		//开始时间
	private Date endTime;		//结束时间
	private Integer isDelete;	//是否删除，1删除，2不删除
	private String creater;		//创建人
	private Date createTime;	//创建时间
	private String lastUpdater;	//最后更新人
	private Date lastUpdateTime;//最后更新时间
	
	private ActivityExt activityExt;
	private String ext;
	private List<UserWinning> winUsers;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getActivityId() {
		return activityId;
	}
	
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
	
	public ActivityExt getActivityExt() {
		return activityExt;
	}
	
	public void setActivityExt(ActivityExt activityExt) {
		this.activityExt = activityExt;
	}
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public List<UserWinning> getWinUsers() {
		return winUsers;
	}
	public void setWinUsers(List<UserWinning> winUsers) {
		this.winUsers = winUsers;
	}
	
}
