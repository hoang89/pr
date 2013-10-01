package com.csn.puripuri;

import com.csn.entity.SearchParameterEntity;
import com.csn.utils.Config;

import android.app.Activity;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class E001Activity extends AppBaseActivity {
	private LinearLayout e001_to_e002;
	private LinearLayout e001_to_e003;
	private LinearLayout e001_to_e004;
	private LinearLayout e001_to_e005;
	private LinearLayout e001_to_e006;
	private ImageView e001_row5_check;
	
	private TextView e001_row1_status;
	private TextView e001_row2_status;
	private TextView e001_row3_status;
	private TextView e001_row4_status;
	private TextView e001_row6_status;
	private Button searchButton;
	private Button btnBack;
	private boolean isCoupon = false;
	public static SearchParameterEntity searchParameterEntity;
	private Button clearConditions;
	private String category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		overridePendingTransition(R.anim.current_in, 0);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e001);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		category = getSharedPreferences("category", 0).getString(Config.CATEGORY, "1");
		Log.e("CAT",category);
		searchParameterEntity = new SearchParameterEntity();
		searchParameterEntity.setCategory(category);
		e001_to_e002 = (LinearLayout) findViewById(R.id.e001_to_e002);
		e001_to_e003 = (LinearLayout) findViewById(R.id.e001_to_e003);
		e001_to_e004 = (LinearLayout) findViewById(R.id.e001_to_e004);
		e001_to_e005 = (LinearLayout) findViewById(R.id.e001_to_e005);
		e001_to_e006 = (LinearLayout) findViewById(R.id.e001_to_e006);
		e001_row1_status = (TextView) findViewById(R.id.e001_row1_status);
		e001_row2_status = (TextView) findViewById(R.id.e001_row2_status);
		e001_row3_status = (TextView) findViewById(R.id.e001_row3_status);
		e001_row4_status = (TextView) findViewById(R.id.e001_row4_status);
		e001_row6_status = (TextView) findViewById(R.id.e001_row6_status);
		clearConditions = (Button) findViewById(R.id.e001_btn_clear);
		
		searchButton = (Button) findViewById(R.id.e001_searchButton);
		e001_row5_check = (ImageView) findViewById(R.id.e001_row5_check);
		btnBack = (Button) findViewById(R.id.e001_btnBack);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		eventSetting();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		resetText();
	}
	
	public void resetText(){
		String inactive = getString(R.string.e001_row1_2);
		String active = getString(R.string.e001_row1_2_active);
		if(!searchParameterEntity.getKeyword().equals("") || !searchParameterEntity.getComment().equals("")){
			e001_row1_status.setText(active);
		}else{
			e001_row1_status.setText(inactive);
		}
		
		if(searchParameterEntity.getAreas().equals("")){
			e001_row2_status.setText(inactive);
		}else{
			e001_row2_status.setText(active);
		}
		
		if(searchParameterEntity.getGenres().equals("")){
			e001_row3_status.setText(inactive);
		}else{
			e001_row3_status.setText(active);
		}
		
		if(searchParameterEntity.getBusinessTimes().equals("")){
			e001_row4_status.setText(inactive);
		}else{
			e001_row4_status.setText(active);
		}
		if(searchParameterEntity.getCoupon().equals("0")){
			e001_row5_check.setImageResource(R.drawable.check_box_inactive);
		}else{
			e001_row5_check.setImageResource(R.drawable.check_box_active);
		}
		
		if(searchParameterEntity.getAges().equals("") && searchParameterEntity.getAvgCosts().equals("") 
				&& searchParameterEntity.getFacilities().equals("") && searchParameterEntity.getServices().equals("")){
			e001_row6_status.setText(inactive);
		}else{
			e001_row6_status.setText(active);
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		overridePendingTransition(0, R.anim.current_out);
	}

	public void eventSetting() {
		e001_to_e002.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), E002Activity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		
		e001_to_e003.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), E003Activity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		e001_to_e004.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), E004Activity.class);
				intent.putExtra("category", ""+category);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		
		e001_to_e005.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), E005Activity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		e001_to_e006.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.e("e003-5","ok");
				Intent intent = new Intent(getApplicationContext(), E006Activity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				intent.putExtra("category", ""+category);
				startActivity(intent);
			}
		});
		e001_row5_check.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!isCoupon){
					e001_row5_check.setImageResource(R.drawable.check_box_active);
					searchParameterEntity.setCoupon("1");
				}else{
					e001_row5_check.setImageResource(R.drawable.check_box_inactive);
					searchParameterEntity.setCoupon("0");
				}
				isCoupon = !isCoupon;
			}
		});
		
		clearConditions.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
			searchParameterEntity.reset();
			resetText();
			}
		});
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), F001Activity.class);
				startActivity(intent);
			}
		});
		
	}
}
