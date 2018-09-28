package com.lzr.com.customuiandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler();
        initView();
        initData();
    }

    public void initHandler() {

    }

    public abstract void initView();

    public abstract void initData();
}
