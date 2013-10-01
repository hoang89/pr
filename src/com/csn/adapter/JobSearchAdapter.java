package com.csn.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csn.entity.JobSearchEntity;
import com.csn.puripuri.D001Activity;
import com.csn.puripuri.G006Activity;
import com.csn.puripuri.R;
public class JobSearchAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<JobSearchEntity> listEntities;
	private LayoutInflater layoutInflater;

	public JobSearchAdapter(Context context, ArrayList<JobSearchEntity> listEntities) {
		super();
		this.context = context;
		this.listEntities = listEntities;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listEntities.size();
	}

	@Override
	public JobSearchEntity getItem(int position) {
		return listEntities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHold viewHold;
		final JobSearchEntity jobSearchEntity;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.g005_list_item, null);
			viewHold = new ViewHold();
			viewHold.name = (TextView) convertView.findViewById(R.id.g005_item_shop_name);
			viewHold.salary = (TextView) convertView.findViewById(R.id.g005_item_salary);
			convertView.setTag(viewHold);
		} else {
			viewHold = (ViewHold) convertView.getTag();
		}
		jobSearchEntity = getItem(position);
		viewHold.name.setText(Html.fromHtml(jobSearchEntity.getName()));
		viewHold.salary.setText(Html.fromHtml(jobSearchEntity.getSalary()));
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(jobSearchEntity.getPostType().equals("shop")){
					Intent intent = new Intent(context,D001Activity.class);
					intent.putExtra("shopid", jobSearchEntity.getId());
					intent.putExtra("screen", "G005");
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}else{
					Intent intent = new Intent(context,G006Activity.class);
					intent.putExtra("shopid", jobSearchEntity.getId());
					intent.putExtra("screen", "G005");
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
			}
		});
		return convertView;
	}

	static class ViewHold {
		private TextView name;
		private TextView salary;
	}

}
