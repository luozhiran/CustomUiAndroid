package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.DrawBitmapView;

public class DrawBitmapActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStart;
    private DrawBitmapView mDonghua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bitmap);
        initView();
    }

    private void initView() {
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mDonghua = (DrawBitmapView) findViewById(R.id.donghua);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.start) {
            mDonghua.check();
        } else {
        }
    }
}
