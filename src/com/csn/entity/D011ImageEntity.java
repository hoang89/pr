package com.csn.entity;

public class D011ImageEntity extends ImageEntity {
	private String name;
	private String price;
	private String category_name;
	
	

	public D011ImageEntity() {
		// TODO Auto-generated constructor stub
		super();
		this.name = "";
		this.price = "";
		this.category_name = "";
	}
	
	public void removeNull() {
		super.removeNull();
		if(this.name.equals("null")){
			this.name = "";
		}else{
			this.name = name.replace("<br/>", "\n");
		}
		
		if(this.price.equals("null")){
			this.price = "";
		}else{
			this.price = this.price.replace("<br/>", "\n");
		}
		
		if(this.category_name.equals("null")){
			this.category_name = "";
		}else{
			this.category_name = this.category_name.replace("<br/>", "\n");
		}
		
		
	}
	
	
	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
