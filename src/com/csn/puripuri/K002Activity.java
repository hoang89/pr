package com.csn.puripuri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import twitter4j.TwitterException;
import twitter4j.http.AccessToken;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.csn.datasource.DatabaseHelper;
import com.csn.datasource.JsonParser;
import com.csn.entity.EventDetailEntity;
import com.csn.entity.ImageEntity;
import com.csn.puripuri.TwitterApp.TwDialogListener;
import com.csn.utils.Config;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class K002Activity extends AppBaseActivity {
	private String eventId;
	private EventDetailEntity eventDetailEntity;
	private ImageView k001_avatar;
	private ImageView k001_img1;
	private ImageView k001_img2;
	private ImageView k001_img3;
	
	private Button fb_btn;
	private Button tw_btn;
	private TextView k001_place;
	private TextView k001_address;
	private TextView k001_phone;
	private TextView k001_phone_callable;
	private TextView k001_date;
	private TextView k001_price;
	private TextView k001_access;
	private TextView k001_des;
	private TextView k001_name;
	private AQuery aQuery;
	private int loadImageSize;
	private int loadImageSizeSmall;
	private ImageView k002_map_button;
	private Button k002_back_button;
	private Button k002_btn_top;
	private Button k002_btn_main;
	private DatabaseHelper databaseHelper;
	private TwitterApp mTwitter;
	private AccessToken mAccessToken;
	private static final String APPID = "411148622267910";
	final static int AUTHORIZE_ACTIVITY_RESULT_CODE = -1;
	private static final String twitter_consumer_key = "jKsHrAfgmdn5QEEtUjIFw";
	private static final String twitter_secret_key = "zMA47TXo361SfT0cdOQbozVgBrU3huhdNm8jEock";
	private Facebook facebook;
	private String[] permissions = { "offline_access", "publish_stream"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.k002);
		getUIComponent();
		aQuery = new AQuery(K002Activity.this);
		facebook = new Facebook(APPID);
		mTwitter 	= new TwitterApp(this, twitter_consumer_key,twitter_secret_key);
		databaseHelper = new DatabaseHelper(getApplicationContext());
		eventId = getIntent().getStringExtra("event_id");
		loadImageSize = getWindowManager().getDefaultDisplay().getWidth();
		loadImageSizeSmall = getWindowManager().getDefaultDisplay().getWidth() /3;
		getBigEventDetail();
	}
	
	public void getUIComponent() {
		k001_avatar = (ImageView) findViewById(R.id.k001_avatar);
		k001_img1 = (ImageView) findViewById(R.id.k001_img1);
		k001_img2 = (ImageView) findViewById(R.id.k001_img2);
		k001_img3 = (ImageView) findViewById(R.id.k001_img3);
		k001_place = (TextView) findViewById(R.id.k001_place);
		k001_address = (TextView) findViewById(R.id.k001_address);
		k001_phone = (TextView) findViewById(R.id.k001_phone);
		k001_phone_callable = (TextView) findViewById(R.id.k001_phone_callable);
		k001_date = (TextView) findViewById(R.id.k001_date);
		k001_price = (TextView) findViewById(R.id.k001_price);
		k001_access = (TextView) findViewById(R.id.k001_access);
		k001_des = (TextView) findViewById(R.id.k001_des);
		k001_name = (TextView) findViewById(R.id.k002_tv_shopname);
		k002_map_button = (ImageView) findViewById(R.id.k002_map_button);
		k002_back_button = (Button) findViewById(R.id.k002_btnBack);
		k002_btn_top = (Button) findViewById(R.id.k002_btn_top);
		k002_btn_main = (Button) findViewById(R.id.k002_btn_main);
		fb_btn = (Button) findViewById(R.id.k002_btn_fb);
		tw_btn = (Button) findViewById(R.id.k002_btn_twitter);
	}
	
	public void setUIComponent() {
		if(eventDetailEntity.getUrl().equals("")){
			fb_btn.setVisibility(View.INVISIBLE);
			tw_btn.setVisibility(View.INVISIBLE);
		}
		fb_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!eventDetailEntity.getUrl().equals("")){
					connectToFacebook();	
				}
			}
		});
		
		tw_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!eventDetailEntity.getUrl().equals("")){
					onTwitterClick();
				}
			}
		});
		
		k002_btn_main.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainPageClick();
			}
		});
		k002_back_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		k002_btn_top.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goTopButtonPresses();
			}
		});
		if(eventDetailEntity != null){
			aQuery.id(R.id.k001_avatar).image(eventDetailEntity.getImage(), Config.memCache, Config.fileCache, loadImageSize, 0, null, 0, 0.5f);
			ArrayList<ImageEntity> list = eventDetailEntity.getListImageEntities();
			if (list.size() >= 1) {
				aQuery.id(R.id.k001_img1).image(list.get(0).getUrl(),Config.memCache, Config.fileCache, loadImageSizeSmall,0, null, 0, 1f);
				aQuery.id(R.id.k001_img1).getImageView().setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								Intent intent = new Intent(getApplicationContext(),ImageViewActivity.class);
								intent.putExtra("listImages", eventDetailEntity.getListImageEntities());
								intent.putExtra("current", 0);
								startActivity(intent);
							}
						});
			} else {
				aQuery.id(R.id.k001_img1).gone();
			}
			if(list.size() >= 2){
				aQuery.id(R.id.k001_img2).image(list.get(1).getUrl(), Config.memCache, Config.fileCache, loadImageSizeSmall, 0, null, 0, 1f);
				aQuery.id(R.id.k001_img2).getImageView().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getApplicationContext(),ImageViewActivity.class);
						intent.putExtra("listImages", eventDetailEntity.getListImageEntities());
						intent.putExtra("current", 1);
						startActivity(intent);
					}
				});
			} else {
				aQuery.id(R.id.k001_img2).gone();
			}
			
			if(list.size() >= 3){
				aQuery.id(R.id.k001_img3).image(list.get(2).getUrl(), Config.memCache, Config.fileCache, loadImageSizeSmall, 0, null, 0, 1f);
				aQuery.id(R.id.k001_img3).getImageView().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getApplicationContext(),ImageViewActivity.class);
						intent.putExtra("listImages", eventDetailEntity.getListImageEntities());
						intent.putExtra("current", 2);
						startActivity(intent);
					}
				});
			} else {
				aQuery.id(R.id.k001_img3).gone();
			}
			
			k002_map_button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (!eventDetailEntity.getLatitude().equals("")) {
						Intent intent = new Intent(getApplicationContext(),D015Activity.class);
						intent.putExtra("lat", eventDetailEntity.getLatitude());
						intent.putExtra("log", eventDetailEntity.getLongitude());
						intent.putExtra("screen", "K002");
						startActivity(intent);
					}
				}
			});
			
			k001_phone_callable.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (eventDetailEntity != null
							&& !eventDetailEntity.getPhone().equals("")) {
						Intent intent = new Intent(Intent.ACTION_CALL, Uri
								.parse("tel:" + eventDetailEntity.getPhone()));
						startActivity(intent);
					}
				}
			});
			
			k001_place.setText(eventDetailEntity.getPlace());
			k001_address.setText(eventDetailEntity.getAddress());
			k001_phone.setText(eventDetailEntity.getPhone());
			k001_phone_callable.setText(eventDetailEntity.getPhone());
			k001_date.setText(eventDetailEntity.getDate());
			k001_price.setText(eventDetailEntity.getPrice());
			k001_access.setText(eventDetailEntity.getAccess());
			k001_des.setText(eventDetailEntity.getDescription());
			k001_name.setText(eventDetailEntity.getName());
		}
	}
	
	private void getBigEventDetail() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("big_event_id", eventId);
		client.get(BASE_URL+"get_big_event_details.php", requestParams,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				Log.e("RES", res);
				eventDetailEntity = JsonParser.getBigEventsDetail(res);
				if(eventDetailEntity != null && !eventDetailEntity.getId().equals("")){
					databaseHelper.insertEvent(eventDetailEntity);
				}
				setUIComponent();
			}
		});
	}
	
	
	private void onTwitterClick() {
		if (mTwitter.hasAccessToken()) {
			TwitterSession mSession = new TwitterSession(this);
			mAccessToken = mSession.getAccessToken();
			if(mAccessToken != null)
			{
				postToTwitter(eventDetailEntity.getUrl());
			}
			else
			{
				mTwitter.authorize();
			}
			
		} else {
			mTwitter.authorize();
		}
	}
	
	private final TwDialogListener mTwLoginDialogListener = new TwDialogListener() {
		public void onComplete(String value) {
			String username = mTwitter.getUsername();
			username		= (username.equals("")) ? "No Name" : username;
			postToTwitter(eventDetailEntity.getUrl());
		}
		
		public void onError(String value) {
		}
	}; 
	private void postToTwitter(final String review)
	{
		new Thread() {
			@Override
			public void run() {
				try {
					mTwitter.updateStatus(review);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(K002Activity.this, "Twitter につぶやきました", Toast.LENGTH_SHORT).show();
						}
					});
					
				} catch (TwitterException e) {
					e.printStackTrace();
					final String message = "失敗しました";
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(K002Activity.this, message, Toast.LENGTH_SHORT).show();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					final String message = "失敗しました";
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(K002Activity.this, message, Toast.LENGTH_SHORT).show();
						}
					});
				}
				
			}
		}.start();
	}
	
	
	
	private void connectToFacebook() {
		facebook.authorize(K002Activity.this,permissions, AUTHORIZE_ACTIVITY_RESULT_CODE,new LoginDialogListener());
	}
	
	public void showError(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	
	class LoginDialogListener implements DialogListener {

		@Override
		public void onCancel() {
			Toast.makeText(getApplicationContext(), "Login canceled", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onComplete(Bundle values) {
			if (values.isEmpty())
	        {
	            return;
	        }

			if (!values.containsKey("post_id"))
	        {
	                Thread thread = new Thread(new Runnable() {
						
						@Override
						public void run() {
							Bundle parameters = new Bundle();
			                parameters.putString("message", eventDetailEntity.getUrl());// the message to post to the wall
			                parameters.putString("description", "topic share");
			                String mes = "失敗しました";
							 try {
								facebook.request("me");
								String res = facebook.request("me/feed", parameters, "POST");
								Log.e("RESFB",res);
				    			try {
									JSONObject jsonObject = new JSONObject(res);
									JSONObject error = jsonObject.getJSONObject("error");
										if(error != null){
											showShare(mes);
										}
								} catch (JSONException e) {
									showShare("Facebook にシェアされました");
									e.printStackTrace();
								}
				    			
							} catch (MalformedURLException e) {
								showShare(mes);
								e.printStackTrace();
							} catch (IOException e) {
								showShare(mes);
								e.printStackTrace();
							}
						}
					});
	                thread.start();
	        }

		}

		@Override
		public void onError(DialogError arg0) {
			Toast.makeText(getApplicationContext(), "Login faile,please try again later", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onFacebookError(FacebookError arg0) {
			Toast.makeText(getApplicationContext(), "Login faile,please try again later", Toast.LENGTH_SHORT).show();
			}
	}
	
	public void showShare(final String mes){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
