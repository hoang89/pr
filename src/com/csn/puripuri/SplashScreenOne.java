package com.csn.puripuri;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenOne extends AppBaseActivity {
	private Thread splashTread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash1);
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(1000);
					}

				} catch (InterruptedException e) {
				} finally {
					Intent intent = new Intent(getApplicationContext(),SplashScreenTwo.class);
					startActivity(intent);
				}
			}
		};
		splashTread.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
	}
}
