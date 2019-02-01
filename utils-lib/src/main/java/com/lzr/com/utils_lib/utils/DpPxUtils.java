package com.lzr.com.utils_lib.utils;

import android.content.Context;

import com.lzr.com.utils_lib.common.UnDeadBag;

public class DpPxUtils {

    public static int dp2px(float dpValue) {
        final float scale = UnDeadBag.getInstance().getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
