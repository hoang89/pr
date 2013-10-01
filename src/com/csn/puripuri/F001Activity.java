package com.csn.puripuri;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csn.adapter.F001Adapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.SearchResultEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class F001Activity extends AppBaseActivity {
	private ArrayList<SearchResultEntity> listSearchResultEntities;
	private F001Adapter f001Adapter;
	private ListView listView;
	private ProgressBar pro;
	private TextView noresults;
	private Button btn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f001);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		listView = (ListView) findViewById(R.id.listResults);
		pro = (ProgressBar) findViewById(R.id.progress);
		btn_back = (Button)findViewById(R.id.f001_btn_back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		noresults = (TextView) findViewById(R.id.f001_tv_noresults);
		getSearchResult();
	}

	public void getSearchResult() {
		AsyncHttpClient aClient = new AsyncHttpClient();
		RequestParams requestParams = new RequestParams();
		if (!E001Activity.searchParameterEntity.getKeyword().equals("")) {
			requestParams.put("keyword",
					E001Activity.searchParameterEntity.getKeyword());
		}
		if (!E001Activity.searchParameterEntity.getComment().equals("")) {
			requestParams.put("comment",
					E001Activity.searchParameterEntity.getComment());
		}
		if (!E001Activity.searchParameterEntity.getAreas().equals("")) {
			requestParams.put("areas",
					E001Activity.searchParameterEntity.getAreas());
		}
		if (!E001Activity.searchParameterEntity.getGenres().equals("")) {
			requestParams.put("genres",
					E001Activity.searchParameterEntity.getGenres());
		}
		if (!E001Activity.searchParameterEntity.getAvgCosts().equals("")) {
			requestParams.put("avg_costs",
					E001Activity.searchParameterEntity.getAvgCosts());
		}
		if (!E001Activity.searchParameterEntity.getFacilities().equals("")) {
			requestParams.put("facilities",
					E001Activity.searchParameterEntity.getFacilities());
		}
		if (!E001Activity.searchParameterEntity.getServices().equals("")) {
			requestParams.put("services",
					E001Activity.searchParameterEntity.getServices());
		}
		if (!E001Activity.searchParameterEntity.getBusinessTimes().equals("")) {
			requestParams.put("businesstimes",
					E001Activity.searchParameterEntity.getBusinessTimes());
		}
		if (!E001Activity.searchParameterEntity.getAges().equals("")) {
			requestParams.put("ages",
					E001Activity.searchParameterEntity.getAges());
		}
		requestParams.put("category_id", E001Activity.searchParameterEntity.getCategory());
		
		requestParams.put("coupon", E001Activity.searchParameterEntity.getCoupon());
		Log.e("PARAM", requestParams.toString());
		aClient.get(BASE_URL + "shop_search.php", requestParams,
				new AsyncHttpResponseHandler() {
					public void onSuccess(String res) {
						super.onSuccess(res);
						
						listSearchResultEntities = JsonParser
								.getSearchResults(res);
						f001Adapter = new F001Adapter(getApplicationContext(),
								listSearchResultEntities);
						listView.setAdapter(f001Adapter);
						pro.setVisibility(View.GONE);
						if (listSearchResultEntities.size() > 0) {
							listView.setVisibility(View.VISIBLE);
						} else {
							noresults.setVisibility(View.VISIBLE);
						}
					};

					public void onFailure(Throwable arg0, String arg1) {
						super.onFailure(arg0, arg1);
						pro.setVisibility(View.GONE);
						noresults.setVisibility(View.VISIBLE);
					};
				});
	}
}
