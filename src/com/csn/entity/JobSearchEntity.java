package com.csn.entity;

public class JobSearchEntity extends BaseEntity {
	private String salary;
	private String postType;
	
	public JobSearchEntity() {
		super();
		this.salary = "";
		this.postType = "";
	}
	
	public void removeHtml(String oldChac,String newChac) {
		name = name.replace(oldChac, newChac);
		salary = salary.replace(oldChac, newChac);
		postType = postType.replace(oldChac, newChac);
	}
	
	public void removeNull() {
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	
}
