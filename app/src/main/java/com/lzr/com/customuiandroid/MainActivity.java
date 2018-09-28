package com.lzr.com.customuiandroid;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.lzr.com.customuiandroid.adapter.LZAdapter;
import com.lzr.com.customuiandroid.base.BaseActivity;
import com.lzr.com.customuiandroid.base.HandlerActivity;

public class MainActivity extends HandlerActivity {

    private RecyclerView mRecycle;
    private LZAdapter mLzAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView() {
        mRecycle = findViewById(R.id.recycle);
    }

    @Override
    public void initData() {

    }
}
