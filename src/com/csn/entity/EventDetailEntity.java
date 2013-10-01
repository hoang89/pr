package com.csn.entity;

import java.util.ArrayList;

public class EventDetailEntity extends BigEventEntity {
	private String place;
	private String address;
	private String latitude;
	private String longitude;
	private String phone;
	private String price;
	private String access;
	private String description;
	private String url;
	private ArrayList<ImageEntity> listImageEntities;
	
	
	public EventDetailEntity() {
		super();
		this.place = "";
		this.address = "";
		this.latitude = "";
		this.longitude = "";
		this.phone = "";
		this.price = "";
		this.access = "";
		this.description = "";
		this.url = "";
		this.listImageEntities = new ArrayList<ImageEntity>();
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArrayList<ImageEntity> getListImageEntities() {
		return listImageEntities;
	}
	public void setListImageEntities(ArrayList<ImageEntity> listImageEntities) {
		this.listImageEntities = listImageEntities;
	}

}
