package com.csn.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csn.datasource.JsonParser;
import com.csn.entity.JobEntity;
import com.csn.puripuri.AppBaseActivity;
import com.csn.puripuri.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class D014Fragment extends Fragment {
	
	private ArrayList<JobEntity> listJobEntities;
	private String shopId;
	private LinearLayout listjobs;
	private TextView d014_error;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.d014layout, null);
		shopId = getActivity().getIntent().getStringExtra("shopid");
		listjobs = (LinearLayout)view.findViewById(R.id.listjobs);
		d014_error = (TextView) view.findViewById(R.id.d014_error);
		if(listJobEntities != null){
			if(listJobEntities.size() > 0){
			buildJobList();
			d014_error.setVisibility(View.GONE);
			}else{
				d014_error.setVisibility(View.VISIBLE);
			}
		}else{
			getJobList();
		}
		return view;
	}
	
	public void buildJobList(){
		listjobs.removeAllViews();
		for (int i = 0; i < listJobEntities.size(); i++) {
			JobEntity jobEntity = listJobEntities.get(i);
			View view = LinearLayout.inflate(getActivity(), R.layout.d014listitem, null);
			
			((TextView)view.findViewById(R.id.d014_post)).setText(jobEntity.getPostDate());
			((TextView)view.findViewById(R.id.d014_job_type)).setText(jobEntity.getJobType());
			((TextView)view.findViewById(R.id.d014_job_title)).setText(jobEntity.getJobTitle());
			((TextView)view.findViewById(R.id.d014_detail)).setText(jobEntity.getDetail());
			((TextView)view.findViewById(R.id.d014_work_hour)).setText(jobEntity.getWorkHour());
			((TextView)view.findViewById(R.id.d014_holiday)).setText(jobEntity.getHolidays());
			((TextView)view.findViewById(R.id.d014_salary)).setText(jobEntity.getSalary());
			((TextView)view.findViewById(R.id.d014_other)).setText(jobEntity.getOther());
			((TextView)view.findViewById(R.id.d014_method)).setText(jobEntity.getMethod());
			listjobs.addView(view);
		}
	}
	
	public void getJobList() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("shop_id", shopId);
		client.get(AppBaseActivity.BASE_URL + "get_jobpostings.php", params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				Log.e("RESD014",res);
				listJobEntities = JsonParser.getJobEntities(res);
				if(listJobEntities != null && listJobEntities.size() > 0){
					buildJobList();
					d014_error.setVisibility(View.GONE);
				}else{
					d014_error.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				d014_error.setVisibility(View.VISIBLE);
			}
		});
	}
}
