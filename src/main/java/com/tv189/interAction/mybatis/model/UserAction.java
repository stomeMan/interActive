package com.tv189.interAction.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class UserAction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String appId;
	private String ip;
	private String ext;
	private Integer status;	
	private String creater;
	private Date createTime;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
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

}
