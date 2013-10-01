package com.csn.puripuri;

import java.util.ArrayList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.csn.adapter.AppBaseAdapter;
import com.csn.adapter.SeperateAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.BaseEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class E006Activity extends AppBaseActivity {
	private ListView listOthers;
	private ArrayList<BaseEntity> ageEntities;
	private ArrayList<BaseEntity> avgCostEntities;
	private ArrayList<BaseEntity> serviceEntities;
	private ArrayList<BaseEntity> facilityEntities;
	private AppBaseAdapter ageAdapter;
	private AppBaseAdapter avgCostAdapter;
	private AppBaseAdapter serviceAdapter;
	private AppBaseAdapter facilityAdapter;

	private SeperateAdapter seperateAdapter;
	private Button backButton;
	private Button okButton;
	private String ages;
	private String avgCosts;
	private String facilities;
	private String services;
	private String category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_left, R.anim.un_slide_left);
		setContentView(R.layout.e006);
		seperateAdapter = new SeperateAdapter(getApplicationContext());
		category = getIntent().getStringExtra("category");
		ages = E001Activity.searchParameterEntity.getAges();
		avgCosts = E001Activity.searchParameterEntity.getAvgCosts();
		facilities = E001Activity.searchParameterEntity.getFacilities();
		services = E001Activity.searchParameterEntity.getServices();

		listOthers = (ListView) findViewById(R.id.e006_listOthers);
		listOthers.setCacheColorHint(0);
		ageEntities = new ArrayList<BaseEntity>();
		avgCostEntities = new ArrayList<BaseEntity>();
		serviceEntities = new ArrayList<BaseEntity>();
		facilityEntities = new ArrayList<BaseEntity>();

		backButton = (Button) findViewById(R.id.e006_btn_back);
		okButton = (Button) findViewById(R.id.e006_okButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ages = "";
				
				for (int i = 0; i < ageEntities.size(); i++) {
					if (ageEntities.get(i).isSelected()) {
						ages += ageEntities.get(i).getId() + ",";
					}
				}
				if (!ages.equals("")) {
					int d = ages.lastIndexOf(",");
					ages = ages.substring(0, d);
				}
				E001Activity.searchParameterEntity.setAges(ages);
				
				avgCosts = "";
				
				for (int i = 0; i < avgCostEntities.size(); i++) {
					if (avgCostEntities.get(i).isSelected()) {
						avgCosts += avgCostEntities.get(i).getId() + ",";
					}
				}
				if (!avgCosts.equals("")) {
					int d = avgCosts.lastIndexOf(",");
					avgCosts = avgCosts.substring(0, d);
				}
				E001Activity.searchParameterEntity.setAvgCosts(avgCosts);
				
				services = "";
				
				for (int i = 0; i < serviceEntities.size(); i++) {
					if (serviceEntities.get(i).isSelected()) {
						services += serviceEntities.get(i).getName()+ ",";
					}
				}
				if (!services.equals("")) {
					int d = services.lastIndexOf(",");
					services = services.substring(0, d);
				}
				E001Activity.searchParameterEntity.setServices(services);
				
				facilities = "";
				
				for (int i = 0; i < facilityEntities.size(); i++) {
					if (facilityEntities.get(i).isSelected()) {
						facilities += facilityEntities.get(i).getName() + ",";
					}
				}
				if (!facilities.equals("")) {
					int d = facilities.lastIndexOf(",");
					facilities = facilities.substring(0, d);
				}
				E001Activity.searchParameterEntity.setFacilities(facilities);

				onBackPressed();
			}
		});
		buildAges();
	}
	public void buildAges() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(BASE_URL + "get_ages.php", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				Log.e("RES", res);
				ageEntities = JsonParser.getAges(res);
				String[] listSelected = ages.split(",");
				for (int i = 0; i < listSelected.length; i++) {
					for (int j = 0; j < ageEntities.size(); j++) {
						if (ageEntities.get(j).getId()
								.equals("" + listSelected[i])) {
							ageEntities.get(j).setSelected(true);
						}
					}
				}
				ageAdapter = new AppBaseAdapter(getApplicationContext(),
						ageEntities);
				seperateAdapter.addSection("客層", ageAdapter);
				listOthers.setAdapter(seperateAdapter);
				seperateAdapter.notifyDataSetChanged();
				buildAvgCost();
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				buildAvgCost();
			}
		});
	}

	public void buildAvgCost() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(BASE_URL + "get_avg_costs.php",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						Log.e("RES", res);
						avgCostEntities = JsonParser.getAvgCosts(res);
						String[] listSelected = avgCosts.split(",");
						for (int i = 0; i < listSelected.length; i++) {
							for (int j = 0; j < avgCostEntities.size(); j++) {
								if (avgCostEntities.get(j).getId()
										.equals("" + listSelected[i])) {
									avgCostEntities.get(j).setSelected(true);
								}
							}
						}
						avgCostAdapter = new AppBaseAdapter(
								getApplicationContext(), avgCostEntities);
						seperateAdapter.addSection("平均単価", avgCostAdapter);
						listOthers.setAdapter(seperateAdapter);
						seperateAdapter.notifyDataSetChanged();
						buildFacility();
					}

					@Override
					public void onFailure(Throwable arg0, String arg1) {
						super.onFailure(arg0, arg1);
						buildFacility();
					}
				});
	}
	public void buildFacility() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams requestParams = new RequestParams();
		requestParams.put("category_id", category);
		client.get(BASE_URL + "get_facilities.php", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						Log.e("RES", res);
						facilityEntities = JsonParser.getFacilities(res);
						String[] listSelected = facilities.split(",");
						for (int i = 0; i < listSelected.length; i++) {
							for (int j = 0; j < facilityEntities.size(); j++) {
								if (facilityEntities.get(j).getName()
										.equals("" + listSelected[i])) {
									facilityEntities.get(j).setSelected(true);
								}
							}
						}
						facilityAdapter = new AppBaseAdapter(
								getApplicationContext(), facilityEntities);
						seperateAdapter.addSection("設備", facilityAdapter);
						listOthers.setAdapter(seperateAdapter);
						seperateAdapter.notifyDataSetChanged();
						buildServices();
					}

					@Override
					public void onFailure(Throwable arg0, String arg1) {
						super.onFailure(arg0, arg1);
						buildServices();
					}
				});
	}

	public void buildServices() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams requestParams = new RequestParams();
		requestParams.put("category_id", category);
		client.get(BASE_URL + "get_services.php", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						Log.e("RES", res);
						serviceEntities = JsonParser.getServices(res);
						String[] listSelected = services.split(",");
						for (int i = 0; i < listSelected.length; i++) {
							for (int j = 0; j < serviceEntities.size(); j++) {
								if (serviceEntities.get(j).getName()
										.equals("" + listSelected[i])) {
									serviceEntities.get(j).setSelected(true);
								}
							}
						}
						serviceAdapter = new AppBaseAdapter(
								getApplicationContext(), serviceEntities);
						seperateAdapter.addSection("サービス", serviceAdapter);
						listOthers.setAdapter(seperateAdapter);
						seperateAdapter.notifyDataSetChanged();
						((ProgressBar)findViewById(R.id.e006_progress)).setVisibility(View.GONE);
						listOthers.setVisibility(View.VISIBLE);
					}

					@Override
					public void onFailure(Throwable arg0, String arg1) {
						super.onFailure(arg0, arg1);
						showError("Get server info faile");
						listOthers.setAdapter(seperateAdapter);
						seperateAdapter.notifyDataSetChanged();
						((ProgressBar)findViewById(R.id.e006_progress)).setVisibility(View.GONE);
					}
				});
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.slide_right, R.anim.un_slide_right);
	}

}
