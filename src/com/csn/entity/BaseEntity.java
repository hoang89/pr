package com.csn.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable{
	protected String id;
	protected String name;
	protected boolean selected;
	
	
	public BaseEntity() {
		super();
		this.id = "";
		this.name = "";
		this.selected = false;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
