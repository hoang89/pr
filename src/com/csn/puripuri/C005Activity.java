package com.csn.puripuri;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class C005Activity extends AppBaseActivity{
	private String oldEmail;
	private String oldPassword;
	private EditText c005_old_email;
	private EditText c005_old_password;
	private SharedPreferences sharedPreferences;
	private Button c004_btn_top;
	private Button c004_btn_main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c005);
		sharedPreferences = getSharedPreferences("login_flag",0);
		oldEmail = sharedPreferences.getString("email", "");
		oldPassword = sharedPreferences.getString("password", "");
		c005_old_email = (EditText) findViewById(R.id.c005_old_email);
		c005_old_password = (EditText) findViewById(R.id.c005_old_password);
		c005_old_email.setText(oldEmail);
		c005_old_password.setText(oldPassword);
		c004_btn_top = (Button) findViewById(R.id.c005_btn_top);
		c004_btn_main = (Button) findViewById(R.id.c005_btn_main);
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
	}
}
