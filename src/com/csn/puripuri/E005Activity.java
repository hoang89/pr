package com.csn.puripuri;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.csn.adapter.TimeAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.BusinessTimeEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class E005Activity extends AppBaseActivity {
	private ListView listTimes;
	private ArrayList<BusinessTimeEntity> listTimeEntities;
	private TimeAdapter timeAdapter;
	private Button backButton;
	private Button okButton;
	private String times;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_left, R.anim.un_slide_left);
		setContentView(R.layout.e005);
		BottomView bottomView =  new BottomView(this);
		bottomView.createTab();
		times = E001Activity.searchParameterEntity.getBusinessTimes();
		listTimes = (ListView) findViewById(R.id.e005_listTimes);
		listTimes.setCacheColorHint(0);
		listTimeEntities = new ArrayList<BusinessTimeEntity>();
		backButton = (Button) findViewById(R.id.e005_btn_back);
		okButton = (Button) findViewById(R.id.e005_okButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				times = "";
				for(int i= 0;i<listTimeEntities.size();i++){
					if(listTimeEntities.get(i).isSelected()){
						times += listTimeEntities.get(i).getId()+",";
					}
				}
				if(!times.equals("")){
					int d = times.lastIndexOf(",");
					times = times.substring(0,d);
				}
				E001Activity.searchParameterEntity.setBusinessTimes(times);
				onBackPressed();
			}
			
		});
		buildAreas();
	}
	public void buildAreas() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(BASE_URL + "get_businesstimes.php", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				listTimeEntities = JsonParser.getBusinessTime(res);
				String[] listSelected = times.split(",");
				for(int i=0;i < listSelected.length;i++){
					for(int j=0;j<listTimeEntities.size();j++){
						if(listTimeEntities.get(j).getId().equals(""+listSelected[i])){
							listTimeEntities.get(j).setSelected(true);
						}
					}
				}
				timeAdapter = new TimeAdapter(getApplicationContext(),listTimeEntities);
				listTimes.setAdapter(timeAdapter);
				timeAdapter.notifyDataSetChanged();
				listTimes.setVisibility(View.VISIBLE);
				((ProgressBar)findViewById(R.id.e005_progress)).setVisibility(View.GONE);
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				showError("Get server info faile");
				((ProgressBar)findViewById(R.id.e005_progress)).setVisibility(View.GONE);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.slide_right, R.anim.un_slide_right);
	}
}
