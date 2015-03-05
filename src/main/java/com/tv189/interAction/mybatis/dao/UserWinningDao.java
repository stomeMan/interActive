package com.tv189.interAction.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.tv189.interAction.mybatis.model.UserWinning;

public interface UserWinningDao extends ISqlDao{

	public List<UserWinning> getWinningUserList(Map<String, String> map);
	
	public void insertUserWinning(UserWinning winUser);

	public List<UserWinning> getWinUsersByUID(String uid);
	
	public Integer getCountByActAndUid(UserWinning winUser);
	
	
}
