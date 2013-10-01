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

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
import com.csn.datasource.JsonParser;
import com.csn.entity.EventEntity;
import com.csn.puripuri.AppBaseActivity;
import com.csn.puripuri.R;
import com.csn.utils.Config;
import com.csn.utils.ConvertUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class D016Fragment extends Fragment {
	private ArrayList< EventEntity> listEventEntities;
	private LinearLayout listevent;
	private String shopId;
	private int imageWidth;
	private TextView d016_error;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.d016layout, null);
		imageWidth = (int) (getActivity().getWindowManager().getDefaultDisplay().getWidth() - ConvertUtils.pxFromDp(40, getActivity()));
		listevent = (LinearLayout) view.findViewById(R.id.listevent);
		d016_error = (TextView) view.findViewById(R.id.d016_error);
		shopId = getActivity().getIntent().getStringExtra("shopid");
		if(listEventEntities == null){
			getEvent();
			
		}else{
			if(listEventEntities.size() >0){
				buildEventUI();
				d016_error.setVisibility(View.GONE);
			}else{
				d016_error.setVisibility(View.VISIBLE);
			}
		}
		return view;
	}
	
	private void buildEventUI(){
		ImageOptions options = new ImageOptions();
		options.fileCache = Config.fileCache;
		options.memCache = Config.memCache;
		options.ratio = AQuery.RATIO_PRESERVE;
		options.targetWidth = imageWidth;
		options.round = (int) ConvertUtils.pxFromDp(10, getActivity());
		options.animation = 0;
		options.fallback = 0;
		listevent.removeAllViews();
		for(int i=0;i<listEventEntities.size();i++){
			EventEntity eventEntity = listEventEntities.get(i);
			View view = LinearLayout.inflate(getActivity(), R.layout.d016listitem, null);
			AQuery aQuery = new AQuery(view);
			aQuery.id(R.id.d016_image).image(eventEntity.getImageUrl(),options);
			aQuery.id(R.id.d016_detail).text(eventEntity.getDetail());
			aQuery.id(R.id.d016_title).text(eventEntity.getTitle());
			aQuery.id(R.id.d016_period).text(eventEntity.getPeriod());
			listevent.addView(view);
		}
	}
	
	public void getEvent() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("shop_id", shopId);
		client.get(AppBaseActivity.BASE_URL + "get_events.php", params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				Log.e("RESD016",res);
				listEventEntities = JsonParser.getEventEntities(res);
				if(listEventEntities != null && listEventEntities.size() > 0){
					buildEventUI();
					d016_error.setVisibility(View.GONE);
				}else{
					d016_error.setVisibility(View.VISIBLE);
				}
				
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				d016_error.setVisibility(View.VISIBLE);
			}
		});
		
	}
}
