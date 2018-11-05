package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.HanZiView;

public class OnlyHanziActivity extends AppCompatActivity {

    private HanZiView mPinying;
    private String chinese = "池塘里有一群小蝌蚪，大大的脑袋，黑灰色的身子，甩着长长的尾巴";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_hanzi);
        initView();
    }

    private void initView() {
        mPinying = (HanZiView) findViewById(R.id.pinying);
        mPinying.setText(chinese);
    }
}
