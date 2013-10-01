package com.csn.puripuri;

import java.util.ArrayList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.csn.adapter.AppBaseAdapter;
import com.csn.datasource.JsonParser;
import com.csn.entity.BaseEntity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
public class G003Activity extends AppBaseActivity {
	private ListView listJobType;
	private ArrayList<BaseEntity> listJobTypeEntities;
	private AppBaseAdapter jobTypeAdapter;
	private Button backButton;
	private Button okButton;
	private String jobTypes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_left, R.anim.un_slide_left);
		setContentView(R.layout.g003);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		jobTypes = G001Activity.searchJobParamEntity.getJobTypes();
		listJobType = (ListView) findViewById(R.id.g003_listjobtype);
		listJobType.setCacheColorHint(0);
		listJobTypeEntities = new ArrayList<BaseEntity>();
		backButton = (Button) findViewById(R.id.g003_btn_back);
		okButton = (Button) findViewById(R.id.g003_okButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				jobTypes = "";
				for(int i= 0;i<listJobTypeEntities.size();i++){
					if(listJobTypeEntities.get(i).isSelected()){
						jobTypes += listJobTypeEntities.get(i).getId()+",";
					}
				}
				if(!jobTypes.equals("")){
					int d = jobTypes.lastIndexOf(",");
					jobTypes = jobTypes.substring(0,d);
				}
				Log.e("jobs",jobTypes);
				G001Activity.searchJobParamEntity.setJobTypes(jobTypes);
				onBackPressed();
			}
		});
		buildAreas();
	}
	public void buildAreas() {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(BASE_URL + "get_job_types.php", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				listJobTypeEntities = JsonParser.getJobTypeEntities(res);
				String[] listSelected = jobTypes.split(",");
				for(int i=0;i < listSelected.length;i++){
					for(int j=0;j<listJobTypeEntities.size();j++){
						if(listJobTypeEntities.get(j).getId().equals(""+listSelected[i])){
							listJobTypeEntities.get(j).setSelected(true);
						}
					}
				}
				jobTypeAdapter = new AppBaseAdapter(getApplicationContext(),listJobTypeEntities);
				listJobType.setAdapter(jobTypeAdapter);
				jobTypeAdapter.notifyDataSetChanged();
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.slide_right, R.anim.un_slide_right);
	}
}
