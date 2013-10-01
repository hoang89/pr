package com.csn.puripuri;

import com.androidquery.AQuery;
import com.csn.datasource.JsonParser;
import com.csn.entity.ShopDetailEntity;
import com.csn.fragments.D014Fragment;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class G006Activity extends AppFragmentBaseActivity {
	private String jobdetai = "JOB";
	private D014Fragment d014;
	private ShopDetailEntity shopDetailEntity;
	private String shopId;
	private int loadImageSize;
	private TextView g006_shop_name;
	private TextView g006_genre_name;
	private TextView g006_des;
	private TextView g006_phone;
	private ImageView g006_map_button;
	private AQuery aQuery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g006);
		getUIComponent();
		shopId = getIntent().getExtras().getString("shopid");
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		loadImageSize = getWindowManager().getDefaultDisplay().getWidth();
		aQuery = new AQuery(this);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (getSupportFragmentManager().findFragmentByTag(jobdetai) == null) {
			d014 = new D014Fragment();
			ft.add(R.id.othercontent, d014, jobdetai);
		} else {
			ft.attach(d014);
		}
		getSupportFragmentManager().executePendingTransactions();
		ft.commit();
		getShopInfo();
		setUIEvent();
	}
	
	public void getUIComponent() {
		g006_shop_name = (TextView) findViewById(R.id.g006_tv_shopname);
		g006_genre_name = (TextView) findViewById(R.id.g006_tv_genrename);
		g006_des = (TextView) findViewById(R.id.g006_tv_description);
		g006_phone = (TextView) findViewById(R.id.g006_shop_phone);
		g006_map_button = (ImageView) findViewById(R.id.g006_map_button);
	}
	
	public void setUIComponent() {
		if(shopDetailEntity != null){
			aQuery.id(R.id.g006_avatar).visible()
			.image(JsonParser.BASE_IMAGE+ shopDetailEntity.getImageUrl(), true, false, loadImageSize,R.drawable.d001_image_sample,null,0,1.0f/2.0f);
			g006_genre_name.setText(shopDetailEntity.getGenreEntity().getName());
			g006_des.setText(Html.fromHtml(shopDetailEntity.getDescription()));
			g006_phone.setText(shopDetailEntity.getPhone());
			g006_shop_name.setText(shopDetailEntity.getName());
		}
	}
	
	public void setUIEvent() {
		g006_map_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (shopDetailEntity != null && !shopDetailEntity.getLatitude().equals("")) {
					Intent intent = new Intent(getApplicationContext(),D015Activity.class);
					intent.putExtra("lat", shopDetailEntity.getLatitude());
					intent.putExtra("log", shopDetailEntity.getLongitude());
					intent.putExtra("shopname", shopDetailEntity.getName());
					intent.putExtra("address", shopDetailEntity.getAddress());
					intent.putExtra("screen", "G006");
					startActivity(intent);
				}
			}
		});
		
		g006_phone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (shopDetailEntity != null
						&& !shopDetailEntity.getPhone().equals("")) {
					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + shopDetailEntity.getPhone()));
					startActivity(intent);
				}
			}
		});
	}
	
	public void getShopInfo() {
		RequestParams requestParams = new RequestParams();
		requestParams.put("shop_id", shopId);
		client.get(BASE_URL + "get_shop_details.php", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						shopDetailEntity = JsonParser.getShopDetail(res);
						setUIComponent();
					}
				});
	}

}
