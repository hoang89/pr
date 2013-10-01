package com.csn.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopDetailEntity implements Serializable{
	private String id;
	private String name;
	private String description;
	private CategoryEntity categoryEntity;
	private GenreEntity genreEntity;
	private String address;
	private String fax;
	private String openingHour;
	private String lastOrder;
	private String holidays;
	private String seats;
	private AvgCostEntity avgCostEntity;
	private String parking;
	private String url;
	private String imageUrl;
	private String phone;
	private ArrayList<CreditCardEntity> creditCardEntities;
	private ArrayList<FacilityEntity> facilityEntities;
	private ArrayList<ServiceEntity> serviceEntities;
	private ArrayList<AgeEntity> ageEntities;
	private ArrayList<ImageEntity> imageEntities;
	private String latitude;
	private String longitude;
	private String twAccount;
	private String twPassword;
	private String fbAccount;
	private String note;
	
	public ShopDetailEntity() {
		super();
		this.id = "";
		this.name = "";
		this.description = "";
		this.categoryEntity = new CategoryEntity();
		this.genreEntity = new GenreEntity();
		this.address = "";
		this.fax = "";
		this.openingHour = "";
		this.lastOrder = "";
		this.holidays = "";
		this.seats = "";
		this.avgCostEntity = new AvgCostEntity();
		this.parking = "";
		this.url = "";
		this.imageUrl = "";
		this.phone = "";
		this.creditCardEntities = new ArrayList<CreditCardEntity>();
		this.facilityEntities = new ArrayList<FacilityEntity>();
		this.serviceEntities = new ArrayList<ServiceEntity>();
		this.ageEntities = new ArrayList<AgeEntity>();
		this.imageEntities = new ArrayList<ImageEntity>();
		this.latitude = "0";
		this.longitude = "0";
		this.twAccount = "";
		this.twPassword = "";
		this.fbAccount = "";
		this.note = "";
	}
	
	
	
	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
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



	public String getTwAccount() {
		return twAccount;
	}



	public void setTwAccount(String twAccount) {
		this.twAccount = twAccount;
	}



	public String getTwPassword() {
		return twPassword;
	}



	public void setTwPassword(String twPassword) {
		this.twPassword = twPassword;
	}



	public String getFbAccount() {
		return fbAccount;
	}



	public void setFbAccount(String fbAccount) {
		this.fbAccount = fbAccount;
	}



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<ImageEntity> getImageEntities() {
		return imageEntities;
	}
	public void setImageEntities(ArrayList<ImageEntity> imageEntities) {
		this.imageEntities = imageEntities;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ArrayList<AgeEntity> getAgeEntities() {
		return ageEntities;
	}
	public void setAgeEntities(ArrayList<AgeEntity> ageEntities) {
		this.ageEntities = ageEntities;
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
	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}
	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}
	public GenreEntity getGenreEntity() {
		return genreEntity;
	}
	public void setGenreEntity(GenreEntity genreEntity) {
		this.genreEntity = genreEntity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getOpeningHour() {
		return openingHour;
	}
	public void setOpeningHour(String openingHour) {
		this.openingHour = openingHour;
	}
	public String getLastOrder() {
		return lastOrder;
	}
	public void setLastOrder(String lastOrder) {
		this.lastOrder = lastOrder;
	}
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public AvgCostEntity getAvgCostEntity() {
		return avgCostEntity;
	}
	public void setAvgCostEntity(AvgCostEntity avgCostEntity) {
		this.avgCostEntity = avgCostEntity;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public ArrayList<CreditCardEntity> getCreditCardEntities() {
		return creditCardEntities;
	}
	public void setCreditCardEntities(ArrayList<CreditCardEntity> creditCardEntities) {
		this.creditCardEntities = creditCardEntities;
	}
	public ArrayList<FacilityEntity> getFacilityEntities() {
		return facilityEntities;
	}
	public void setFacilityEntities(ArrayList<FacilityEntity> facilityEntities) {
		this.facilityEntities = facilityEntities;
	}
	public ArrayList<ServiceEntity> getServiceEntities() {
		return serviceEntities;
	}
	public void setServiceEntities(ArrayList<ServiceEntity> serviceEntities) {
		this.serviceEntities = serviceEntities;
	}
	
	public void removeHtml(String oldChac,String newChac) {
		name = name.replace(oldChac, newChac);
		description = description.replace(oldChac, newChac);
		address = address.replace(oldChac, newChac);
		openingHour = openingHour.replace(oldChac, newChac);
	}
	
	
}
