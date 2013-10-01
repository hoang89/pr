package com.csn.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.csn.datasource.Constant;
import com.csn.datasource.JsonParser;
import com.csn.entity.CouponEntity;
import com.csn.puripuri.AppBaseActivity;
import com.csn.puripuri.R;
import com.csn.utils.ConvertUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class D013Fragment extends Fragment {
	private String shopId;
	private ArrayList<CouponEntity> listCouponEntities;
	private LinearLayout listCoupon;
	private LinearLayout couponDetail;
	private int j = 0;
	private int loadImageSize;
	private LinearLayout d013_success;
	private TextView d013_error;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.d013layout, null);
		loadImageSize = (int) (getActivity().getWindowManager()
				.getDefaultDisplay().getWidth() - ConvertUtils.pxFromDp(80,
				getActivity()));
		d013_error = (TextView) view.findViewById(R.id.d013_error);
		d013_success = (LinearLayout) view.findViewById(R.id.d013_success);
		
		shopId = getActivity().getIntent().getStringExtra("shopid");
		listCoupon = (LinearLayout) view.findViewById(R.id.listCoupon);
		couponDetail = (LinearLayout) view.findViewById(R.id.couponDetail);
		if (listCouponEntities == null) {
			getCoupons();
		} else {
			if(listCouponEntities.size() > 0){
			buildCoupons();
			d013_success.setVisibility(View.VISIBLE);
			d013_error.setVisibility(View.GONE);
			}else{
				d013_error.setVisibility(View.VISIBLE);
			}
		}
		return view;
	}

	public void getCoupons() {
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams requestParams = new RequestParams();
		requestParams.put("shop_id", shopId);
		client.get(AppBaseActivity.BASE_URL + "get_coupons.php", requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String res) {
						super.onSuccess(res);
						Log.e("RESCOP", res);
						listCouponEntities = JsonParser.getCoupons(res);
						if(listCouponEntities != null && listCouponEntities.size() > 0){
							buildCoupons();
							d013_success.setVisibility(View.VISIBLE);
							d013_error.setVisibility(View.GONE);
						}else{
							d013_error.setVisibility(View.VISIBLE);
							d013_success.setVisibility(View.GONE);
						}
					}
				});
	}

	public void buildCoupons() {

		if (listCouponEntities != null) {
			for (int i = 0; i < listCouponEntities.size(); i++) {
				CouponEntity c = listCouponEntities.get(i);
				j = i;
				View v = LayoutInflater.from(getActivity()).inflate(
						R.layout.d013_coupon_item, null);
				if (v != null) {
					TextView title = (TextView) v.findViewById(R.id.d013_coupon_title);
					title.setText(c.getTitle());

					TextView time = (TextView) v.findViewById(R.id.d013_coupon_time);
					time.setText(c.getEffectiveBegin() + "~"+ c.getEffectiveEnd());

					TextView condition = (TextView) v.findViewById(R.id.d013_coupon_condition);
					condition.setText(c.getUseCondition());
					v.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							setOnItemClick(j);
						}
					});
					listCoupon.addView(v);
				}
			}
			listCoupon.setVisibility(View.VISIBLE);
		}
	}

	public void setOnItemClick(int position) {
		if (listCoupon.isShown()) {
			listCoupon.setVisibility(View.GONE);
			couponDetail.removeAllViews();
		}
		CouponEntity c = listCouponEntities.get(position);
		View v = LayoutInflater.from(getActivity()).inflate(
				R.layout.d013_coupon_detail_item, null);
		AQuery aQuery = new AQuery(v);
		TextView title = (TextView) v.findViewById(R.id.d013_coupon_title);
		title.setText(c.getTitle());

		TextView time = (TextView) v.findViewById(R.id.d013_coupon_time);
		time.setText(c.getEffectiveBegin() + "~" + c.getEffectiveEnd());

		TextView condition = (TextView) v
				.findViewById(R.id.d013_coupon_condition);
		condition.setText(c.getUseCondition());

		TextView detai = (TextView) v.findViewById(R.id.d013_coupon_detail);
		detai.setText(c.getDetail());

		TextView note = (TextView) v.findViewById(R.id.d013_coupon_note);
		note.setText(c.getNote());
		aQuery.id(R.id.d013_coupon_image).image(c.getImage(),
				Constant.memCache, Constant.fileCache, loadImageSize, AQuery.GONE, null,
				0, AQuery.RATIO_PRESERVE);
		aQuery.id(R.id.d013_coupon_close).getButton()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (couponDetail.isShown()) {
							couponDetail.setVisibility(View.GONE);
							listCoupon.setVisibility(View.VISIBLE);
						}
					}
				});
		couponDetail.addView(v);
		couponDetail.setVisibility(View.VISIBLE);
	}
}
