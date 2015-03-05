package com.tv189.interAction.mybatis.dao;

import java.util.List;

import com.tv189.interAction.mybatis.model.ActivityType;

public interface ActivityTypeDao extends ISqlDao {
	
	public ActivityType getActivityTypeByActId(String activityId);

	public List<ActivityType> getActivityTypeByActIds(String[] actIdArr);
}
