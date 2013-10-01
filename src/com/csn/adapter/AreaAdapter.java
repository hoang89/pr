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

import com.csn.entity.AreaEntity;
import com.csn.puripuri.R;

public class AreaAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<AreaEntity> listAreas;
	private LayoutInflater layoutInflater;

	public AreaAdapter(Context context, ArrayList<AreaEntity> listAreas) {
		super();
		this.context = context;
		this.listAreas = listAreas;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listAreas.size();
	}

	@Override
	public AreaEntity getItem(int position) {
		return listAreas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHold viewHold;
		final AreaEntity areaEntity;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.areaitems, null);
			viewHold = new ViewHold();
			viewHold.textView = (Button) convertView
					.findViewById(R.id.arearname);
			convertView.setTag(viewHold);
		} else {
			viewHold = (ViewHold) convertView.getTag();
		}

		areaEntity = (AreaEntity) getItem(position);
		if (!areaEntity.isSelected()) {
			viewHold.textView.setBackgroundColor(Color.WHITE);
			viewHold.textView.setTextColor(Color.BLACK);
		} else {
			viewHold.textView.setTextColor(Color.WHITE);
			viewHold.textView.setBackgroundColor(Color.BLACK);
		}
		viewHold.textView.setText(areaEntity.getName());
		viewHold.textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!areaEntity.isSelected()) {
					viewHold.textView.setTextColor(Color.WHITE);
					viewHold.textView.setBackgroundColor(Color.BLACK);
					areaEntity.setSelected(true);
				} else {
					viewHold.textView.setBackgroundColor(Color.WHITE);
					viewHold.textView.setTextColor(Color.BLACK);
					areaEntity.setSelected(false);
				}
			}
		});
		return convertView;
	}

	static class ViewHold {
		private Button textView;
	}

}
