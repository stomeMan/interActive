package com.tv189.interAction.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.tv189.interAction.mybatis.model.LiveProgramInfo;

public interface LiveProgramDao extends ISqlDao{

	public List<LiveProgramInfo> getLiveProgramsByLiveDate(LiveProgramInfo lpi) ;

	public List<LiveProgramInfo> getLiveProgramByLiveDate(
			Map<String, String> map);

}
