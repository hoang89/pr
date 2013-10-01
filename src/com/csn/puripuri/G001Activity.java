package com.csn.puripuri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csn.entity.SearchJobParamEntity;

public class G001Activity extends AppBaseActivity {
	private LinearLayout g001_to_g002;
	private LinearLayout g001_to_g003;
	private LinearLayout g001_to_g004;
	private TextView g001_row1_status;
	private TextView g001_row2_status;
	private TextView g001_row3_status;
	private Button g001_search_buton;
	private Button g001_reset_buton,btnBack;
	
	public static SearchJobParamEntity searchJobParamEntity;
	private String category;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g001);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		category = getIntent().getStringExtra("category");
		searchJobParamEntity = new SearchJobParamEntity();
		searchJobParamEntity.setCategory(category);
		btnBack = (Button) findViewById(R.id.g001_btnBack);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		getUIComponent();
		setUIEvent();
	
	}
	
	public void getUIComponent() {
		g001_to_g002 = (LinearLayout) findViewById(R.id.g001_to_g002);
		g001_to_g003 = (LinearLayout) findViewById(R.id.g001_to_g003);
		g001_to_g004 = (LinearLayout) findViewById(R.id.g001_to_g004);
		g001_row1_status = (TextView) findViewById(R.id.g001_row1_status);
		g001_row2_status = (TextView) findViewById(R.id.g001_row2_status);
		g001_row3_status = (TextView) findViewById(R.id.g001_row3_status);
		g001_reset_buton = (Button) findViewById(R.id.g001_reset_button);
		g001_search_buton = (Button) findViewById(R.id.g001_search_button);
	}
	
	public void setUIEvent() {
		g001_to_g002.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),G002Activity.class);
				startActivity(intent);
			}
		});
		g001_to_g003.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),G004Activity.class);
				startActivity(intent);
			}
		});
		
		g001_to_g004.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),G003Activity.class);
				startActivity(intent);
			}
		});
		
		g001_reset_buton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				searchJobParamEntity.reset();
				resetText();
			}
		});
		
		g001_search_buton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),G005Activity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		resetText();
	}
	
	public void resetText() {
		String inactive = getString(R.string.e001_row1_2);
		String active = getString(R.string.e001_row1_2_active);
		if(searchJobParamEntity.getAreas().equals("")){
			g001_row1_status.setText(inactive);
		}else{
			g001_row1_status.setText(active);
		}
		
		if(searchJobParamEntity.getJobTitles().equals("")){
			g001_row2_status.setText(inactive);
		}else{
			g001_row2_status.setText(active);
		}
		
		if(searchJobParamEntity.getJobTypes().equals("")){
			g001_row3_status.setText(inactive);
		}else{
			g001_row3_status.setText(active);
		}
	}
}
