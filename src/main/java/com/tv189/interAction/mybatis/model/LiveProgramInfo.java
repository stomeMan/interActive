package com.tv189.interAction.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class LiveProgramInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String liveId;
	private String pid;					//节目编号  对应数据库表字段liveListId 
	private Integer isRecord;			//默认null 对应数据库表字段isTaped
	private String programListDate;		//节目单日期yyyyMMdd
	private Date startTime;				//节目开始时间
	private Date endTime;				//节目结束时间
	private String title;				//节目名称
	private String length;				//节目时长(秒)
	private String sCover;				//节目横图JSON串
	private String cover;				//节目竖图JSON串
	private Integer status;				//1：未编辑，2：已编辑
	private String activityId;			//活动ID
	private String adapter;				//兼容老版本说说评论数据
	private String ext;	
	
	private LiveChannelInfo liveChannelInfo;
	
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(Integer isRecord) {
		this.isRecord = isRecord;
	}

	public String getProgramListDate() {
		return programListDate;
	}

	public void setProgramListDate(String programListDate) {
		this.programListDate = programListDate;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getsCover() {
		return sCover;
	}

	public void setsCover(String sCover) {
		this.sCover = sCover;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getAdapter() {
		return adapter;
	}

	public void setAdapter(String adapter) {
		this.adapter = adapter;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public LiveChannelInfo getLiveChannelInfo() {
		return liveChannelInfo;
	}

	public void setLiveChannelInfo(LiveChannelInfo liveChannelInfo) {
		this.liveChannelInfo = liveChannelInfo;
	}

}
