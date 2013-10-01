package com.csn.entity;

public class SearchParameterEntity {
	private String keyword;
	private String comment;
	private String areas;
	private String genres;
	private String avgCosts;
	private String facilities;
	private String services;
	private String businessTimes;
	private String ages;
	private String coupon;
	private String category;
	
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public SearchParameterEntity() {
		this.reset();
		this.category = "";
	}
	
	public void reset() {
		this.keyword = "";
		this.comment = "";
		this.areas = "";
		this.genres = "";
		this.avgCosts = "";
		this.facilities = "";
		this.services = "";
		this.businessTimes = "";
		this.ages = "";
		this.coupon = "0";
	}
	
	
	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getAvgCosts() {
		return avgCosts;
	}
	public void setAvgCosts(String avgCosts) {
		this.avgCosts = avgCosts;
	}
	public String getFacilities() {
		return facilities;
	}
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getBusinessTimes() {
		return businessTimes;
	}
	public void setBusinessTimes(String businessTimes) {
		this.businessTimes = businessTimes;
	}
	public String getAges() {
		return ages;
	}
	public void setAges(String ages) {
		this.ages = ages;
	}
	
	
}
