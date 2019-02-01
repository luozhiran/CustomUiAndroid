package com.lzr.com.customuiandroid;

import android.app.Application;
import com.lzr.com.utils_lib.common.UnDeadBag;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UnDeadBag.getInstance().init(this);
    }
}
