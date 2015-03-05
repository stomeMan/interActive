package com.tv189.interAction.httpRes;

import java.util.Map;

public class BasicResponseMap {
	private Integer code;
	private String msg;
	private Map<String,Object> info;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String,Object> getInfo() {
		return info;
	}
	public void setInfo(Map<String,Object> info) {
		this.info = info;
	}
}
