package com.tv189.interAction.httpRes;

public class BasicResponse {
	private Integer code;
	private String msg;
	private Object info;
	
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
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public BasicResponse(){
		
	}
	public BasicResponse(Integer code, String msg, Object info) {
		super();
		this.code = code;
		this.msg = msg;
		this.info = info;
	}
}
