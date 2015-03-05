package com.tv189.interAction.mybatis.dao;

import java.util.List;

import com.tv189.interAction.mybatis.model.Activity;

public interface ActivityDao extends ISqlDao {
	
	public Activity selectByActivityId(String activityId);
	
	public Activity getActAndExtByActId(String activityId);

	public List<Activity> getActAndExtByActIds(String[] actIdArr);
	
	public List<Activity> selectByActivityIds(String[] actIdArr);

	public Activity getActivityByActId(String activityId);
	
}
