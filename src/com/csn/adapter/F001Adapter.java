package com.csn.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.csn.entity.SearchResultEntity;
import com.csn.puripuri.D001Activity;
import com.csn.puripuri.R;
import com.csn.utils.StringUtils;

public class F001Adapter extends BaseAdapter {
	
	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<SearchResultEntity> listSearchResults;
	private LayoutInflater layoutInflater;
	
	
	public F001Adapter(Context context, ArrayList<SearchResultEntity> listSearchResults) {
		super();
		this.context = context;
		this.listSearchResults = listSearchResults;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listSearchResults.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchResults.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final  ViewHolder viewHolder;
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.f001_item, null);
			viewHolder = new ViewHolder();
			viewHolder.genre = (TextView) convertView.findViewById(R.id.f001_genre);
			viewHolder.shopname = (TextView) convertView.findViewById(R.id.f001_shopname);
			viewHolder.description = (TextView) convertView.findViewById(R.id.f001_des);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final SearchResultEntity searchResultEntity = (SearchResultEntity) getItem(position);
		AQuery aQuery = new AQuery(convertView);
		aQuery.id(R.id.f001_image).visible().image(searchResultEntity.getImageUrl(), true, true,200,
				R.drawable.image_sample,null,0,1.0f/1.0f);
		viewHolder.genre.setText(searchResultEntity.getGenreName());
		viewHolder.shopname.setText(StringUtils.validString(searchResultEntity.getShopName(),14));
		viewHolder.description.setText(StringUtils.validString(searchResultEntity.getNewestComment(),21));
		convertView.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,D001Activity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("shopid",searchResultEntity.getShopId());
				intent.putExtra("screen", "F001");
				context.startActivity(intent);
			}
		});
		return convertView;
	}
	
	static class ViewHolder{
		private TextView genre;
		private TextView shopname;
		private TextView description;
	}

}
