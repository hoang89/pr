package com.csn.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.csn.datasource.Constant;
import com.csn.datasource.JsonParser;
import com.csn.entity.ShopDetailEntity;
import com.csn.puripuri.AppBaseActivity;
import com.csn.puripuri.ImageViewActivity;
import com.csn.puripuri.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class D001Fragment1 extends Fragment {
	private WebView mWebView;
	private ShopDetailEntity shopDetailEntity;
	private TextView d001_add;
	private TextView d001_fax;
	private TextView d001_time;
	private TextView d001_lastOrder;
	private TextView d001_holidays;
	private TextView d001_bussinessTime;
	private TextView d001_cards;
	private TextView d001_parking;
	private TextView d001_homes;
	private TextView d001_seats;
	private TextView d001_ages;
	private TextView d001_note;
	private LinearLayout listDevices;
	private LinearLayout listServices;
	private AQuery aQuery;
	private String shopId;
	private int loadImageSize;
	private LinearLayout d001_other_lo;
	private String url = "http://www.facebook.com/plugins/likebox.php?href=https%3A%2F%2F"
			+ "www.facebook.com%2FFacebookJapan&width=400&height=290&"
			+ "colorscheme=light&show_faces=true&border_color&stream=false&header=true&appId=225429780912342";

	public D001Fragment1() {
	}

	public ShopDetailEntity getShopDetailEntity() {
		return shopDetailEntity;
	}

	public void setShopDetailEntity(ShopDetailEntity shopDetailEntity) {
		this.shopDetailEntity = shopDetailEntity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadImageSize = getActivity().getWindowManager().getDefaultDisplay().getWidth() /3;
		
		
	}


	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.d001_fragment1, null);
		getUIComponent(v);
		WebViewClient yourWebClient = new WebViewClient()
	       {
	           // Override page so it's load on my view only
	           @Override
	           public boolean shouldOverrideUrlLoading(WebView  view, String  url)
	           {
	            // This line we let me load only pages inside Firstdroid Webpage
	            if ( url.contains("facebook") == true )
	               // Load new URL Don't override URL Link
	               return false;
	             
	            // Return true to override url loading (In this case do nothing).
	            return true;
	           }
	       };
	       
	       
	   // Get Web view
	   mWebView = (WebView) v.findViewById( R.id.fb_webview ); 
	   mWebView.getSettings().setJavaScriptEnabled(true);  
	   mWebView.setWebViewClient(yourWebClient);
	   mWebView.setVerticalScrollBarEnabled(false);
	   mWebView.setOnTouchListener(new View.OnTouchListener() {
	               public boolean onTouch(View v, MotionEvent event) {
	   				return (event.getAction() == MotionEvent.ACTION_MOVE);
	               }
	           });

	       
		shopId = getActivity().getIntent().getStringExtra("shopid");
		aQuery = new AQuery(v);
		
		if (shopDetailEntity != null) {
			setUIComponent();
		} else {
			getShopInfo();
		}
		return v;
	}
	/**
	 * Get Shop in for ma ti on detail to fill layout
	 * API: get_shop_detail.php
	 * parameter: shop_id id of shop want to get
	 */
	public void getShopInfo() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("shop_id", shopId);
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(AppBaseActivity.BASE_URL + "get_shop_details.php",
				requestParams, new AsyncHttpResponseHandler() {
					
//					@SuppressLint("SetJavaScriptEnabled")
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						shopDetailEntity = JsonParser.getShopDetail(res);
						if(!shopDetailEntity.getFbAccount().equals("")){
							Log.e("URL",shopDetailEntity.getFbAccount());
							mWebView.loadUrl(shopDetailEntity.getFbAccount());
						}else{
							d001_other_lo.setVisibility(View.GONE);
						}
						setUIComponent();
					}
				});
	}
	
	/**
	 * show shop detail info to view
	 */
	public void setUIComponent() {
		if (shopDetailEntity != null) {
			d001_add.setText(shopDetailEntity.getAddress());
			d001_bussinessTime.setText(shopDetailEntity.getAvgCostEntity().getName());
			String cards = "";
			for (int i = 0; i < shopDetailEntity.getCreditCardEntities().size(); i++) {
				cards += shopDetailEntity.getCreditCardEntities().get(i).getName() + "/";
			}
			if(!cards.equals("")){
			cards = cards.substring(0, cards.lastIndexOf("/"));
			d001_cards.setText(cards);
			}

			d001_fax.setText(shopDetailEntity.getFax());
			d001_holidays.setText(shopDetailEntity.getHolidays());
			d001_homes.setText(shopDetailEntity.getUrl());
			d001_homes.setTextColor(Color.BLUE);
			d001_homes.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (shopDetailEntity.getUrl() != "") {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri
								.parse(shopDetailEntity.getUrl()));
						startActivity(intent);
					}
				}
			});
			
			d001_lastOrder.setText(shopDetailEntity.getLastOrder());
			d001_parking.setText(shopDetailEntity.getParking());
			d001_note.setText(shopDetailEntity.getNote());
			d001_time.setText(shopDetailEntity.getOpeningHour());
			d001_seats.setText(shopDetailEntity.getSeats());
			String ageString = "";
			for (int i = 0; i < shopDetailEntity.getAgeEntities().size(); i++) {
				ageString += shopDetailEntity.getAgeEntities().get(i).getName() + "/";
			}
			if (!ageString.equals("")) {
				ageString = ageString.substring(0, ageString.lastIndexOf("/"));
				d001_ages.setText(ageString);
			}
			setImages();
			setShopServices();
			setShopDevices();
		}
	}

	public void setImages() {
		if (shopDetailEntity.getImageEntities().size() >= 1) {
			aQuery.id(R.id.shopImage1).visible().image(shopDetailEntity.getImageEntities().get(0).getUrl(),Constant.memCache,true,loadImageSize,0,null,0,AQuery.RATIO_PRESERVE);
			aQuery.id(R.id.shopImage1).getImageView().setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(getActivity(),ImageViewActivity.class);
							intent.putExtra("listImages", shopDetailEntity.getImageEntities());
							intent.putExtra("current", 0);
							startActivity(intent);
						}
					});
		} else {
			aQuery.id(R.id.shopImage1).gone();
		}

		if (shopDetailEntity.getImageEntities().size() >= 2) {
			aQuery.id(R.id.shopImage2).visible().image(shopDetailEntity.getImageEntities().get(1).getUrl(),Constant.memCache,Constant.fileCache,loadImageSize,0,null,0,AQuery.RATIO_PRESERVE);
			aQuery.id(R.id.shopImage2).getImageView().setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(getActivity(),ImageViewActivity.class);
							intent.putExtra("listImages", shopDetailEntity.getImageEntities());
							intent.putExtra("current", 1);
							startActivity(intent);
							
						}
					});
		} else {
			aQuery.id(R.id.shopImage2).gone();
		}
		if (shopDetailEntity.getImageEntities().size() >= 3) {
			aQuery.id(R.id.shopImage3).visible().image(shopDetailEntity.getImageEntities().get(2).getUrl(),Constant.memCache,Constant.fileCache,loadImageSize,0,null,0,AQuery.RATIO_PRESERVE);
			aQuery.id(R.id.shopImage3).getImageView().setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(getActivity(),ImageViewActivity.class);
							intent.putExtra("listImages", shopDetailEntity.getImageEntities());
							intent.putExtra("current", 2);
							startActivity(intent);
						}
					});
		} else {
			aQuery.id(R.id.shopImage3).gone();
		}
	}

	public void setShopServices() {
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		for (int i = 0; i < shopDetailEntity.getServiceEntities().size(); i++) {
			View view = layoutInflater.inflate(R.layout.d001_services_item,
					null);
			TextView left = (TextView) view.findViewById(R.id.d001_item_left);
			left.setText(shopDetailEntity.getServiceEntities().get(i).getName());
			if (shopDetailEntity.getServiceEntities().get(i).getFlag()
					.equals("1")) {
				left.setTextAppearance(getActivity(), R.style.text_active);
			} else {
				left.setTextAppearance(getActivity(), R.style.text_inactive);
			}
			i++;
			if (i < shopDetailEntity.getServiceEntities().size()) {
				TextView right = (TextView) view
						.findViewById(R.id.d001_item_right);
				right.setText(shopDetailEntity.getServiceEntities().get(i)
						.getName());
				if (shopDetailEntity.getServiceEntities().get(i).getFlag()
						.equals("1")) {
					right.setTextAppearance(getActivity(), R.style.text_active);
				} else {
					right.setTextAppearance(getActivity(), R.style.text_inactive);
				}
			}
			listServices.addView(view);
		}
	}

	public void setShopDevices() {
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		for (int i = 0; i < shopDetailEntity.getFacilityEntities().size(); i++) {
			View view = layoutInflater.inflate(R.layout.d001_services_item,null);
			TextView left = (TextView) view.findViewById(R.id.d001_item_left);
			left.setText(shopDetailEntity.getFacilityEntities().get(i).getName());
			if (shopDetailEntity.getFacilityEntities().get(i).getFlag().equals("1")) {
				left.setTextAppearance(getActivity(), R.style.text_active);
			} else {
				left.setTextAppearance(getActivity(), R.style.text_inactive);
			}
			i++;
			if (i < shopDetailEntity.getFacilityEntities().size()) {
				TextView right = (TextView) view.findViewById(R.id.d001_item_right);
				right.setText(shopDetailEntity.getFacilityEntities().get(i).getName());
				if (shopDetailEntity.getFacilityEntities().get(i).getFlag().equals("1")) {
					right.setTextAppearance(getActivity(), R.style.text_active);
				} else {
					right.setTextAppearance(getActivity(), R.style.text_inactive);
				}
			}
			listDevices.addView(view);
		}
	}

	public void getUIComponent(View v) {
		d001_add = (TextView) v.findViewById(R.id.d001_add_content);
		d001_fax = (TextView) v.findViewById(R.id.d001_fax_content);
		d001_note = (TextView) v.findViewById(R.id.d001_note);
		d001_time = (TextView) v.findViewById(R.id.d001_time_content);
		d001_lastOrder = (TextView) v.findViewById(R.id.d001_order_content);
		d001_holidays = (TextView) v.findViewById(R.id.d001_holidays_content);
		d001_bussinessTime = (TextView) v.findViewById(R.id.d001_bus_content);
		d001_cards = (TextView) v.findViewById(R.id.d001_card_content);
		d001_parking = (TextView) v.findViewById(R.id.d001_parking_content);
		d001_homes = (TextView) v.findViewById(R.id.d001_home_content);
		d001_seats = (TextView) v.findViewById(R.id.d001_seats_content);
		d001_ages = (TextView) v.findViewById(R.id.d001_ages_content);
		listServices = (LinearLayout) v.findViewById(R.id.listServices);
		listDevices = (LinearLayout) v.findViewById(R.id.listDevices);
		d001_other_lo = (LinearLayout)v.findViewById(R.id.d001_other_lo);
	}
}
