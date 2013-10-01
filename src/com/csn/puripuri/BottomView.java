package com.csn.puripuri;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BottomView {
	private Button top;
	private Button search;
	private Button eyes;
	private Button main;
	private Activity activity;
	
	public BottomView(Activity activity) {
		super();
		this.activity = activity;
	}
	
	public void mainPageClick() {
		if(isLoggedIn()){
			Intent intent = new Intent(activity.getApplicationContext(),C001Activity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(intent);
		}else{
			Intent intent = new Intent(activity.getApplicationContext(),B001Activity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(intent);
		}
	}
	
	public boolean isLoggedIn() {
		SharedPreferences sharedPreferences = activity.getSharedPreferences("login_flag", 0);
		if(!sharedPreferences.getBoolean("login", false)){
			return false;
		}
		return true;
	}
	
	public void createTab() {
		top = (Button) activity.findViewById(R.id.bottom_btn_top);
		eyes = (Button) activity.findViewById(R.id.bottom_btn_eyes);
		search = (Button)activity.findViewById(R.id.bottom_btn_search);
		main = (Button)activity.findViewById(R.id.bottom_btn_main);
		top.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity.getApplicationContext(),A001Activity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				activity.startActivity(intent);
			}
		});
		
		main.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainPageClick();
			}
		});
		
		eyes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!activity.getClass().equals(H001Activity.class)){
				Intent intent = new Intent(activity.getApplicationContext(),H001Activity.class);
				activity.startActivity(intent);
				}
			}
		});
		
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!activity.getClass().equals(E001Activity.class)){
				Intent intent = new Intent(activity.getApplicationContext(),E001Activity.class);
				activity.startActivity(intent);
				}
			}
		});
	}
}
