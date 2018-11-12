package com.lzr.com.learn_lib.uiactivity;

import android.app.ActionBar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzr.com.learn_lib.R;
import com.lzr.com.ui_utils_lib.ActionBarUtils;

public class MeasureViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_view);
        ActionBarUtils.hideActionBar(this);

    }
}
