package com.csn.datasource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;
import android.util.Log;

import com.csn.entity.AgeEntity;
import com.csn.entity.AreaEntity;
import com.csn.entity.AvgCostEntity;
import com.csn.entity.BaseEntity;
import com.csn.entity.BigEventEntity;
import com.csn.entity.BusinessTimeEntity;
import com.csn.entity.CategoryEntity;
import com.csn.entity.CommentEntity;
import com.csn.entity.CouponEntity;
import com.csn.entity.CreditCardEntity;
import com.csn.entity.D011ImageEntity;
import com.csn.entity.EventDetailEntity;
import com.csn.entity.EventEntity;
import com.csn.entity.FacilityEntity;
import com.csn.entity.GenreEntity;
import com.csn.entity.ImageEntity;
import com.csn.entity.JobEntity;
import com.csn.entity.JobSearchEntity;
import com.csn.entity.JobTitleEntity;
import com.csn.entity.NewsEntity;
import com.csn.entity.SearchResultEntity;
import com.csn.entity.ServiceEntity;
import com.csn.entity.ShopDetailEntity;

public class JsonParser {
	public static String BASE_IMAGE = "http://49.212.169.177/puripuri/shop_admin/img/shop_admin/";
    public static String BASE_IMAGE_2 = "http://49.212.169.177/puripuri/site_admin/img/site_admin/";

