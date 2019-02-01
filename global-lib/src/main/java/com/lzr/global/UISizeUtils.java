package com.lzr.global;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

public class UISizeUtils {
    public UISizeUtils() {
    }

    public static int getMeasureSize(int measureSpec) {
        int width = View.MeasureSpec.getSize(measureSpec);
        return width;
    }

    public static int getMeasureMode(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return mode;
    }

    public static DensityData getScreenDensity_ByWindowManager(Activity context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        DensityData densityData = new DensityData();
        densityData.setWidth(mDisplayMetrics.widthPixels);
        densityData.setHeight(mDisplayMetrics.heightPixels);
        densityData.setDensity(mDisplayMetrics.density);
        densityData.setDensityDpi(mDisplayMetrics.densityDpi);
        return densityData;
    }

    public static DensityData getScreenDensity_ByResources(Context context) {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        DensityData densityData = new DensityData();
        densityData.setWidth(mDisplayMetrics.widthPixels);
        densityData.setHeight(mDisplayMetrics.heightPixels);
        densityData.setDensity(mDisplayMetrics.density);
        densityData.setDensityDpi(mDisplayMetrics.densityDpi);
        return densityData;
    }

    public static DensityData getDefaultScreenDensity(Activity context) {
        Display mDisplay = context.getWindowManager().getDefaultDisplay();
        DensityData densityData = new DensityData();
        densityData.setWidth(mDisplay.getWidth());
        densityData.setHeight(mDisplay.getDisplayId());
        return densityData;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(pxValue / fontScale + 0.5F);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5F);
    }
}
