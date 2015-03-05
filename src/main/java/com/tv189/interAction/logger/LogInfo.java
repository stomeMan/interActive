package com.tv189.interAction.logger;

import org.springframework.stereotype.Repository;

/**
 * @author xuezhiyu
 *
 * 2014-6-18
 */
@Repository
public class LogInfo {
	private String logName;
	private String logType;
	private String logContent;
	
	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	
	public LogInfo(String logName,String logType,String logContent){
		this.logName = logName;
		this.logType = logType;
		this.logContent = logContent;
	}
	public LogInfo(){
		
	}
}
