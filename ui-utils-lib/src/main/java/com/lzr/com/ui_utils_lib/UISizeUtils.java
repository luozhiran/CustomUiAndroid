package com.lzr.com.ui_utils_lib;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;

public class UISizeUtils {

    public static int getMeasureSize(int measureSpec) {
        int width = View.MeasureSpec.getSize(measureSpec);
        return width;
    }

    public static int getMeasureMode(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return mode;
    }

    // 通过WindowManager获取
    public static DensityData getScreenDensity_ByWindowManager(Activity context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        context.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        DensityData densityData = new DensityData();
        densityData.setWidth(mDisplayMetrics.widthPixels);
        densityData.setHeight(mDisplayMetrics.heightPixels);
        densityData.setDensity(mDisplayMetrics.density);
        densityData.setDensityDpi(mDisplayMetrics.densityDpi);
        return densityData;
    }

    // 通过Resources获取
    public static DensityData getScreenDensity_ByResources(Context context) {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        DensityData densityData = new DensityData();
        densityData.setWidth(mDisplayMetrics.widthPixels);
        densityData.setHeight(mDisplayMetrics.heightPixels);
        densityData.setDensity(mDisplayMetrics.density);
        densityData.setDensityDpi(mDisplayMetrics.densityDpi);
        return densityData;
    }

    // 获取屏幕的默认分辨率
    public static DensityData getDefaultScreenDensity(Activity context) {
        Display mDisplay = context.getWindowManager().getDefaultDisplay();
        DensityData densityData = new DensityData();
        densityData.setWidth(mDisplay.getWidth());
        densityData.setHeight(mDisplay.getDisplayId());
        return densityData;
    }



    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
