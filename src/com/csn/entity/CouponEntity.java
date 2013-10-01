package com.csn.entity;

import java.io.Serializable;

public class CouponEntity implements Serializable{
	private String title;
	private String effectiveBegin;
	private String effectiveEnd;
	private String useCondition;
	private String detail;
	private String note;
	private String image;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEffectiveBegin() {
		return effectiveBegin;
	}
	public void setEffectiveBegin(String effectiveBegin) {
		this.effectiveBegin = effectiveBegin;
	}
	public String getEffectiveEnd() {
		return effectiveEnd;
	}
	public void setEffectiveEnd(String effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}
	public String getUseCondition() {
		return useCondition;
	}
	public void setUseCondition(String useCondition) {
		this.useCondition = useCondition;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
