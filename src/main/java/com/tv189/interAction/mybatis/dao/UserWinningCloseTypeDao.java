package com.tv189.interAction.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.tv189.interAction.mybatis.model.UserWinningCloseType;

public interface UserWinningCloseTypeDao extends ISqlDao {
	
    public void  insert(UserWinningCloseType record);

	public List<UserWinningCloseType> getWinningUserList(Map<String, String> map);
    
	public Integer getCountByActAndUid(UserWinningCloseType record);
}