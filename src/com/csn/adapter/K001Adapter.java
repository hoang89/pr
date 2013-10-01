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
import com.csn.entity.BigEventEntity;
import com.csn.puripuri.K002Activity;
import com.csn.puripuri.R;
import com.csn.utils.Config;
import com.csn.utils.ConvertUtils;
import com.csn.utils.StringUtils;
public class K001Adapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<BigEventEntity> listEntities;
	private LayoutInflater layoutInflater;
	private int imageSize;

	public K001Adapter(Context context, ArrayList<BigEventEntity> listEntities) {
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
	public BigEventEntity getItem(int position) {
		return listEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHold viewHold;
		final BigEventEntity bigEventEntity;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.k001_list_item, null);
			viewHold = new ViewHold();
			convertView.setTag(viewHold);
		} else {
			viewHold = (ViewHold) convertView.getTag();
		}
		bigEventEntity = getItem(position);
		AQuery aQuery = new AQuery(convertView);
		aQuery.id(R.id.k001_avatar).image(bigEventEntity.getImage(), Config.memCache, Config.fileCache, imageSize, 0, null, 0, 1);
		aQuery.id(R.id.k001_name).text(StringUtils.validString(bigEventEntity.getName(), 14));
		if(bigEventEntity.getTime().length() > 11){
			aQuery.id(R.id.k001_date).text(StringUtils.validString(bigEventEntity.getDate(), 11));
		}else{
			aQuery.id(R.id.k001_date).text(StringUtils.validString(bigEventEntity.getDate(), 22 - bigEventEntity.getTime().length()));
		}
		if(bigEventEntity.getDate().length() > 11){
			aQuery.id(R.id.k001_time).text(StringUtils.validString(bigEventEntity.getTime(), 11));
		}else{
			aQuery.id(R.id.k001_time).text(StringUtils.validString(bigEventEntity.getTime(), 22 - bigEventEntity.getDate().length()));
		}
		aQuery.id(R.id.k001_people).text("出演:"+StringUtils.validString(bigEventEntity.getPeople(), 12));
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,K002Activity.class);
				intent.putExtra("event_id", bigEventEntity.getId());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
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
