package com.csn.entity;

import java.io.Serializable;

public class ImageEntity implements Serializable{
	protected String url;
	protected String description;
	public ImageEntity() {
		// TODO Auto-generated constructor stub
		this.url = "";
		this.description = "";
	}
	
	protected void removeNull() {
		if(this.url.equals("null")){
			this.url = "";
		}
		
		if(this.description.equals("null")){
			this.description = "";
		}else{
			this.description = description.replace("<br/>", "\n");
		}
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
