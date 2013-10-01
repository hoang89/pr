package com.csn.puripuri;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

import com.androidquery.callback.BitmapAjaxCallback;
import com.csn.adapter.D001ImagePagerAdapter;
import com.csn.entity.ImageEntity;
import com.csn.entity.ShopDetailEntity;

public class ImageViewActivity extends AppBaseActivity implements OnPageChangeListener{
	private ArrayList<ImageEntity> listImageEntities;
	private ViewPager listImage;
	private TextView title;
	private int current;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		current = getIntent().getIntExtra("current", 0);
		listImageEntities = (ArrayList<ImageEntity>) getIntent().getSerializableExtra("listImages");
		title = (TextView)findViewById(R.id.d001_image_des);
		listImage = (ViewPager) findViewById(R.id.images);
		if (listImageEntities != null) {
			D001ImagePagerAdapter d001ImagePagerAdapter = new D001ImagePagerAdapter(ImageViewActivity.this, listImageEntities);
			listImage.setAdapter(d001ImagePagerAdapter);
			listImage.setOnPageChangeListener(this);
			listImage.setCurrentItem(current);
			if(!listImageEntities.get(current).getDescription().equals("null")){
			title.setText(listImageEntities.get(current).getDescription());
			}
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		BitmapAjaxCallback.clearCache();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		if(!listImageEntities.get(current).getDescription().equals("null")){
			title.setText(listImageEntities.get(arg0).getDescription());
			}
		
	}
}
