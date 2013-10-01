package com.csn.puripuri;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.csn.adapter.GenreAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.GenreEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class E004Activity extends AppBaseActivity {
	private ListView listGenres;
	private ArrayList<GenreEntity> listGenreEntities;
	private GenreAdapter genreAdapter;
	private Button backButton;
	private Button okButton;
	private String genres;
	private String category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_left, R.anim.un_slide_left);
		setContentView(R.layout.e004);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		category = getIntent().getStringExtra("category");
		genres = E001Activity.searchParameterEntity.getGenres();
		listGenres = (ListView) findViewById(R.id.e004_listGenres);
		listGenres.setCacheColorHint(0);
		listGenreEntities = new ArrayList<GenreEntity>();
		backButton = (Button) findViewById(R.id.e004_btn_back);
		okButton = (Button) findViewById(R.id.e004_okButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				genres = "";
				for(int i= 0;i<listGenreEntities.size();i++){
					if(listGenreEntities.get(i).isSelected()){
						genres += listGenreEntities.get(i).getId()+",";
					}
				}
				if(!genres.equals("")){
					int d = genres.lastIndexOf(",");
					genres = genres.substring(0,d);
				}
				E001Activity.searchParameterEntity.setGenres(genres);
				onBackPressed();
			}
		});
		buildGenres();
	}
	public void buildGenres() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params =  new RequestParams();
		params.put("category_id", category);
		Log.e("RE","CA"+category);
		client.get(BASE_URL + "get_genres.php",params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				listGenreEntities = JsonParser.getGenres(res);
				String[] listSelected = genres.split(",");
				for(int i=0;i < listSelected.length;i++){
					for(int j=0;j<listGenreEntities.size();j++){
						if(listGenreEntities.get(j).getId().equals(""+listSelected[i])){
							listGenreEntities.get(j).setSelected(true);
						}
					}
				}
				genreAdapter = new GenreAdapter(getApplicationContext(),listGenreEntities);
				listGenres.setAdapter(genreAdapter);
				genreAdapter.notifyDataSetChanged();
				listGenres.setVisibility(View.VISIBLE);
				((ProgressBar)findViewById(R.id.e004_progress)).setVisibility(View.GONE);
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				showError("Get server info faile");
				((ProgressBar)findViewById(R.id.e004_progress)).setVisibility(View.GONE);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.slide_right, R.anim.un_slide_right);
	}
}
