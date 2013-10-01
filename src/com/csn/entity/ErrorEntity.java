package com.csn.entity;

public class ErrorEntity {
	private String code;
	private String message;
	
	public ErrorEntity() {
		this.code = "";
		this.message = "";
	}
	
	public void removeNull() {
		if(this.code == "null"){
			this.code = "";
		}
		
		if(this.message == "null"){
			this.message = "";
		}
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
