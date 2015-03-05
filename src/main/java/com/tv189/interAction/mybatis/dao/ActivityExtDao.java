package com.tv189.interAction.mybatis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tv189.interAction.mybatis.model.ActivityExt;

@Repository
public interface ActivityExtDao extends ISqlDao {
	@Autowired
	public ActivityExt selectByActivityId(String activityId);
	
	public String getExtByActId(String activityId);
}
