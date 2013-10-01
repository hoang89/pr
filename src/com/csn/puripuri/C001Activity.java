package com.csn.puripuri;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.csn.adapter.HistoryAdapter;
import com.csn.datasource.DatabaseHelper;
import com.csn.entity.DBEntity;

public class C001Activity extends AppBaseActivity {
	private DatabaseHelper databaseHelper;
	private ListView listHistories;
	private ArrayList<DBEntity> listEntities;
	private Button c001_to_c004;
	private Button c001_btn_top;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c001);
		databaseHelper = new DatabaseHelper(getApplicationContext());
		listEntities = databaseHelper.listHistory();
		listHistories = (ListView) findViewById(R.id.listHistories);
		if(listEntities != null){
			HistoryAdapter historyAdapter = new HistoryAdapter(getApplicationContext(), listEntities);
			listHistories.setAdapter(historyAdapter);
			historyAdapter.notifyDataSetChanged();
		}else{
			Log.e("NUL","NULL");
		}
		c001_to_c004 = (Button) findViewById(R.id.c001_to_c004);
		c001_btn_top = (Button) findViewById(R.id.c001_btn_top);
		c001_btn_top.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goTopButtonPresses();
			}
		});
		c001_to_c004.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),C004Activity.class);
				startActivity(intent);
			}
		});
	}
}
