package com.csn.puripuri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartPointActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(getApplicationContext(),SplashScreenOne.class);
		startActivity(intent);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}
}
