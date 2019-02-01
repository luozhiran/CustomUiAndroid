package com.lzr.com.learn_lib.uiactivity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzr.com.learn_lib.R;

public class TextFlickerActivity extends AppCompatActivity implements LifecycleObserver{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_flicker);
    }


}
