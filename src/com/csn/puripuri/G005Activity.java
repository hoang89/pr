package com.csn.puripuri;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.csn.adapter.JobSearchAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.JobSearchEntity;
import com.csn.entity.SearchJobParamEntity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class G005Activity extends AppBaseActivity{
	private SearchJobParamEntity searchJobParamEntity;
	private ArrayList<JobSearchEntity> listJobSearchEntities;
	private ListView listView;
	private JobSearchAdapter jobSearchAdapter;
	private Button g005_back_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g005);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		searchJobParamEntity = G001Activity.searchJobParamEntity;
		listView = (ListView) findViewById(R.id.g005_list_jobs);
		g005_back_button = (Button) findViewById(R.id.g005_btn_back);
		listView.setCacheColorHint(0);
		searchJob();
		
		g005_back_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	public void buildListResult() {
		if(listJobSearchEntities != null){
			jobSearchAdapter = new JobSearchAdapter(getApplicationContext(), listJobSearchEntities);
			listView.setAdapter(jobSearchAdapter);
			jobSearchAdapter.notifyDataSetChanged();
			listView.setVisibility(View.VISIBLE);
			((ProgressBar) findViewById(R.id.progress)).setVisibility(View.GONE);
		}
	}
	
	public void searchJob() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("category", searchJobParamEntity.getCategory());
		if(searchJobParamEntity.getAreas().equals("")){
			requestParams.put("areas", searchJobParamEntity.getAreas());
		}
		
		if(searchJobParamEntity.getJobTitles().equals("")){
			requestParams.put("job_titles", searchJobParamEntity.getJobTitles());
		}
		
		if(searchJobParamEntity.getJobTypes().equals("")){
			requestParams.put("job_types", searchJobParamEntity.getJobTypes());
		}
		
		client.get(BASE_URL+"job_search.php", requestParams, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				listJobSearchEntities = JsonParser.getJobSearchResults(res);
				buildListResult();
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				((ProgressBar) findViewById(R.id.progress)).setVisibility(View.GONE);
				showError("Get server info faile");
			}
		});
	}
}
