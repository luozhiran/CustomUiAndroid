package com.lzr.com.ui_utils_lib;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

public class ActionBarUtils {

    public static void hideActionBar(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getActionBar().hide();
        } else {
            activity.getSupportActionBar().hide();
        }
    }

    public static void getActionBarHeight(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getActionBar().getHeight();
        } else {
            activity.getSupportActionBar().getHeight();

        }
    }

}
