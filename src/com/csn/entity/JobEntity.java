package com.csn.entity;

public class JobEntity {
	private String postDate;
	private String jobTitle;
	private String jobType;
	private String detail;
	private String workHour;
	private String holidays;
	private String salary;
	private String other;
	private String method;
	
	public JobEntity() {
		// TODO Auto-generated constructor stub
		this.postDate = "";
		this.jobTitle = "";
		this.jobType = "";
		this.detail = "";
		this.workHour = "";
		this.holidays = "";
		this.salary = "";
		this.other = "";
		this.method = "";
	}
	
	public void removeNull() {
		if(this.postDate == "null"){
			this.postDate = "";
		}else{
			this.postDate = this.postDate.replace("<br/>", "\n");
		}
		
		if(this.jobTitle == "null"){
			this.jobTitle = "";
		}else{
			this.jobTitle = this.jobTitle.replace("<br/>", "\n");
		}
		
		if(this.jobType == "null"){
			this.jobType = "";
		}else{
			this.jobType = this.jobType.replace("<br/>", "\n");
		}
		
		if(this.salary == "null"){
			this.salary ="";
		}else{
			this.salary = this.salary.replace("<br/>", "\n");
		}
		
		if(this.workHour == "null"){
			this.workHour = "";
		}else{
			this.workHour = this.workHour.replace("<br/>", "\n");
		}
		
		if(this.detail == "null"){
			this.detail = "";
		}else{
			this.detail = this.detail.replace("<br/>", "\n");
		}
		if(this.holidays == "null"){
			this.holidays = "";
		}else{
			this.holidays = this.holidays.replace("<br/>", "\n");
		}
		
		if(this.other == "null"){
			this.other = "";
		}else{
			this.other =this.other.replace("<br/>", "\n");
		}
		
		if(this.method == "null"){
			this.method = "";
		}else{
			this.method = this.method.replace("<br/>", "\n");
		}
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getWorkHour() {
		return workHour;
	}
	public void setWorkHour(String workHour) {
		this.workHour = workHour;
	}
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
}
