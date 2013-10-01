package com.csn.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class ConvertUtils {
	public float dpToPixel(int dp,Resources resources) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
	}
	
	public static float dpFromPx(float px,Context context)
	{
	    return px / context.getResources().getDisplayMetrics().density;
	}


	public static float pxFromDp(float dp,Context context)
	{
	    return dp * context.getResources().getDisplayMetrics().density;
	}
}
