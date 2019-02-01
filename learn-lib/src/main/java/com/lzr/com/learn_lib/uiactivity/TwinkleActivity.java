package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.TwinkleView;

public class TwinkleActivity extends AppCompatActivity {

    private TwinkleView mTwinkleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twinkle);
        initView();
    }

    private void initView() {
        mTwinkleView = findViewById(R.id.twinkleView);
    }
}
