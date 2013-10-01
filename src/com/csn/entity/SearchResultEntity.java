package com.csn.entity;

import java.io.Serializable;



public class SearchResultEntity implements Serializable {
	private String shopId;
	private String shopName;
	private String genreName;
	private String description;
	private String imageUrl;
	private String newestComment;
	
	
	
	public SearchResultEntity() {
		super();
		this.shopId = "";
		this.shopName = "";
		this.genreName = "";
		this.description = "";
		this.imageUrl = "";
		this.newestComment = "";
	}
	
	public void clearNull() {
		if(this.shopId.equals("null")){
			this.shopId = "";
		}
		
		if(this.shopName.equals("null")){
			this.shopName = "";
		}
		
		if(this.genreName.equals("null")){
			this.genreName = "";
		}
		if(this.description.equals("null")){
			this.description = "";
		}
		
		if(this.newestComment.equals("null")){
			this.newestComment = "";
		}
	}
	public String getNewestComment() {
		return newestComment;
	}
	public void setNewestComment(String newestComment) {
		this.newestComment = newestComment;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
