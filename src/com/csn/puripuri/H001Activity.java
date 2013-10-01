package com.csn.puripuri;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.csn.adapter.SimpleItemizedOverlay;
import com.csn.datasource.JsonParser;
import com.csn.entity.ShopDetailEntity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class H001Activity extends MapActivity implements LocationListener,
		OnClickListener {
	public static final String TAG = H001Activity.class.getSimpleName();
	private MapView mapView;
	private Button btnF001F002;
	private boolean isUseLocation = false;

	// add for use location manager
	private LocationManager locationManager;
	private MapController mapController;

	private Drawable drawable;
	private Drawable currentDrawable;
	private SimpleItemizedOverlay itemizedOverlay;
	private List<Overlay> mapOverlays;
	private OverlayItem currentPos;
	private SimpleItemizedOverlay currentItemizedOverlay;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.h001layout);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		
		//
		init();
		

		// add action
		addAction();

		// use location
		useLocation();

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

	private void addAction() {
		btnF001F002.setOnClickListener(this);
	}

	private void init() {
		drawable = getResources().getDrawable(R.drawable.marker1);

		btnF001F002 = (Button) findViewById(R.id.H001_gotoF001_F002);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		mapController = mapView.getController();

		mapController.setZoom(16);
		currentDrawable = getResources().getDrawable(R.drawable.marker2);
		itemizedOverlay = new SimpleItemizedOverlay(drawable, mapView);
		currentItemizedOverlay = new SimpleItemizedOverlay(currentDrawable, mapView);
		mapOverlays = mapView.getOverlays();
	}
	
	

	private void useLocation() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);

		if (provider == null) {
			provider = LocationManager.NETWORK_PROVIDER;
		}

		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			onLocationChanged(location);
		}

		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		// use location
		if (location != null && !isUseLocation) {
			isUseLocation = true;
			// movie to my location
			GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6),(int) (location.getLongitude() * 1E6));
			GeoPoint point2 = new GeoPoint((int)(36.0481103 * 1E6), (int)(136.187000*1E6));
			//Log.e("CU","CU:lat:  "+location.getLatitude() + "  long: "+location.getLongitude());
			mapController.animateTo(point2);
			
			if(currentPos == null){
				currentPos =  new OverlayItem(point2,"You here now!!","");
			}else{
				mapOverlays.remove(currentPos);
				currentPos =  new OverlayItem(point2,"You here now!!","");
			}
			currentItemizedOverlay.addOverlay(currentPos);
			mapOverlays.add(currentItemizedOverlay);
			// use location to get list
			callApi(location);
		}
	}

	private void callApi(Location location) {
		AsyncHttpClient client = new AsyncHttpClient();
		String url = "http://49.212.169.177/webapi/get_nearby_shops.php";
		RequestParams requestParams = new RequestParams();
		// 36.0481103 136.187464
		//requestParams.put("lat", location.getLatitude() + "");
		requestParams.put("lat", "36.0481103");
		requestParams.put("lon", "136.187000");
		//requestParams.put("lon", location.getLongitude() + "");
		Log.e("param",requestParams.toString());
		client.get(url, requestParams, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String res) {
				super.onSuccess(res);
				Log.d(TAG, res);
				List<ShopDetailEntity> lShopDetailEntities = JsonParser.getNearByShop(res);
				updateMap(lShopDetailEntities);
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				Log.e(TAG, arg1, arg0);
			}
		});
	}

	private void updateMap(final List<ShopDetailEntity> lShopDetailEntities) {

		if (lShopDetailEntities.isEmpty()) {
			return;
		}

		itemizedOverlay.setTabOverlayItem(new SimpleItemizedOverlay.OnTabOverlayItem() {
			
			@Override
			public void onTab(int index) {
				if(lShopDetailEntities != null && !lShopDetailEntities.isEmpty()){
					ShopDetailEntity shopDetailEntity =  lShopDetailEntities.get(index);
					Intent intent = new Intent(H001Activity.this, D001Activity.class);
					intent.putExtra("shopid", shopDetailEntity.getId());
					intent.putExtra("screen", "H001");
					startActivity(intent);
				}
			}
		});
		for (ShopDetailEntity shopDetailEntity : lShopDetailEntities) {
			GeoPoint point = new GeoPoint(
					(int) (Double.parseDouble(shopDetailEntity.getLatitude()) * 1E6),
					(int) (Double.parseDouble(shopDetailEntity.getLongitude()) * 1E6));
			OverlayItem overlayItem = new OverlayItem(point,shopDetailEntity.getName(), shopDetailEntity.getAddress());
			Log.d("SHOP","NAME: " + shopDetailEntity.getName() + " ADD: "+shopDetailEntity.getAddress());
			itemizedOverlay.addOverlay(overlayItem);
		}

		mapOverlays.add(itemizedOverlay);
		mapView.invalidate();
	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

	}

	@Override
	public void onClick(View view) {
		if (view == btnF001F002) {
				
			// goto F001 0r F002
			finish();
		}
	}
}