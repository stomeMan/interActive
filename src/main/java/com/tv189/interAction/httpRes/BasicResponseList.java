package com.tv189.interAction.httpRes;

import java.util.List;
import java.util.Map;

public class BasicResponseList {
	private Integer code;
	private String msg;
	private List<Map<String,Object>> info;
	
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
	public List<Map<String, Object>> getInfo() {
		return info;
	}
	public void setInfo(List<Map<String, Object>> info) {
		this.info = info;
	}

}
