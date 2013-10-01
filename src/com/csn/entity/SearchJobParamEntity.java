package com.csn.entity;

public class SearchJobParamEntity {
	private String category;
	private String areas;
	private String jobTypes;
	private String jobTitles;
	
	public void reset() {
		this.category = "";
		this.areas = "";
		this.jobTitles = "";
		this.jobTypes = "";
	}

	public SearchJobParamEntity() {
		super();
		this.reset();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getJobTypes() {
		return jobTypes;
	}

	public void setJobTypes(String jobTypes) {
		this.jobTypes = jobTypes;
	}

	public String getJobTitles() {
		return jobTitles;
	}

	public void setJobTitles(String jobTitles) {
		this.jobTitles = jobTitles;
	}
	
	
}
