package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lzr.com.control_lib.HandlerActivity;
import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.HorizontalProgressBar;

public class ProgressActivity extends HandlerActivity implements View.OnClickListener {

    private HorizontalProgressBar mProgress;
    private Button mStart;
    private ProgressBar mAndroidProgress;
    private Button mAndroidStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 1) {
            if (mProgress.getProgress() < 100) {
                mProgress.setProgress(mProgress.getProgress() + 2);
            }
        } else if (msg.what == 2) {
            mAndroidProgress.setProgress(mAndroidProgress.getProgress() + 2);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_progress;
    }

    @Override
    public void initView() {
        mProgress = findViewById(R.id.progress);
        mStart = findViewById(R.id.start);
        mAndroidProgress = (ProgressBar) findViewById(R.id.androidProgress);
        mAndroidStart = (Button) findViewById(R.id.androidStart);
        mStart.setOnClickListener(this);
        mAndroidStart.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.start) {
            mHandler.sendEmptyMessage(1);
        } else if (i == R.id.androidStart) {
            mHandler.sendEmptyMessage(2);
        } else {

        }
    }
}
