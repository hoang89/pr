package com.csn.puripuri;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class C004Activity extends AppBaseActivity {
	private String oldEmail;
	private String oldPassword;
	private EditText c004_old_email;
	private EditText c004_old_password;
	private EditText c004_new_email;
	private EditText c004_new_email_confirm;
	private EditText c004_current_password;
	private EditText c004_new_password;
	private String newEmail;
	private String newEmailConfirm;
	private String currentPassword;
	private String newPassword;
	private Button c004_submit_button;
	private String app_id;
	private SharedPreferences sharedPreferences;
	private Button c004_btn_top;
	private Button c004_btn_main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c004);
		sharedPreferences = getSharedPreferences("login_flag",0);
		oldEmail = sharedPreferences.getString("email", "");
		oldPassword = sharedPreferences.getString("password", "");
		app_id = sharedPreferences.getString("app_id", "");
		c004_old_email = (EditText) findViewById(R.id.c004_old_email);
		c004_old_password = (EditText) findViewById(R.id.c004_old_password);
		c004_old_email.setText(oldEmail);
		c004_old_password.setText(oldPassword);
		c004_new_email = (EditText) findViewById(R.id.c004_new_email);
		c004_new_email_confirm = (EditText) findViewById(R.id.c004_new_email_confirm);
		c004_current_password = (EditText) findViewById(R.id.c004_current_password);
		c004_new_password = (EditText) findViewById(R.id.c004_new_password);
		c004_submit_button = (Button) findViewById(R.id.c004_submit_button);
		c004_btn_top = (Button) findViewById(R.id.c004_btn_top);
		c004_btn_main = (Button) findViewById(R.id.c004_btn_main);
		c004_btn_top.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goTopButtonPresses();
			}
		});
		
		c004_btn_main.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mainPageClick();
			}
		});
		c004_submit_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				changeInfo();
			}

			
		});
	}
	
	private void changeInfo() {
		newEmail = c004_new_email.getText().toString().trim();
		newEmailConfirm = c004_new_email_confirm.getText().toString().trim();
		currentPassword = c004_current_password.getText().toString().trim();
		newPassword = c004_new_password.getText().toString().trim();
		
		if(checkValidate()){
			updateUserInfo();
		}
	}
	
	private void updateUserInfo(){
		RequestParams requestParams = new RequestParams();
		requestParams.put("app_user_id", app_id);
		requestParams.put("username", newEmail);
		requestParams.put("password", newPassword);
		client.get(BASE_URL+"change_app_user.php", requestParams, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				// TODO Auto-generated method stub
				super.onSuccess(res);
				Log.e("RES",res);
				checkChange(res);
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub
				super.onFailure(arg0, arg1);
				showError("Update faile, plesea try again");
			}
		});
	}
	
	private void checkChange(String res){
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(res);
			if(jsonObject.getInt("error_code") == 0){
				Editor editor = sharedPreferences.edit();
				editor.putString("email", newEmail);
				editor.putString("password", newPassword);
				editor.commit();
				Intent intent = new Intent(getApplicationContext(),C005Activity.class);
				startActivity(intent);
			}else{
				showError(jsonObject.getString("message"));
			}
		} catch (JSONException e) {
			showError("Cheng user info faile");
			e.printStackTrace();
		}
		
	}
	
	private boolean checkValidate(){
		if(!validateEmail(newEmail)){
			showError("Email format not valid");
			return false;
		}
		
		if(!newEmail.equals(newEmailConfirm)){
			showError("Email not match");
			return false;
		}
		
		if(!currentPassword.equals(oldPassword)){
			showError("Current password not match");
			return false;
		}
		
		if(newPassword.equals("")){
			showError("Password cannot blank");
			return false;
		}
		
		return true;
	}
}
