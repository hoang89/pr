package com.csn.puripuri;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.csn.adapter.SimpleItemizedOverlay;
import com.csn.adapter.SimpleItemizedOverlay.OnTabOverlayItem;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class D015Activity extends MapActivity {
	private MapView mapView;
	private List<Overlay> mapOverlays;
	private Drawable drawable;
	private SimpleItemizedOverlay itemizedOverlay;
	private String lat;
	private String log;
	private Button d015_back;
	private String name;
	private String address;
	private String screen;
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.d015layout);
		BottomView bottomView = new BottomView(this);
		bottomView.createTab();
		d015_back = (Button) findViewById(R.id.d015_btn_back);
		
		lat = getIntent().getStringExtra("lat");
		
		log = getIntent().getStringExtra("log");
		name = getIntent().getStringExtra("shopname");
		address = getIntent().getStringExtra("address");
		screen = getIntent().getStringExtra("screen");
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();
		// first overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new SimpleItemizedOverlay(drawable, mapView);

		GeoPoint point = new GeoPoint((int) (Double.parseDouble(lat) * 1E6),
				(int) (Double.parseDouble(log) * 1E6));
		OverlayItem overlayItem = new OverlayItem(point,name,address);
		
		itemizedOverlay.addOverlay(overlayItem);
		itemizedOverlay.setTabOverlayItem(new OnTabOverlayItem() {
			
			@Override
			public void onTab(int index) {
				if(screen.equals("D001") || screen.equals("G006")){
				onBackPressed();
				}
			}
		});

		mapOverlays.add(itemizedOverlay);
		MapController mapController = mapView.getController();
		
		mapController.setZoom(16);
		mapController.animateTo(point);
		d015_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
