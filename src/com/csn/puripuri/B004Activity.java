package com.csn.puripuri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class B004Activity extends AppBaseActivity {
	private String email;
	private String password;
	private TextView b004_email;
	private Button b004_next;
	private Button b004_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b004);
		password = getIntent().getStringExtra("password");
		email = getIntent().getStringExtra("email");
		b004_email= (TextView) findViewById(R.id.b004_email);
		b004_next = (Button) findViewById(R.id.b004_next);
		b004_email.setText(email);
		b004_back = (Button) findViewById(R.id.b004_back_button);
		b004_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		b004_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),B001Activity.class);
				intent.putExtra("email", email);
				intent.putExtra("password", password);
				startActivity(intent);
			}
		});
	}

}
