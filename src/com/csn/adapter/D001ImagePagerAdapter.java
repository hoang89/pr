package com.csn.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.csn.datasource.Constant;
import com.csn.entity.ImageEntity;
import com.csn.puripuri.R;

public class D001ImagePagerAdapter extends PagerAdapter {
	
	private ArrayList<ImageEntity> listUrl;
	private int loadImageSize;
	private float ratio;
	
	
	public D001ImagePagerAdapter(Activity activity,ArrayList<ImageEntity> listUrl) {
		super();
		this.listUrl = listUrl;
		loadImageSize = activity.getWindowManager().getDefaultDisplay().getWidth();
		ratio = AQuery.RATIO_PRESERVE;
	}
	
	

	@Override
	public int getCount() {
		return listUrl.size();
	}
	
	@Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
 
    }

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		 return arg0 == ((View) arg1);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.imageview_item,null);
		AQuery aQuery = new AQuery(view);
		aQuery.id(R.id.d001_detail_image).visible().image(listUrl.get(position).getUrl(),Constant.memCache,Constant.fileCache,loadImageSize,0,null,0,ratio);
		((ViewPager) container).addView(view, 0);
		return view;
	}
	
	

}
