package com.csn.puripuri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import twitter4j.TwitterException;
import twitter4j.http.AccessToken;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.csn.datasource.DatabaseHelper;
import com.csn.datasource.JsonParser;
import com.csn.entity.CommentEntity;
import com.csn.entity.ShopDetailEntity;
import com.csn.fragments.D001Fragment1;
import com.csn.fragments.D011Fragment;
import com.csn.fragments.D013Fragment;
import com.csn.fragments.D014Fragment;
import com.csn.fragments.D016Fragment;
import com.csn.puripuri.TwitterApp.TwDialogListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class D001Activity extends AppBaseActivity {
	private LinearLayout listComments;
	private LayoutInflater layoutInflater;
	private static String fragment1 = "D001Fragment1";
	private static String fragment2 = "D001Fragment2";
	private static String fragment3 = "D001Fragment3";
	private static String fragment4 = "D001Fragment4";
	private static String fragment5 = "D001Fragment5";
	private TextView shopCategoryName;
	private ShopDetailEntity shopDetailEntity;
	private TextView shopName;
	private TextView genreName;
	private TextView description;
	private TextView shopPhone;
	private D001Fragment1 d1;
	private D011Fragment d011;
	private D013Fragment d013;
	private D014Fragment d014;
	private D016Fragment d016;
	private String shopId;
	private ArrayList<CommentEntity> listCommentEntities;
	private LinearLayout lo_comments;
	private AQuery aQuery;
	private Button d001_btn_tab1;
	private Button d001_btn_tab2;
	private Button d001_btn_tab3;
	private Button d001_btn_tab4;
	private Button d001_btn_tab5;
	private Button d001_back_button;
	private TabInfo currentTab;
	private int loadImageSize;
	private ImageView d001_map_button;
	private String screen;
	private Facebook facebook;
	private String[] permissions = { "offline_access", "publish_stream"};
	private static final String APPID = "411148622267910";
	final static int AUTHORIZE_ACTIVITY_RESULT_CODE = -1;
	private static final String twitter_consumer_key = "jKsHrAfgmdn5QEEtUjIFw";
	private static final String twitter_secret_key = "zMA47TXo361SfT0cdOQbozVgBrU3huhdNm8jEock";
	private Button fb_btn;
	private Button tw_btn;
	private String message;
	private TwitterApp mTwitter;
	private AccessToken mAccessToken;
	private DatabaseHelper databaseHelper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.d001);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		loadImageSize = getWindowManager().getDefaultDisplay().getWidth();
		aQuery = new AQuery(this);
		facebook = new Facebook(APPID);
		mTwitter 	= new TwitterApp(this, twitter_consumer_key,twitter_secret_key);
		databaseHelper = new DatabaseHelper(getApplicationContext());
		mTwitter.setListener(mTwLoginDialogListener);
		getUIComponent();
		shopId = getIntent().getExtras().getString("shopid");
		screen = getIntent().getExtras().getString("screen");
		currentTab = new TabInfo();
		if(screen.equals("G005")){
			setFragmentForView(5);
			setActiveButton(5);
		}else{
		setFragmentForView(1);
		setActiveButton(1);
		}
		//setUIEvent();
		getShopInfo();
		
	}
	
	private void onTwitterClick() {
		if (mTwitter.hasAccessToken()) {
			TwitterSession mSession = new TwitterSession(this);
			mAccessToken = mSession.getAccessToken();
			if(mAccessToken != null)
			{
				postToTwitter(shopDetailEntity.getUrl());
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
			postToTwitter(shopDetailEntity.getUrl());
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
							Toast.makeText(D001Activity.this, "Twitter につぶやきました", Toast.LENGTH_SHORT).show();
						}
					});
					
				} catch (TwitterException e) {
					e.printStackTrace();
					final String message = "失敗しました";
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(D001Activity.this, message, Toast.LENGTH_SHORT).show();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					final String message = "失敗しました";
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(D001Activity.this, message, Toast.LENGTH_SHORT).show();
						}
					});
				}
				
			}
		}.start();
	}
	
	
	
	private void connectToFacebook() {
		facebook.authorize((Activity) D001Activity.this,permissions, AUTHORIZE_ACTIVITY_RESULT_CODE,new LoginDialogListener());
	}

	public void setFragmentForView(int i) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (currentTab.currentFragment != null && currentTab.currentNumber == i) {
			return;
		} else {
			if (currentTab.currentFragment != null) {
				ft.detach(currentTab.currentFragment);
			}
			switch (i) {
			case 1:
				setActiveButton(1);
				if (getSupportFragmentManager().findFragmentByTag(fragment1) == null) {
					d1 = new D001Fragment1();
					ft.add(R.id.othercontent, d1, fragment1);
				} else {
					ft.attach(d1);
				}
				currentTab.currentFragment = d1;

				break;
			case 2:
				setActiveButton(2);
				if (getSupportFragmentManager().findFragmentByTag(fragment2) == null) {
					d011 = new D011Fragment();
					ft.add(R.id.othercontent, d011, fragment2);
				} else {
					ft.attach(d011);
				}
				currentTab.currentFragment = d011;
				break;
				
			case 3:
				setActiveButton(3);
				if (getSupportFragmentManager().findFragmentByTag(fragment3) == null) {
					d016 = new D016Fragment();
					ft.add(R.id.othercontent, d016, fragment3);
				} else {
					ft.attach(d016);
				}
				currentTab.currentFragment = d016;
				break;
				
				
			case 4:
				setActiveButton(4);
				if (getSupportFragmentManager().findFragmentByTag(fragment4) == null) {
					d013 = new D013Fragment();
					ft.add(R.id.othercontent, d013, fragment4);
				} else {
					ft.attach(d013);
				}
				currentTab.currentFragment = d013;
				break;
				
			case 5:
				setActiveButton(5);
				if (getSupportFragmentManager().findFragmentByTag(fragment5) == null) {
					d014 = new D014Fragment();
					ft.add(R.id.othercontent, d014, fragment5);
				} else {
					ft.attach(d014);
				}
				currentTab.currentFragment = d014;
				break;
			default:
				break;
			}
			currentTab.currentNumber = i;
		}
		getSupportFragmentManager().executePendingTransactions();
		ft.commit();

	}

	private void setUIEvent() {
		if(shopDetailEntity != null){
		shopPhone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (shopDetailEntity != null
						&& !shopDetailEntity.getPhone().equals("")) {
					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + shopDetailEntity.getPhone()));
					startActivity(intent);
				}
			}
		});
		d001_btn_tab1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setFragmentForView(1);
			}
		});

		d001_btn_tab2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setFragmentForView(2);
			}
		});
		
		d001_btn_tab3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setFragmentForView(3);
			}
		});
		
		d001_btn_tab4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setFragmentForView(4);
			}
		});
		

		d001_btn_tab5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setFragmentForView(5);
			}
		});
		
		fb_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!shopDetailEntity.getUrl().equals("")){
					connectToFacebook();	
				}
			}
		});
		
		tw_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!shopDetailEntity.getUrl().equals("")){
					onTwitterClick();
				}
			}
		});
		
		d001_map_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (shopDetailEntity != null && !shopDetailEntity.getLatitude().equals("")) {
					Intent intent = new Intent(getApplicationContext(),D015Activity.class);
					intent.putExtra("lat", shopDetailEntity.getLatitude());
					intent.putExtra("log", shopDetailEntity.getLongitude());
					intent.putExtra("shopname", shopDetailEntity.getName());
					intent.putExtra("address", shopDetailEntity.getAddress());
					intent.putExtra("screen", "D001");
					startActivity(intent);
				}
			}
		});
		
		d001_back_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		}
	}

	public void getComments() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("shop_id", shopId);
		client.get(BASE_URL + "get_comments.php", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						listCommentEntities = JsonParser.getComments(res);
						getTwitterComment();
					}

					@Override
					public void onFailure(Throwable arg0, String arg1) {
						super.onFailure(arg0, arg1);
						listCommentEntities = new ArrayList<CommentEntity>();
						getTwitterComment();
					}
				});

	}
	
	public void getTwitterComment() {
		if(shopDetailEntity != null && !shopDetailEntity.getTwAccount().equals("")){
			client.get(shopDetailEntity.getTwAccount(), new AsyncHttpResponseHandler(){
				@Override
				public void onSuccess(String res) {
					// TODO Auto-generated method stub
					super.onSuccess(res);
					Log.e("RESTW",res);
					if(listCommentEntities == null){
						listCommentEntities = new ArrayList<CommentEntity>();
					}
					listCommentEntities = JsonParser.getTwitterComment(res, listCommentEntities);
					buildComment(listCommentEntities);
				}
				@Override
				public void onFailure(Throwable arg0, String arg1) {
					// TODO Auto-generated method stub
					super.onFailure(arg0, arg1);
					buildComment(listCommentEntities);
				}
			});
			
		}else{
			buildComment(listCommentEntities);
		}
	}

	public void buildComment(ArrayList<CommentEntity> listCommentEntities) {
		layoutInflater = LayoutInflater.from(getApplicationContext());
		if(listCommentEntities != null && listCommentEntities.size() > 0){
			for (int i = 0; i < listCommentEntities.size(); i++) {
				View commentItems = layoutInflater.inflate(
						R.layout.commentitems, null);
				TextView content = (TextView) commentItems
						.findViewById(R.id.comment_content);
				content.setText(Html.fromHtml(listCommentEntities.get(i)
						.getComment()));
				TextView date = (TextView) commentItems
						.findViewById(R.id.comment_date);
				date.setText(listCommentEntities.get(i).getDate());
				listComments.addView(commentItems);
			}
		}else{
			lo_comments.setVisibility(View.GONE);
		}
	}

	public void getUIComponent() {
		listComments = (LinearLayout) findViewById(R.id.listComments);
		shopCategoryName = (TextView) findViewById(R.id.shopCategoryName);
		shopName = (TextView) findViewById(R.id.d001_tv_shopname);
		genreName = (TextView) findViewById(R.id.d001_tv_genrename);
		description = (TextView) findViewById(R.id.d001_tv_description);
		shopPhone = (TextView) findViewById(R.id.d001_tv_shopPhone);
		lo_comments = (LinearLayout) findViewById(R.id.d001_lo_comments);
		d001_btn_tab1 = (Button) findViewById(R.id.d001_btn_tab1);
		d001_btn_tab2 = (Button) findViewById(R.id.d001_btn_tab2);
		d001_btn_tab3 = (Button) findViewById(R.id.d001_btn_tab3);
		d001_btn_tab4 = (Button) findViewById(R.id.d001_btn_tab4);
		d001_btn_tab5 = (Button) findViewById(R.id.d001_btn_tab5);
		d001_map_button = (ImageView) findViewById(R.id.d001_map_button);
		d001_back_button = (Button) findViewById(R.id.d001_btnBack);
		fb_btn = (Button) findViewById(R.id.d001_btn_fb);
		tw_btn = (Button) findViewById(R.id.d001_btn_twitter);
	}

	public void setActiveButton(int i) {
		d001_btn_tab1.setBackgroundResource(R.drawable.tab_inactive);
		d001_btn_tab2.setBackgroundResource(R.drawable.tab_inactive);
		d001_btn_tab3.setBackgroundResource(R.drawable.tab_inactive);
		d001_btn_tab4.setBackgroundResource(R.drawable.tab_inactive);
		d001_btn_tab5.setBackgroundResource(R.drawable.tab_inactive);
		switch (i) {
		case 1:
			d001_btn_tab1.setBackgroundResource(R.drawable.tab_active);
			break;
		case 2:
			d001_btn_tab2.setBackgroundResource(R.drawable.tab_active);
			break;
		case 3:
			d001_btn_tab3.setBackgroundResource(R.drawable.tab_active);
			break;
		case 4:
			d001_btn_tab4.setBackgroundResource(R.drawable.tab_active);
			break;
		case 5:
			d001_btn_tab5.setBackgroundResource(R.drawable.tab_active);
			break;
		default:
			break;
		}
	}

	public void getShopInfo() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("shop_id", shopId);
		client.get(BASE_URL + "get_shop_details.php", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						getComments();
						shopDetailEntity = JsonParser.getShopDetail(res);
						if(shopDetailEntity != null && !shopDetailEntity.getId().equals("")){
							databaseHelper.insertShop(shopDetailEntity);
						}
						setUIComponent();
					}
				});
	}

	public void setUIComponent() {
		if (shopDetailEntity != null && !shopDetailEntity.getId().equals("")) {
			aQuery.id(R.id.shopProfileImage).visible()
					.image(shopDetailEntity.getImageUrl(), true, false, loadImageSize,R.drawable.d001_image_sample,null,0,1.0f/2.0f);
			shopCategoryName.setText(shopDetailEntity.getCategoryEntity().getName());
			shopName.setText(shopDetailEntity.getName());
			genreName.setText(shopDetailEntity.getGenreEntity().getName());
			description.setText(Html.fromHtml(shopDetailEntity.getDescription()));
			shopPhone.setText(shopDetailEntity.getPhone());
			if(shopDetailEntity.getUrl().equals("")){
				fb_btn.setVisibility(View.INVISIBLE);
				tw_btn.setVisibility(View.INVISIBLE);
			}
			Collator collator = Collator.getInstance(Locale.JAPANESE);
			int result = collator.compare(shopDetailEntity.getGenreEntity().getName(), "携帯ショップ");
			if(result == 0){
				d001_btn_tab2.setText("機種サービス");
				d001_btn_tab2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
			}
			setUIEvent();
		}else{
			showError("Load information faile");
		}
	}

	static class TabInfo {
		private Fragment currentFragment;
		private int currentNumber;
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
			                parameters.putString("message", shopDetailEntity.getUrl());// the message to post to the wall
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
