package com.csn.puripuri;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class B001Activity extends AppBaseActivity{
	private Button b001_to_b002;
	private Button b001_to_c001;
	private EditText b001_email;
	private EditText b001_password;
	private String email;
	private String password;
	private ProgressDialog progressDialog;
	private String app_user_id;
	private Button b001_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b001);
		getUIComponent();
		setUIEvent();
	}
	
	public void getUIComponent() {
		b001_to_b002 = (Button) findViewById(R.id.b001_to_b002);
		b001_to_c001 = (Button) findViewById(R.id.b001_to_c001);
		b001_email = (EditText) findViewById(R.id.b001_email);
		b001_back = (Button) findViewById(R.id.b001_back_button);
		b001_password = (EditText) findViewById(R.id.b001_password);
		progressDialog = new ProgressDialog(B001Activity.this);
		progressDialog.setTitle("Loging in ...");
	}
	
	
	private boolean checkCondition() {
		if(b001_email.getText().toString().trim().equals("")){
			showError("Email should provided");
			return false;
		}
		if(!validateEmail(b001_email.getText().toString().trim())){
			showError("Email not valid");
			return false;
		}
		
		if(b001_password.getText().toString().trim().equals("")){
			showError("Password should provided");
			return false;
		}
		
		
		return true;
	}
	
	public void setUIEvent() {
		b001_to_b002.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),B002Activity.class);
				startActivity(intent);
			}
		});
		
		b001_to_c001.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(checkCondition()){
					progressDialog.show();
					login();
				}
			}
		});
		
		b001_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	private void checkLogin(String res){
		try {
			JSONObject jsonObject = new JSONObject(res);
			if(jsonObject.getInt("error_code") == 0){
				app_user_id = jsonObject.getJSONObject("data").getString("app_user_id");
				SharedPreferences.Editor editor = getSharedPreferences("login_flag", 0).edit();
				editor.putBoolean("login", true);
				editor.putString("app_id", app_user_id);
				editor.putString("email", email);
				editor.putString("password", password);
				editor.commit();
				showError("Login Success");
				mainPageClick();
			}else{
				showError(jsonObject.getString("message"));
			}
		} catch (JSONException e) {
			showError("Login faile");
			e.printStackTrace();
		}
	}
	
	private void login() {
		email = b001_email.getText().toString().trim();
		password = b001_password.getText().toString().trim();
		RequestParams requestParams = new RequestParams();
		requestParams.put("username", email);
		requestParams.put("password", password);
		client.get(BASE_URL+"authorize_app_user.php", requestParams,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				checkLogin(res);
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				showError("Login faile");
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
				if(progressDialog != null && progressDialog.isShowing()){
					progressDialog.dismiss();
				}
			}
		});
	}
}
