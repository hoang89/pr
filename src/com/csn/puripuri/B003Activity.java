package com.csn.puripuri;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class B003Activity extends AppBaseActivity {
	private String password;
	private String email;
	private EditText b003_email;
	private EditText b003_password;
	private Button b003_next;
	private ProgressDialog progressDialog;
	private Button b003_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b003);
		password = getIntent().getStringExtra("password");
		email = getIntent().getStringExtra("email");
		getUIComponent();
		setUIComponent();
	}
	
	
	private void getUIComponent() {
		b003_email = (EditText) findViewById(R.id.b003_email);
		b003_password = (EditText) findViewById(R.id.b003_password);
		b003_next = (Button) findViewById(R.id.b003_next);
		b003_back = (Button) findViewById(R.id.b003_back_button);
		progressDialog = new ProgressDialog(B003Activity.this);
		progressDialog.setTitle("Signing up ...");
	}
	
	private void setUIComponent(){
		b003_email.setText(email);
		b003_password.setText(password);
		b003_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressDialog.show();
				registre();
			}
		});
		b003_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	private void registre() {
		
		RequestParams requestParams = new RequestParams();
		requestParams.put("username", email);
		requestParams.put("password", password);
		client.get(BASE_URL+"register_app_user.php", requestParams,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String res) {
				// TODO Auto-generated method stub
				super.onSuccess(res);
				checkRegistre(res);
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub
				super.onFailure(arg0, arg1);
				showError("Registre faile");
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				if(progressDialog != null && progressDialog.isShowing()){
					progressDialog.dismiss();
				}
			}
		});
	}
	
	private void checkRegistre(String res) {
		if (res != null) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(res);
				if (jsonObject.getInt("error_code") == 0) {
					showError("Registre success");
					Intent intent = new Intent(getApplicationContext(),B004Activity.class);
					intent.putExtra("email", email);
					intent.putExtra("password", password);
					startActivity(intent);
				} else {
					showError(jsonObject.getString("message"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			showError("Registre faile");
		}
			
	}
}
