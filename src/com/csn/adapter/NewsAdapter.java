package com.csn.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csn.entity.NewsEntity;
import com.csn.puripuri.R;

public class NewsAdapter extends BaseAdapter {
	
	@SuppressWarnings("unused")
	private Context context;
	private ArrayList<NewsEntity> listNews;
	private LayoutInflater layoutInflater;
	
	
	public NewsAdapter(Context context, ArrayList<NewsEntity> listNews) {
		super();
		this.context = context;
		this.listNews = listNews;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listNews.size();
	}

	@Override
	public Object getItem(int position) {
		return listNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.listitem, null);
		}
		NewsEntity newsEntity = (NewsEntity) getItem(position);
		TextView news_date = (TextView) convertView.findViewById(R.id.news_date);
		TextView news_text = (TextView) convertView.findViewById(R.id.news_text);
		news_date.setText(newsEntity.getDate());
		news_text.setText(newsEntity.getText());
		return convertView;
	}

}
