package com.tv189.interAction.mybatis.model;

import java.util.Date;

public class LiveChannelInfo {
	
	private Integer id;
	private String liveId;			//直播编号
	private String name;			//直播名称
	private Integer plats;			//支持平台
	private String pinyin;
	private Integer physicalType;	//0、直播，1、轮播
	private String cpId;
	private String spId;
	private String scover;
	private String description;
	private String nodeId;
	private Date createTime;
	private Date updateTime;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPlats() {
		return plats;
	}
	public void setPlats(Integer plats) {
		this.plats = plats;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public Integer getPhysicalType() {
		return physicalType;
	}
	public void setPhysicalType(Integer physicalType) {
		this.physicalType = physicalType;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getScover() {
		return scover;
	}
	public void setScover(String scover) {
		this.scover = scover;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
