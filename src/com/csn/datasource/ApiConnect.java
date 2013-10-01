package com.csn.datasource;

import java.util.ArrayList;

import com.csn.entity.NewsEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ApiConnect {
	private static String BASE_URL = "http://49.212.169.177/webapi/";
	private static AsyncHttpClient client = new AsyncHttpClient();
	private static String response;
	
	
	public static ArrayList<NewsEntity> getNews(){
		String url = BASE_URL+"get_news.php";
		client.get(url,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				// TODO Auto-generated method stub
				super.onSuccess(res);
				response = res;
			}
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub
				super.onFailure(arg0, arg1);
				response = null;
			}
		});
		if(response == null){
			return new ArrayList<NewsEntity>();
		}else{
			return JsonParser.getNews(response);
		}
	}

}
