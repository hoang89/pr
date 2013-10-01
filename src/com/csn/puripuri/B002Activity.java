package com.csn.puripuri;

import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class B002Activity extends AppBaseActivity {
	private EditText b002_email;
	private EditText b002_password;
	private EditText b002_password_confirm;
	private Button b002_button_confirm;
	private Button b002_back;
	private TextView b002_help;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b002);
		b002_help = (TextView) findViewById(R.id.b002_help);
		Pattern patternUseInfo = Pattern.compile(getResources().getString(R.string.use_info));
		Linkify.addLinks(b002_help, patternUseInfo,"puripuri_link://");
		getUIComponent();
		setUIEvent();
	}
	
	@SuppressLint("ParserError")
	private void getUIComponent() {
		b002_email = (EditText) findViewById(R.id.b002_email);
		b002_password = (EditText) findViewById(R.id.b002_password);
		b002_password_confirm = (EditText) findViewById(R.id.b002_password_confirm);
		b002_button_confirm = (Button) findViewById(R.id.b002_button_confirm);
		b002_back = (Button) findViewById(R.id.b002_back_button);
		
		
	}
	
	private void setUIEvent() {
		
		
		b002_button_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(checkCondition()){
					Intent intent = new Intent(getApplicationContext(),B003Activity.class);
					intent.putExtra("email", b002_email.getText().toString().trim());
					intent.putExtra("password", b002_password.getText().toString().trim());
					startActivity(intent);
				}
			}
		});
		
		b002_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	private boolean checkCondition() {
		if(b002_email.getText().toString().trim().equals("")){
			showError("Email should provided");
			return false;
		}
		if(!validateEmail(b002_email.getText().toString().trim())){
			showError("Email not valid");
			return false;
		}
		
		if(b002_password.getText().toString().trim().equals("")){
			showError("Password should provided");
			return false;
		}
		
		if(b002_password_confirm.getText().toString().trim().equals("")){
			showError("Password confirm should provided");
			return false;
		}
		
		if(!b002_password.getText().toString().trim().equals(b002_password_confirm.getText().toString().trim())){
			showError("Password not match");
			return false;
		}
		
		
		return true;
	}

}
