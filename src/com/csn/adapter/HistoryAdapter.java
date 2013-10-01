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
import com.csn.datasource.DatabaseHelper;
import com.csn.entity.DBEntity;
import com.csn.puripuri.D001Activity;
import com.csn.puripuri.K002Activity;
import com.csn.puripuri.R;
import com.csn.utils.Config;
import com.csn.utils.ConvertUtils;
import com.csn.utils.StringUtils;
public class HistoryAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<DBEntity> listEntities;
	private LayoutInflater layoutInflater;
	private int imageSize;

	public HistoryAdapter(Context context, ArrayList<DBEntity> listEntities) {
		super();
		this.context = context;
		this.listEntities = listEntities;
		layoutInflater = LayoutInflater.from(context);
		imageSize = (int) ConvertUtils.pxFromDp(100, context);
	}

	@Override
	public int getCount() {
		return listEntities.size();
	}

	@Override
	public DBEntity getItem(int position) {
		return listEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHold viewHold;
		final DBEntity dbEntity;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.c001_item, null);
			viewHold = new ViewHold();
			convertView.setTag(viewHold);
		} else {
			viewHold = (ViewHold) convertView.getTag();
		}
		dbEntity = getItem(position);
		AQuery aQuery = new AQuery(convertView);
		aQuery.id(R.id.c001_avatar).image(dbEntity.getUrl(), Config.memCache, Config.fileCache, imageSize, 0, null, 0, 1);
		aQuery.id(R.id.c001_name).text(StringUtils.validString(dbEntity.getName(), 14));
		aQuery.id(R.id.c001_genre).text(dbEntity.getGenre());
		aQuery.id(R.id.c001_caterogy).text(dbEntity.getCategory());
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(dbEntity.getType() == DatabaseHelper.EVENT){
				Intent intent = new Intent(context,K002Activity.class);
				intent.putExtra("event_id", dbEntity.getId());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
				}else{
					Intent intent = new Intent(context,D001Activity.class);
					intent.putExtra("shopid", dbEntity.getId());
					intent.putExtra("screen", "C001");
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
			}
		});
		return convertView;
	}

	static class ViewHold {
		private TextView name;
		private TextView people;
		private TextView date;
		private TextView time;
		private ImageView avatar;
	}

}
