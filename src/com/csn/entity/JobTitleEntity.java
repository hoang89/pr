package com.csn.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class JobTitleEntity implements Serializable {
	private String section;
	private ArrayList<BaseEntity> listJobTitle;
	
	public JobTitleEntity() {
		super();
		this.section = "";
		this.listJobTitle = new ArrayList<BaseEntity>();
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public ArrayList<BaseEntity> getListJobTitle() {
		return listJobTitle;
	}
	public void setListJobTitle(ArrayList<BaseEntity> listJobTitle) {
		this.listJobTitle = listJobTitle;
	}
	
	
}
