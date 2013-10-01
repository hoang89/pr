package com.csn.puripuri;

import java.util.regex.Pattern;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

public class AppBaseActivity extends FragmentActivity {
	protected Button btn_login;
	public static String BASE_URL = "http://49.212.169.177/webapi/";
	public static AsyncHttpClient client = new AsyncHttpClient();
	public static String response;
	
	protected void showError(String mes) {
		Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Button btn_login = (Button) findViewById(R.id.btn_login);
		if(btn_login != null){
			checkToShow(btn_login);
		}
	}
	
	protected void goTopButtonPresses(){
		Intent intent = new Intent(getApplicationContext(),A001Activity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public boolean isLoggedIn() {
		SharedPreferences sharedPreferences = getSharedPreferences("login_flag", 0);
		Log.e("RE","RE"+ sharedPreferences.getString("app_id", "NT"));
		if(!sharedPreferences.getBoolean("login", false)){
			return false;
		}
		return true;
	}
	
	public void goToLogin(){
		Intent intent = new Intent(getApplicationContext(),B001Activity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void goToLogout(){
		SharedPreferences sharedPreferences = getSharedPreferences("login_flag", 0);
		Editor editor = sharedPreferences.edit();
		editor.remove("login");
		editor.commit();
		Log.e("re","REmove");
		Intent intent = new Intent(getApplicationContext(),A001Activity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	
	public void checkToShow(Button btn_login) {
		if(isLoggedIn()){
			btn_login.setBackgroundResource(R.drawable.logout_button);
			btn_login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					goToLogout();
				}
			});
		}else{
			btn_login.setBackgroundResource(R.drawable.login_button);
			btn_login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					goToLogin();
				}
			});
		}
	}
	
	
	public void mainPageClick() {
		if(isLoggedIn()){
			Intent intent = new Intent(getApplicationContext(),C001Activity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}else{
			Intent intent = new Intent(getApplicationContext(),B001Activity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
		}
	}
	
	private static final Pattern patten = Pattern
			.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

	public boolean validateEmail(String email) {

		if (!patten.matcher(email).matches()) {
			return false;
		} else {
			return true;
		}

	}
}
