package com.csn.puripuri;

import com.csn.utils.Config;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class A002Activity extends AppBaseActivity {
	private Button btn_top;
	private int from;
	private Button category1;
	private Button category2;
	private Button category3;
	private Button category4;
	private Button category5;
	private Button category6;
	private Button category7;
	private Button category8;
	private Button category9;
	private Button category10;
	private Button category11;
	private Button category12;
	private Button main_page;
	private Button btn_login;
	
	private static final int YELLOW = 1;
	private static final int BLUE = 2;
	private static final int GREEN = 3;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.current_in, 0);
		setContentView(R.layout.main_layout);
		from = getIntent().getIntExtra("button", -1);
		getButtonLayout();
		btn_login = (Button) findViewById(R.id.btn_login);		
		btn_top = (Button) findViewById(R.id.btn_top);
		btn_top.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		
		main_page = (Button) findViewById(R.id.btn_mainPage);
		main_page.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainPageClick();
			}
		});
		Log.e("Child","OnCreate");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//checkToShow(btn_login);
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		overridePendingTransition(0, R.anim.current_out);
	}
	
	public void pushCategory(int category) {
		SharedPreferences sharedPreferences = getSharedPreferences("category",0);
		Editor e = sharedPreferences.edit();
		e.putString(Config.CATEGORY, ""+category);
		e.commit();
	}

	public void getButtonLayout() {
		category1 = (Button) findViewById(R.id.btn_category1);
		category2 = (Button) findViewById(R.id.btn_category2);
		category3 = (Button) findViewById(R.id.btn_category3);
		category4 = (Button) findViewById(R.id.btn_category4);
		category5 = (Button) findViewById(R.id.btn_category5);
		category6 = (Button) findViewById(R.id.btn_category6);
		category7 = (Button) findViewById(R.id.btn_category7);
		category8 = (Button) findViewById(R.id.btn_category8);
		category9 = (Button) findViewById(R.id.btn_category9);
		category10 = (Button) findViewById(R.id.btn_category10);
		category11 = (Button) findViewById(R.id.btn_category11);
		category12 = (Button) findViewById(R.id.btn_category12);

		category1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pushCategory(1);
				setButtonClick(1);
			}
		});

		category2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pushCategory(2);
				setButtonClick(2);
			}
		});
		category3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});
		category12.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//setButtonClick();
			}
		});

	}

	private void setButtonClick(int pos) {
		if(from == BLUE){
			Intent intent = new Intent(getApplicationContext(),E001Activity.class);
			startActivity(intent);
		}
		
		if(from == YELLOW){
			Intent intent = new Intent(getApplicationContext(),G001Activity.class);
			startActivity(intent);
		}
	}

}
