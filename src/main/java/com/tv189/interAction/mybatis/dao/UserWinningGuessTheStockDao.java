package com.tv189.interAction.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.tv189.interAction.mybatis.model.UserWinningGuessTheStock;

public interface UserWinningGuessTheStockDao extends ISqlDao{

	public void insert(UserWinningGuessTheStock record);
    
    public List<UserWinningGuessTheStock> getWinningUserList(Map<String, String> map);
    
    public Integer getCountByActAndUid(UserWinningGuessTheStock record);
}