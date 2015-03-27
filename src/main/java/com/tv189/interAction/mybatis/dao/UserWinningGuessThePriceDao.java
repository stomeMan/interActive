package com.tv189.interAction.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.tv189.interAction.mybatis.model.UserWinningGuessThePrice;

public interface UserWinningGuessThePriceDao extends ISqlDao {
	
    public void insert(UserWinningGuessThePrice record);
    
    public List<UserWinningGuessThePrice> getWinningUserList(Map<String, String> map);
    
    public Integer getCountByActAndUid(UserWinningGuessThePrice record);
}