	public static ArrayList<NewsEntity> getNews(String response) {
		ArrayList<NewsEntity> listEntities = new ArrayList<NewsEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					NewsEntity newsEntity = new NewsEntity();
					newsEntity.setDate(jsonObject.getString("news_date"));
					newsEntity.setText(jsonObject.getString("text"));
					listEntities.add(newsEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listEntities;
	}

	public static ArrayList<AreaEntity> getAreas(String response) {
		ArrayList<AreaEntity> listEntities = new ArrayList<AreaEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					AreaEntity areaEntity = new AreaEntity();
					areaEntity.setId(jsonObject.getString("area_id"));
					areaEntity.setName(jsonObject.getString("area_name"));
					areaEntity.setSelected(false);
					listEntities.add(areaEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listEntities;
	}
	
	public static ArrayList<GenreEntity> getGenres(String response) {
		ArrayList<GenreEntity> listEntities = new ArrayList<GenreEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);

					GenreEntity genreEntity = new GenreEntity();
					genreEntity.setId(jsonObject.getString("genre_id"));
					genreEntity.setName(jsonObject.getString("genre_name"));
					genreEntity.setSelected(false);
					listEntities.add(genreEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listEntities;
	}

	public static ArrayList<SearchResultEntity> getSearchResults(String response) {
		ArrayList<SearchResultEntity> listEntities = new ArrayList<SearchResultEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					SearchResultEntity searchResultEntity = new SearchResultEntity();
					searchResultEntity.setShopId(jsonObject
							.getString("shop_id"));
					searchResultEntity.setShopName(jsonObject
							.getString("shop_name"));
					searchResultEntity.setGenreName(jsonObject
							.getString("genre_name"));
					searchResultEntity.setImageUrl(BASE_IMAGE+jsonObject
							.getString("image_url"));
					searchResultEntity.setDescription(jsonObject
							.getString("description"));
					searchResultEntity.setNewestComment(jsonObject.getString("newest_comment"));
					searchResultEntity.clearNull();
					listEntities.add(searchResultEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return listEntities;
	}

	public static ArrayList<BaseEntity> getAges(String response) {
		ArrayList<BaseEntity> listEntities = new ArrayList<BaseEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					AgeEntity ageEntity = new AgeEntity();
					ageEntity.setId(jsonObject.getString("age_id"));
					ageEntity.setName(jsonObject.getString("age_name"));
					ageEntity.setSelected(false);
					listEntities.add(ageEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	public static ArrayList<CouponEntity> getCoupons(String response) {
		ArrayList<CouponEntity> listEntities = new ArrayList<CouponEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					CouponEntity couponEntity = new CouponEntity();
					couponEntity.setTitle(jsonObject.getString("title"));
					couponEntity.setEffectiveBegin(jsonObject.getString("effective_begin"));
					couponEntity.setEffectiveEnd(jsonObject.getString("effective_end"));
					couponEntity.setUseCondition(jsonObject.getString("use_condition"));
					couponEntity.setDetail(jsonObject.getString("detail"));
					couponEntity.setNote(jsonObject.getString("note"));
					couponEntity.setImage(BASE_IMAGE+jsonObject.getString("image_url"));
					listEntities.add(couponEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	public static ArrayList<BaseEntity> getAvgCosts(String response) {
		ArrayList<BaseEntity> listEntities = new ArrayList<BaseEntity>();
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					AvgCostEntity avgCostEntity = new AvgCostEntity();
					avgCostEntity.setId(jsonObject.getString("avg_cost_id"));
					avgCostEntity.setName(jsonObject.getString("avg_cost_name"));
					avgCostEntity.setSelected(false);
					listEntities.add(avgCostEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<BusinessTimeEntity> getBusinessTime(String response) {
		ArrayList<BusinessTimeEntity> listEntities = new ArrayList<BusinessTimeEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					BusinessTimeEntity businessTimeEntity = new BusinessTimeEntity();
					businessTimeEntity.setId(jsonObject.getString("businesstime_id"));
					businessTimeEntity.setName(jsonObject.getString("businesstime_name"));
					businessTimeEntity.setSelected(false);
					listEntities.add(businessTimeEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<BaseEntity> getFacilities(String response) {
		ArrayList<BaseEntity> listEntities = new ArrayList<BaseEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					BaseEntity facilityEntity = new BaseEntity();
					facilityEntity.setId(jsonObject.getString("order_num"));
					facilityEntity.setName(jsonObject.getString("facility_name"));
					facilityEntity.setSelected(false);
					listEntities.add(facilityEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<BaseEntity> getServices(String response) {
		ArrayList<BaseEntity> listEntities = new ArrayList<BaseEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					ServiceEntity serviceEntity = new ServiceEntity();
					serviceEntity.setId(jsonObject.getString("order_num"));
					serviceEntity.setName(jsonObject.getString("service_name"));
					serviceEntity.setSelected(false);
					listEntities.add(serviceEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	public static ArrayList<ArrayList<D011ImageEntity>> getMenus(String response) {
		ArrayList<ArrayList<D011ImageEntity>> listEntities= new ArrayList<ArrayList<D011ImageEntity>>();
		if(response == null){
			return listEntities;
		}
		response = Html.fromHtml(response).toString();
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONObject json = jsonObjects.getJSONObject("data");
				
				for (int i = 0; i < 3; i++) {
					ArrayList<D011ImageEntity> listMenu = new ArrayList<D011ImageEntity>();
					JSONArray menus1 = json.getJSONArray("menu0"+(i+1));
					for (int j = 0; j < menus1.length(); j++) {
						D011ImageEntity d011ImageEntity = new D011ImageEntity();
						d011ImageEntity.setUrl(BASE_IMAGE+menus1.getJSONObject(j).getString("image_url"));
						d011ImageEntity.setDescription(menus1.getJSONObject(j).getString("description"));
						d011ImageEntity.setName(menus1.getJSONObject(j).getString("item_name"));
						d011ImageEntity.setPrice(menus1.getJSONObject(j).getString("price"));
						d011ImageEntity.setCategory_name(menus1.getJSONObject(j).getString("category_name"));
						d011ImageEntity.removeNull();
						listMenu.add(d011ImageEntity);
					}
					listEntities.add(listMenu);
				}
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	public static ArrayList<CommentEntity> getComments(String response) {
		ArrayList<CommentEntity> listEntities = new ArrayList<CommentEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					CommentEntity commentEntity = new CommentEntity();
					commentEntity.setShopId(jsonObject.getString("shop_id"));
					commentEntity.setComment(jsonObject.getString("text"));
					commentEntity.setDate(jsonObject.getString("post_date"));
					listEntities.add(commentEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	public static ShopDetailEntity getShopDetail(String res){
		ShopDetailEntity shopDetailEntity =  new ShopDetailEntity();
		if(res == null){
			return shopDetailEntity;
		}
		res =Html.fromHtml(res).toString();
		try {
			JSONObject jsonObject =  new JSONObject(res);
			if(jsonObject.getInt("error_code") == 0){
				JSONObject jsonResult = jsonObject.getJSONObject("data");
				shopDetailEntity.setId(jsonResult.getString("shop_id"));
				shopDetailEntity.setName(jsonResult.getString("name"));
				
				CategoryEntity categoryEntity = new CategoryEntity();
				categoryEntity.setId(jsonResult.getString("category_id"));
				categoryEntity.setName(jsonResult.getString("category_name"));
				shopDetailEntity.setCategoryEntity(categoryEntity);
				
				GenreEntity genreEntity = new GenreEntity();
				genreEntity.setId(jsonResult.getString("genre_id"));
				genreEntity.setName(jsonResult.getString("genre_name"));
				shopDetailEntity.setGenreEntity(genreEntity);
				
				
				shopDetailEntity.setNote(jsonResult.getString("note"));
				shopDetailEntity.setAddress(jsonResult.getString("address"));
				shopDetailEntity.setFax(jsonResult.getString("fax"));
				shopDetailEntity.setPhone(jsonResult.getString("phone"));
				shopDetailEntity.setOpeningHour(jsonResult.getString("opening_hour"));
				shopDetailEntity.setLastOrder(jsonResult.getString("last_order"));
				shopDetailEntity.setHolidays(jsonResult.getString("holidays"));
				shopDetailEntity.setSeats(jsonResult.getString("seats"));
				shopDetailEntity.setDescription(jsonResult.getString("description"));
				
				AvgCostEntity avgCostEntity = new AvgCostEntity();
				avgCostEntity.setId("1");
				avgCostEntity.setName(jsonResult.getString("avg_cost_name"));
				
				shopDetailEntity.setAvgCostEntity(avgCostEntity);
				shopDetailEntity.setParking(jsonResult.getString("parking"));
				shopDetailEntity.setUrl(jsonResult.getString("url"));
				shopDetailEntity.setImageUrl(BASE_IMAGE+jsonResult.getString("image_url"));
				shopDetailEntity.setLatitude(jsonResult.getString("latitude"));
				shopDetailEntity.setLongitude(jsonResult.getString("longitude"));
				shopDetailEntity.setTwAccount(jsonResult.getString("tw_timeline_url"));
				//shopDetailEntity.setTwAccount(jsonResult.getString("tw_password"));
				shopDetailEntity.setFbAccount(jsonResult.getString("fb_fan_page_url"));
				
				JSONArray listCC = jsonResult.getJSONArray("creditcards");
				ArrayList<CreditCardEntity> listCardEntities = new ArrayList<CreditCardEntity>();
				for (int i = 0; i < listCC.length(); i++) {
					CreditCardEntity cardEntity = new CreditCardEntity();
					cardEntity.setId("id");
					cardEntity.setName(listCC.getString(i));
					listCardEntities.add(cardEntity);
				}
				
				shopDetailEntity.setCreditCardEntities(listCardEntities);
				
				JSONArray listFacility = jsonResult.getJSONArray("facilities");
				ArrayList<FacilityEntity> listFacilityEntities = new ArrayList<FacilityEntity>();
				for (int i = 0; i < listFacility.length(); i++) {
					FacilityEntity facilityEntity = new FacilityEntity();
					facilityEntity.setId(listFacility.getJSONObject(i).getString("facility_id"));
					facilityEntity.setName(listFacility.getJSONObject(i).getString("facility_name"));
					facilityEntity.setFlag(listFacility.getJSONObject(i).getString("flag"));
					listFacilityEntities.add(facilityEntity);
				}
				
				shopDetailEntity.setFacilityEntities(listFacilityEntities);
				
				JSONArray listServices = jsonResult.getJSONArray("services");
				ArrayList<ServiceEntity> listServiceEntities = new ArrayList<ServiceEntity>();
				for (int i = 0; i < listServices.length(); i++) {
					ServiceEntity serviceEntity = new ServiceEntity();
					serviceEntity.setId(listServices.getJSONObject(i).getString("service_id"));
					serviceEntity.setName(listServices.getJSONObject(i).getString("service_name"));
					serviceEntity.setFlag(listServices.getJSONObject(i).getString("flag"));
					listServiceEntities.add(serviceEntity);
				}
				
				shopDetailEntity.setServiceEntities(listServiceEntities);
				
				JSONArray listAges = jsonResult.getJSONArray("ages");
				ArrayList<AgeEntity> listAgeEntities = new ArrayList<AgeEntity>();
				for (int i = 0; i < listAges.length(); i++) {
					AgeEntity ageEntity = new AgeEntity();
					ageEntity.setId(""+i);
					ageEntity.setName(listAges.getString(i));
					ageEntity.setSelected(false);
					listAgeEntities.add(ageEntity);
				}
				
				shopDetailEntity.setAgeEntities(listAgeEntities);
				
				JSONArray listImages = jsonResult.getJSONArray("exteriors");
				ArrayList<ImageEntity> listImageEntities = new ArrayList<ImageEntity>();
				for (int i = 0; i < listImages.length(); i++) {
					ImageEntity imageEntity = new ImageEntity();
					imageEntity.setUrl(BASE_IMAGE+listImages.getJSONObject(i).getString("image_url"));
					imageEntity.setDescription(listImages.getJSONObject(i).getString("text"));
					Log.e("DES",imageEntity.getDescription());
					listImageEntities.add(imageEntity);
				}
				
				shopDetailEntity.setImageEntities(listImageEntities);
				shopDetailEntity.removeHtml("<br/>", "\n");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return shopDetailEntity;
	}
	
	public static ArrayList<JobEntity> getJobEntities(String response) {
		ArrayList<JobEntity> listEntities = new ArrayList<JobEntity>();
		if(response == null){
			return listEntities;
		}
		response = Html.fromHtml(response).toString();
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					JobEntity jobEntity = new JobEntity();
					jobEntity.setPostDate(jsonObject.getString("post_date"));
					jobEntity.setJobTitle(jsonObject.getString("job_title"));
					jobEntity.setJobType(jsonObject.getString("job_type"));
					jobEntity.setDetail(jsonObject.getString("detail"));
					jobEntity.setWorkHour(jsonObject.getString("work_hours"));
					jobEntity.setHolidays(jsonObject.getString("holidays"));
					jobEntity.setOther(jsonObject.getString("other"));
					jobEntity.setMethod(jsonObject.getString("method"));
					jobEntity.removeNull();
					listEntities.add(jobEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<EventEntity> getEventEntities(String response) {
		ArrayList<EventEntity> listEntities = new ArrayList<EventEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					EventEntity eventEntity = new EventEntity();
					eventEntity.setTitle(jsonObject.getString("title"));
					eventEntity.setPeriod(jsonObject.getString("effective_period"));
					eventEntity.setDetail(jsonObject.getString("detail"));
					eventEntity.setImageUrl(BASE_IMAGE+jsonObject.getString("image_url"));
					listEntities.add(eventEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<BaseEntity> getJobTypeEntities(String response) {
		ArrayList<BaseEntity> listEntities = new ArrayList<BaseEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					BaseEntity jobTypeEntity = new BaseEntity();
					jobTypeEntity.setId(jsonObject.getString("id"));
					jobTypeEntity.setName(jsonObject.getString("job_type"));
					listEntities.add(jobTypeEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<JobTitleEntity> getJobTitleEntities(String response) {
		ArrayList<JobTitleEntity> listEntities = new ArrayList<JobTitleEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					JobTitleEntity jobTitleEntity = new JobTitleEntity();
					jobTitleEntity.setSection(jsonObject.getString("section_name"));
					
					JSONArray itemArray = jsonObject.getJSONArray("items");
					for(int j=0;j<itemArray.length();j++){
						JSONObject items = itemArray.getJSONObject(j);
						BaseEntity baseEntity = new BaseEntity();
						baseEntity.setId(items.getString("item_id"));
						baseEntity.setName(items.getString("item_name"));
						
						jobTitleEntity.getListJobTitle().add(baseEntity);
					}
					
					listEntities.add(jobTitleEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<JobSearchEntity> getJobSearchResults(String response) {
		ArrayList<JobSearchEntity> listEntities = new ArrayList<JobSearchEntity>();
		if(response == null){
			return listEntities;
		}
		response =Html.fromHtml(response).toString();
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					JobSearchEntity jobSearchEntity = new JobSearchEntity();
					jobSearchEntity.setId(jsonObject.getString("shop_id"));
					jobSearchEntity.setName(jsonObject.getString("shop_name"));
					jobSearchEntity.setSalary(jsonObject.getString("salary"));
					jobSearchEntity.setPostType(jsonObject.getString("jobposting_type"));
					jobSearchEntity.removeHtml("<br/>", "\n");
					listEntities.add(jobSearchEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	
	public static ArrayList<BigEventEntity> getBigEventsList(String response) {
		ArrayList<BigEventEntity> listEntities = new ArrayList<BigEventEntity>();
		if(response == null){
			return listEntities;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
				JSONArray jsonArray = jsonObjects.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					BigEventEntity bigEventEntity = new BigEventEntity();
					bigEventEntity.setId(jsonObject.getString("big_event_id"));
					bigEventEntity.setName(jsonObject.getString("name"));
					bigEventEntity.setDate(jsonObject.getString("display_date"));
					bigEventEntity.setTime(jsonObject.getString("event_time"));
					bigEventEntity.setPeople(jsonObject.getString("people"));
					bigEventEntity.setImage(BASE_IMAGE_2+jsonObject.getString("image_url"));
					listEntities.add(bigEventEntity);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listEntities;
	}
	
	public static EventDetailEntity getBigEventsDetail(String response) {
		EventDetailEntity eventDetailEntity = new EventDetailEntity();
		if(response == null){
			return eventDetailEntity;
		}
		try {
			JSONObject jsonObjects = new JSONObject(response);
			if (jsonObjects.getInt("error_code") == 0) {
					JSONObject jsonObject = jsonObjects.getJSONObject("data");
					
					eventDetailEntity.setId(jsonObject.getString("big_event_id"));
					eventDetailEntity.setName(jsonObject.getString("name"));
					eventDetailEntity.setDate(jsonObject.getString("display_date"));
					eventDetailEntity.setTime(jsonObject.getString("event_time"));
					eventDetailEntity.setPeople(jsonObject.getString("people"));
					eventDetailEntity.setPlace(jsonObject.getString("place"));
					eventDetailEntity.setAccess(jsonObject.getString("access"));
					eventDetailEntity.setAddress(jsonObject.getString("address"));
					eventDetailEntity.setLatitude(jsonObject.getString("latitude"));
					eventDetailEntity.setLongitude(jsonObject.getString("longitude"));
					eventDetailEntity.setPhone(jsonObject.getString("phone"));
					eventDetailEntity.setPrice(jsonObject.getString("price"));
					eventDetailEntity.setDescription(jsonObject.getString("description"));
					eventDetailEntity.setUrl(jsonObject.getString("url"));
					eventDetailEntity.setImage(BASE_IMAGE_2+jsonObject.getString("image_url"));
					JSONArray galery = jsonObject.getJSONArray("gallery");
					ArrayList< ImageEntity> listImageEntities = new ArrayList<ImageEntity>();
					for(int i=0;i<galery.length();i++){
						ImageEntity imageEntity = new ImageEntity();
						imageEntity.setUrl(BASE_IMAGE_2+galery.getJSONObject(i).getString("image_url"));
						listImageEntities.add(imageEntity);
					}
					eventDetailEntity.setListImageEntities(listImageEntities);
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return eventDetailEntity;
	}
	
	public static List<ShopDetailEntity> getNearByShop(String res) {
		List<ShopDetailEntity> lShopDetailEntities = new ArrayList<ShopDetailEntity>();
		try {
			JSONObject jsonObject = new JSONObject(res);
			JSONArray data = jsonObject.getJSONArray("data");

			for (int i = 0; i < data.length(); i++) {
				JSONObject jsonShop = data.getJSONObject(i);
				ShopDetailEntity shopDetailEntity = new ShopDetailEntity();
				shopDetailEntity.setId(jsonShop.getString("shop_id"));
				shopDetailEntity.setLatitude(jsonShop.getString("latitude"));
				shopDetailEntity.setLongitude(jsonShop.getString("longitude"));
				shopDetailEntity.setName(jsonShop.getString("name"));
				shopDetailEntity.setAddress(jsonShop.getString("address"));
				lShopDetailEntities.add(shopDetailEntity);
			}
		} catch (Exception e) {
			Log.e("Error", "Parse", e);
		}
		return lShopDetailEntities;
	}
	
	public static ArrayList<CommentEntity> getTwitterComment(String response, ArrayList<CommentEntity> listEntities) {
		if(response == null){
			return listEntities;
		}
		JSONArray jsonArray;
		final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		try {
			jsonArray = new JSONArray(response);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				CommentEntity commentEntity = new CommentEntity();
				commentEntity.setShopId("");
				commentEntity.setComment(jsonObject.getString("text"));
				commentEntity.setDate(jsonObject.getString("created_at"));
				SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
				sf.setLenient(true);
				try {
					Date date = sf.parse(commentEntity.getDate());
					SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
					
					commentEntity.setDate(s.format(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listEntities.add(commentEntity);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listEntities;
	}
}
