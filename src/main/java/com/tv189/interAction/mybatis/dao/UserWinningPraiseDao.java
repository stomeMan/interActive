package com.tv189.interAction.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.tv189.interAction.mybatis.model.UserWinningPraise;

public interface UserWinningPraiseDao extends ISqlDao {

    public void insert(UserWinningPraise record);
    
    public List<UserWinningPraise> getWinningUserList(Map<String, String> map);
    
    public Integer getCountByActAndUid(UserWinningPraise record);
}