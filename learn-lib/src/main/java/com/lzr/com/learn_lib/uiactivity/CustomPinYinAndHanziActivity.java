package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.CustomPinYinAndHanziView;

public class CustomPinYinAndHanziActivity extends AppCompatActivity {

    private CustomPinYinAndHanziView mCustomPinYinAndHanziView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pin_yin_and_hanzi);
        initView();
    }

    private void initView() {
        mCustomPinYinAndHanziView = (CustomPinYinAndHanziView) findViewById(R.id.customPinYinAndHanziView);
    }
}
