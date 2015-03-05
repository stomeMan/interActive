package com.tv189.interAction.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityType implements Serializable{
	
	private static final long serialVersionUID = -639306323666529523L;
	private Integer typeId;
	private String typeName;
	private String description;	//活动介绍
	private Date startTime;		//开始时间
	private String creater;		//创建人
	private Date createTime;	//创建时间
	private String lastUpdater;	//最后更新人
	private Date lasterUpdateTime;	//最后更新时间
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public Date getLasterUpdateTime() {
		return lasterUpdateTime;
	}
	public void setLasterUpdateTime(Date lasterUpdateTime) {
		this.lasterUpdateTime = lasterUpdateTime;
	}
	
}
