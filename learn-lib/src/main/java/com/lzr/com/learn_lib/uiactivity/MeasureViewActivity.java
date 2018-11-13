package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.RobotHeader;
import com.lzr.com.ui_utils_lib.ActionBarUtils;

public class MeasureViewActivity extends AppCompatActivity {

    private RobotHeader mRobotHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_view);
        initView();
        ActionBarUtils.hideActionBar(this);
    }

    private void initView() {

    }
}
