package com.csn.entity;

public class BigEventEntity extends BaseEntity {
	protected String date;
	protected String time;
	protected String people;
	protected String image;
	public BigEventEntity() {
		super();
		this.date = "";
		this.time = "";
		this.people = "";
		this.image = "";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
}
