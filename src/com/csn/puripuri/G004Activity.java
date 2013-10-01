package com.csn.puripuri;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.csn.adapter.AppBaseAdapter;
import com.csn.adapter.SeperateAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.JobTitleEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class G004Activity extends AppBaseActivity {
	
	private SeperateAdapter seperateAdapter;
	private ListView listOthers;
	private Button backButton;
	private Button okButton;
	private ArrayList<JobTitleEntity> listJobTitleEntities;
	private String listJobTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g004);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		seperateAdapter = new SeperateAdapter(getApplicationContext());
		listOthers = (ListView) findViewById(R.id.g004_listOthers);
		listOthers.setCacheColorHint(0);
		listJobTitle = G001Activity.searchJobParamEntity.getJobTitles();
		backButton = (Button) findViewById(R.id.g004_btn_back);
		okButton = (Button) findViewById(R.id.g004_okButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listJobTitle = "";
				for(int i=0;i< listJobTitleEntities.size();i++){
					JobTitleEntity jobTitleEntity = listJobTitleEntities.get(i);
					for(int j=0;j < jobTitleEntity.getListJobTitle().size();j++){
						if(jobTitleEntity.getListJobTitle().get(j).isSelected()){
							listJobTitle += jobTitleEntity.getListJobTitle().get(j).getId()+",";
						}
					}
				}
				
				if(!listJobTitle.equals("")){
					int d = listJobTitle.lastIndexOf(",");
					listJobTitle = listJobTitle.substring(0,d);
				}
				Log.e("jobsTitle",listJobTitle);
				G001Activity.searchJobParamEntity.setJobTitles(listJobTitle);
				onBackPressed();
			}
		});
		
		getJobTitles();
	}
	
	public void buildJobTitlesList() {
		String[] listSelected = listJobTitle.split(",");
		if(listJobTitleEntities != null){
			for(int i=0;i< listJobTitleEntities.size();i++){
				JobTitleEntity jobTitleEntity = listJobTitleEntities.get(i);
				for(int k = 0; k < listSelected.length;k++){
					for(int j = 0;j< jobTitleEntity.getListJobTitle().size();j++){
						if(jobTitleEntity.getListJobTitle().get(j).getId().equals(listSelected[k])){
							jobTitleEntity.getListJobTitle().get(j).setSelected(true);
						}
					}
				}
				AppBaseAdapter appBaseAdapter = new AppBaseAdapter(getApplicationContext(), jobTitleEntity.getListJobTitle());
				seperateAdapter.addSection(jobTitleEntity.getSection(), appBaseAdapter);
			}
			
			listOthers.setAdapter(seperateAdapter);
			seperateAdapter.notifyDataSetChanged();
		}
	}
	
	public void getJobTitles(){
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.get(BASE_URL + "get_job_titles.php", new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				Log.e("RESJOB",res);
				listJobTitleEntities = JsonParser.getJobTitleEntities(res);
				buildJobTitlesList();
			}
		});
	}
}
