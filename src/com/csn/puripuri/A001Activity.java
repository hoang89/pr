package com.csn.puripuri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.csn.adapter.NewsAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.NewsEntity;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class A001Activity extends AppBaseActivity {
	private ListView listview;
	private Button btn_yellow;
	private Button btn_blue;
	private Button btn_green;
	private NewsAdapter newsAdapter;
	private ArrayList<NewsEntity> listNewsEntities;
	private static final int YELLOW = 1;
	private static final int BLUE = 2;
	private static final int GREEN = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a001);
		
		listview = (ListView) findViewById(R.id.test_list);
		listNewsEntities = new ArrayList<NewsEntity>();
				
		listview.setCacheColorHint(0);
		listview.setDivider(new BitmapDrawable(getResources(), BitmapFactory
				.decodeResource(getResources(), R.drawable.new_divider)));
		
		btn_yellow = (Button) findViewById(R.id.btn_yellow);
		btn_blue = (Button) findViewById(R.id.btn_blue);
		btn_green = (Button) findViewById(R.id.btn_green);

		btn_blue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				buttonClick(arg0,BLUE);
			}
		});

		btn_green.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//buttonClick(arg0,GREEN);
				Intent intent = new Intent(getApplicationContext(),K001Activity.class);
				startActivity(intent);
			}
		});

		btn_yellow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				buttonClick(arg0,YELLOW);
			}
		});
		
		btn_login = (Button) findViewById(R.id.btn_login);
		if(isLoggedIn()){
			btn_login.setBackgroundResource(R.drawable.logout_button);
			btn_login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					goToLogout();
				}
			});
		}else{
			btn_login.setBackgroundResource(R.drawable.login_button);
			btn_login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					goToLogin();
				}
			});
		}
		
		getNews();
	}
	
	private void buttonClick(View v,int button){
		Intent intent = new Intent(getApplicationContext(),A002Activity.class);
		intent.putExtra("button", button);
		startActivity(intent);
		
	}

	
	
	public  void getNews(){
		String url = BASE_URL+"get_news.php";
		client.get(url,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				listNewsEntities = JsonParser.getNews(res);
				newsAdapter = new NewsAdapter(getApplicationContext(), listNewsEntities); 
				listview.setAdapter(newsAdapter);
				newsAdapter.notifyDataSetChanged();
			}
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
			}
		});
	}
}
