package com.tv189.interAction.mybatis.dao;


import java.util.List;

import com.tv189.interAction.mybatis.model.UserAction;

public interface UserActionDao extends ISqlDao {
	
	public void insertUserAction1(UserAction userAction);
	
	public void insertUserAction2(UserAction userAction);
	
	public void insertUserAction3(UserAction userAction);
	
	public List<UserAction> selectUserActive1(UserAction userAction);

	public List<UserAction> selectUserActive2(UserAction userAction);

	public List<UserAction> selectUserActive3(UserAction userAction);

}
