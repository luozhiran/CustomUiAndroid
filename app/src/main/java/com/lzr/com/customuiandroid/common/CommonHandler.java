package com.lzr.com.customuiandroid.common;

import android.os.Handler;
import android.os.Message;

import com.lzr.com.customuiandroid.base.HandlerActivity;

import java.lang.ref.WeakReference;

public class CommonHandler extends Handler {

    private WeakReference<HandlerActivity> baseActivityWeakReference;

    public CommonHandler(HandlerActivity baseActivity) {
        this.baseActivityWeakReference = new WeakReference<HandlerActivity>(baseActivity);
    }

    @Override
    public void handleMessage(Message msg) {
        if (baseActivityWeakReference != null && baseActivityWeakReference.get() != null) {
            baseActivityWeakReference.get().handleMessage(msg);
        }
    }
}
