package com.lzr.com.customuiandroid;

import android.app.Application;

import com.lzr.com.learn_lib.undead_bag.UnDeadBag;
import com.lzr.com.ui_utils_lib.UISizeUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UnDeadBag.getInstance().ResourcesDensityData = UISizeUtils.getScreenDensity_ByResources(this);
    }
}
