package com.csn.puripuri;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.csn.adapter.AreaAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.AreaEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class E003Activity extends AppBaseActivity {
	private ListView listAreas;
	private ArrayList<AreaEntity> listAreaEntities;
	private AreaAdapter areaAdapter;
	private Button backButton;
	private Button okButton;
	private String areas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_left, R.anim.un_slide_left);
		setContentView(R.layout.e003);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		areas = E001Activity.searchParameterEntity.getAreas();
		listAreas = (ListView) findViewById(R.id.e003_listArea);
		listAreas.setCacheColorHint(0);
		listAreaEntities = new ArrayList<AreaEntity>();
		backButton = (Button) findViewById(R.id.e003_btn_back);
		okButton = (Button) findViewById(R.id.e003_okButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				areas = "";
				for(int i= 0;i<listAreaEntities.size();i++){
					if(listAreaEntities.get(i).isSelected()){
						areas += listAreaEntities.get(i).getId()+",";
					}
				}
				if(!areas.equals("")){
					int d = areas.lastIndexOf(",");
					areas = areas.substring(0,d);
				}
				E001Activity.searchParameterEntity.setAreas(areas);
				onBackPressed();
			}
		});
		buildAreas();
	}
	public void buildAreas() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(BASE_URL + "get_areas.php", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				listAreaEntities = JsonParser.getAreas(res);
				String[] listSelected = areas.split(",");
				for(int i=0;i < listSelected.length;i++){
					for(int j=0;j<listAreaEntities.size();j++){
						if(listAreaEntities.get(j).getId().equals(""+listSelected[i])){
							listAreaEntities.get(j).setSelected(true);
						}
					}
				}
				areaAdapter = new AreaAdapter(getApplicationContext(),listAreaEntities);
				listAreas.setAdapter(areaAdapter);
				areaAdapter.notifyDataSetChanged();
				listAreas.setVisibility(View.VISIBLE);
				((ProgressBar)findViewById(R.id.e003_progress)).setVisibility(View.GONE);
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				showError("Get server info faile");
				((ProgressBar)findViewById(R.id.e003_progress)).setVisibility(View.GONE);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.slide_right, R.anim.un_slide_right);
	}
}
