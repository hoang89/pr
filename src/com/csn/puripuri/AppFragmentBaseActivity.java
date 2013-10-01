package com.csn.puripuri;

import com.loopj.android.http.AsyncHttpClient;

import android.support.v4.app.FragmentActivity;

public class AppFragmentBaseActivity extends FragmentActivity {
	protected String BASE_URL = "http://49.212.169.177/webapi/";
	protected AsyncHttpClient client = new AsyncHttpClient();
}
