package com.lzr.com.customuiandroid.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.lzr.com.customuiandroid.common.CommonHandler;

public abstract class HandlerActivity extends BaseActivity {
    protected CommonHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initHandler() {
        mHandler = new CommonHandler(this);
    }

    public abstract void handleMessage(Message msg);
}
