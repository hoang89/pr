package com.csn.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.csn.datasource.Constant;
import com.csn.datasource.JsonParser;
import com.csn.entity.D011ImageEntity;
import com.csn.puripuri.AppBaseActivity;
import com.csn.puripuri.R;
import com.csn.utils.ConvertUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class D011Fragment extends Fragment {
	private String shop_id;
	private ArrayList<ArrayList<D011ImageEntity>> listImageEntities;
	private LinearLayout d011_menu1;
	private LinearLayout lo_menu1;
	private LinearLayout lo_menu2;
	private LinearLayout lo_menu3;
	private LinearLayout d011_menu2;
	private int loadImageSizeMenu1;
	private int loadImageSizeMenu2;
	private LinearLayout d011_success;
	private TextView d011_error;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.d011fragment, null);
		loadImageSizeMenu1 = (int) (getActivity().getWindowManager().getDefaultDisplay().getWidth() - ConvertUtils.pxFromDp(20, getActivity()));
		loadImageSizeMenu2 = (int) (ConvertUtils.pxFromDp(getActivity().getResources().getDimension(R.dimen.d011_menu2_image_size), getActivity()));
		d011_menu1 = (LinearLayout) view.findViewById(R.id.d011_lo_menu1);
		d011_menu2 = (LinearLayout) view.findViewById(R.id.d011_lo_menu2);
		lo_menu2 = (LinearLayout) view.findViewById(R.id.lo_menu2);
		lo_menu3 = (LinearLayout) view.findViewById(R.id.d011_lo_menu3);
		d011_success = (LinearLayout)view.findViewById(R.id.d011_success);
		d011_error = (TextView)view.findViewById(R.id.d011_error);
		shop_id = getActivity().getIntent().getStringExtra("shopid");
		if (listImageEntities != null) {
			if(listImageEntities != null &&( listImageEntities.get(0).size() > 0 || listImageEntities.get(1).size() > 0 || listImageEntities.get(2).size() > 0)){
				buildMenu();
				buildMenu2();
				buildMenu3();
				d011_success.setVisibility(View.VISIBLE);
				d011_error.setVisibility(View.GONE);
			}else{
				d011_error.setVisibility(View.VISIBLE);
			}
		} else {
			getMenus();
		}
		return view;
	}
	public void getMenus() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams requestParams = new RequestParams();
		requestParams.put("shop_id", shop_id);
		client.get(AppBaseActivity.BASE_URL + "get_menu.php", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						Log.e("D011",res);
						listImageEntities = JsonParser.getMenus(res);
						if(listImageEntities != null &&( listImageEntities.get(0).size() > 0 || listImageEntities.get(1).size() > 0 || listImageEntities.get(2).size() > 0)){
							buildMenu();
							buildMenu2();
							buildMenu3();
							d011_success.setVisibility(View.VISIBLE);
							d011_error.setVisibility(View.GONE);
						}else{
							d011_error.setVisibility(View.VISIBLE);
							d011_success.setVisibility(View.GONE);
						}
					}
					
					@Override
					public void onFailure(Throwable arg0, String arg1) {
						// TODO Auto-generated method stub
						super.onFailure(arg0, arg1);
						d011_error.setVisibility(View.VISIBLE);
					}
				});
	}

	public void buildMenu() {
		if (listImageEntities != null) {
			ArrayList<D011ImageEntity> menu1 = listImageEntities.get(0);
			for (int i = 0; i < menu1.size(); i++) {
				View menu1_item = LinearLayout.inflate(getActivity(),R.layout.d011_menu1_item, null);
				AQuery aQuery = new AQuery(menu1_item);
				aQuery.id(R.id.d011_menu1_image).visible().image(menu1.get(i).getUrl(), Constant.memCache, Constant.fileCache, loadImageSizeMenu1,0,null,0,0.5f);
				aQuery.id(R.id.d011_menu1_des).text(menu1.get(i).getDescription());
				aQuery.id(R.id.d011_menu1_name).text(menu1.get(i).getName());
				TextView t2 = (TextView) menu1_item.findViewById(R.id.d011_menu1_price);
				if (!menu1.get(i).getPrice().equals("")) {
					t2.setText(menu1.get(i).getPrice());
				} else {
					t2.setText("");
				}
				
				d011_menu1.addView(menu1_item);
			}
		}

	}

	@SuppressLint("ParserError")
	public void buildMenu2() {
		Log.e("buildMenu2","ok");
		if (listImageEntities != null) {
			ArrayList<D011ImageEntity> menu1 = listImageEntities.get(1);
			for (int i = 0; i < menu1.size(); i++) {
				View menu1_item = LinearLayout.inflate(getActivity(),R.layout.d011_menu2_item, null);
				AQuery aQuery = new AQuery(menu1_item);
				aQuery.id(R.id.d011_menu2_image).visible().image(menu1.get(i).getUrl(), Constant.memCache, Constant.fileCache, loadImageSizeMenu2,0,null,0,1);
				((TextView)menu1_item.findViewById(R.id.d011_menu2_des)).setText(menu1.get(i).getName());
				((TextView)menu1_item.findViewById(R.id.d011_menu2_price)).setText(menu1.get(i).getPrice());
			
				d011_menu2.addView(menu1_item);
			}
			if (menu1.size() > 0) {
				lo_menu2.setVisibility(View.VISIBLE);
			
			}
		}

	}
	
	public void buildMenu3() {
		
		if (listImageEntities != null) {
			
			ArrayList<D011ImageEntity> menu1 = listImageEntities.get(2);
			for(int i=0;i<menu1.size();i++){
				
				D011ImageEntity d011ImageEntity = menu1.get(i);
				if(d011ImageEntity.getName().equals("")&& d011ImageEntity.getCategory_name().equals("")){
					
				}else{
				View view = LayoutInflater.from(getActivity()).inflate(R.layout.d011_menu3, null);
				((TextView)view.findViewById(R.id.d011_menu_type)).setText(d011ImageEntity.getCategory_name());
				((TextView)view.findViewById(R.id.d011_menu_name)).setText(d011ImageEntity.getName());
				if(!d011ImageEntity.getPrice().equals("")){
					((TextView)view.findViewById(R.id.d011_menu_price)).setText(d011ImageEntity.getPrice()+"円");
				}
				lo_menu3.addView(view);
				}
			}		
		}
	}

}
