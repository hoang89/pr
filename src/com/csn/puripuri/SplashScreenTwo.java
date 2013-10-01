package com.csn.puripuri;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenTwo extends AppBaseActivity {
	private Thread splashTread;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash2);
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(1000);
					}

				} catch (InterruptedException e) {
				} finally {
					Intent intent = new Intent(getApplicationContext(),A001Activity.class);
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
