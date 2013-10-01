package com.csn.puripuri;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.csn.adapter.K001Adapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.BigEventEntity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class K001Activity extends AppBaseActivity {
	private ProgressBar progressBar;
	private ArrayList<BigEventEntity> listBigEventEntities;
	private ListView k001_list;
	private K001Adapter k001Adapter;
	private Calendar calendar;
	private int month;
	private int year;
	private int currentMonth;
	private int currentYear;
	private TextView month_title;
	private ImageView nextButton;
	private ImageView previousButton;
	private Button k001_back;
	private Button k001_btn_top;
	private Button k001_btn_main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		calendar = Calendar.getInstance();
		month = calendar.get(Calendar.MONTH) + 1;
		currentMonth = month;
		year = calendar.get(Calendar.YEAR);
		currentYear = year;
		setContentView(R.layout.k001);
		getUIComponent();
		getListBigEvent(year, month);
		setUIComponent();
		setUIEvent();
	}
	
	private void getUIComponent() {
		progressBar = (ProgressBar) findViewById(R.id.k001_progress);
		k001_list = (ListView) findViewById(R.id.k001_list);
		month_title = (TextView) findViewById(R.id.month_title);
		nextButton = (ImageView) findViewById(R.id.next);
		previousButton = (ImageView) findViewById(R.id.previous);
		k001_back = (Button) findViewById(R.id.k001_btn_back);
		k001_btn_top = (Button) findViewById(R.id.k001_btn_top);
		k001_btn_main = (Button) findViewById(R.id.k001_btn_main);
	}
	
	private void setUIComponent(){
		month_title.setText(year+"年"+month+"月");
	}
	
	private void setUIEvent() {
		k001_btn_main.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mainPageClick();
			}
		});
		k001_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		k001_btn_top.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goTopButtonPresses();
			}
		});
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(month == (calendar.getMaximum(Calendar.MONTH)+1)){
					year += 1;
					month = calendar.getMinimum(Calendar.MONTH)+1;
				}else{
					month += 1;
				}
				setUIComponent();
				getListBigEvent(year, month);
			}
		});
		
		previousButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (month == currentMonth && year == currentYear) {

				} else {
					if (month == (calendar.getMinimum(Calendar.MONTH) + 1)) {
						year -= 1;
						month = calendar.getMaximum(Calendar.MONTH) + 1;
					} else {
						month -= 1;
					}
					setUIComponent();
					getListBigEvent(year, month);
				}
			}
		});
	}
	
	private void buildListEvent() {
		if(listBigEventEntities != null){
			k001Adapter = new K001Adapter(getApplicationContext(), listBigEventEntities);
			k001_list.setAdapter(k001Adapter);
			k001Adapter.notifyDataSetChanged();
			k001_list.setVisibility(View.VISIBLE);
		}
	}
	
	private void getListBigEvent(int year,int month) {
		if(!progressBar.isShown()){
			progressBar.setVisibility(View.VISIBLE);
		}
		RequestParams requestParams = new RequestParams();
		requestParams.put("year", ""+year);
		requestParams.put("month", ""+month);
		client.get(BASE_URL+"big_event_search.php", requestParams,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				listBigEventEntities = JsonParser.getBigEventsList(res);
				buildListEvent();
				progressBar.setVisibility(View.GONE);
			}
			
			@Override
			public void onFailure(Throwable arg0, String res) {
				super.onFailure(arg0, res);
				progressBar.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(), "Get server info faile", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
