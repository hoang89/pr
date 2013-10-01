package com.csn.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.csn.entity.BusinessTimeEntity;
import com.csn.puripuri.R;

public class TimeAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<BusinessTimeEntity> listTimes;
	private LayoutInflater layoutInflater;

	public TimeAdapter(Context context, ArrayList<BusinessTimeEntity> listTimes) {
		super();
		this.context = context;
		this.listTimes = listTimes;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listTimes.size();
	}

	@Override
	public BusinessTimeEntity getItem(int position) {
		return listTimes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHold viewHold;
		final BusinessTimeEntity timeEntity;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.areaitems, null);
			viewHold = new ViewHold();
			viewHold.textView = (Button) convertView
					.findViewById(R.id.arearname);
			convertView.setTag(viewHold);
		} else {
			viewHold = (ViewHold) convertView.getTag();
		}

		timeEntity = (BusinessTimeEntity) getItem(position);
		if (!timeEntity.isSelected()) {
			viewHold.textView.setBackgroundColor(Color.WHITE);
			viewHold.textView.setTextColor(Color.BLACK);
		} else {
			viewHold.textView.setTextColor(Color.WHITE);
			viewHold.textView.setBackgroundColor(Color.BLACK);
		}
		viewHold.textView.setText(timeEntity.getName());
		viewHold.textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!timeEntity.isSelected()) {
					viewHold.textView.setTextColor(Color.WHITE);
					viewHold.textView.setBackgroundColor(Color.BLACK);
					timeEntity.setSelected(true);
				} else {
					viewHold.textView.setBackgroundColor(Color.WHITE);
					viewHold.textView.setTextColor(Color.BLACK);
					timeEntity.setSelected(false);
				}
			}
		});
		return convertView;
	}

	static class ViewHold {
		private Button textView;
	}

}
