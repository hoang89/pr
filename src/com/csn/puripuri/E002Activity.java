package com.csn.puripuri;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class E002Activity extends Activity {
	private Button backButton;
	private Button okButton;
	private EditText keyword;
	private EditText message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_left, R.anim.un_slide_left);
		setContentView(R.layout.e002);
		BottomView bottomView = new  BottomView(this);
		bottomView.createTab();
		backButton = (Button) findViewById(R.id.e002_btn_back);
		okButton = (Button) findViewById(R.id.e002_btn_ok);
		keyword = (EditText) findViewById(R.id.e002_keyword);
		message = (EditText) findViewById(R.id.e002_message);
		keyword.setText(E001Activity.searchParameterEntity.getKeyword());
		message.setText(E001Activity.searchParameterEntity.getComment());
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {

				E001Activity.searchParameterEntity.setKeyword(keyword.getText()
						.toString().trim());

				E001Activity.searchParameterEntity.setComment(message.getText()
						.toString().trim());

				onBackPressed();
			}
		});
	}

	@Override
	protected void onPause() {
		overridePendingTransition(R.anim.slide_right, R.anim.un_slide_right);
		super.onPause();
	}
}
