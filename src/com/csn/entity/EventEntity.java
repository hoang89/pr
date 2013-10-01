package com.csn.entity;

public class EventEntity {
	private String title;
	private String period;
	private String detail;
	private String imageUrl;
	
	public EventEntity() {
		// TODO Auto-generated constructor stub
		this.title = "";
		this.period = "";
		this.detail = "";
		this.imageUrl = "";
	}
	
	public void removeNull() {
		if(this.title == "null"){
			this.title = "";
		}
		
		if(this.period == "null"){
			this.period = "";
		}
		
		if(this.detail == "null"){
			this.detail = "";
		}
		
		if(this.imageUrl == "null"){
			this.imageUrl = "";
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
