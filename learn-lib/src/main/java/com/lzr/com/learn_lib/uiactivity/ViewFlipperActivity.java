package com.lzr.com.learn_lib.uiactivity;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.lzr.com.control_lib.HandlerActivity;
import com.lzr.com.learn_lib.R;

import java.util.ArrayList;

public class ViewFlipperActivity extends HandlerActivity {

    private ViewFlipper mFlipperView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_flipper;
    }

    @Override
    public void initView() {
        mFlipperView = findViewById(R.id.view_flipper);
        mFlipperView.setAutoStart(true);
//        mFlipperView.setFlipInterval(200);
        mFlipperView.setOutAnimation(this, R.anim.anim_get_out);
        mFlipperView.setInAnimation(this, R.anim.anim_come_in);
        mFlipperView.startFlipping();
        createView(1);
        createView(2);

    }


    private void createView(int i) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_advertisement, null);
        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        Button bt = view.findViewById(R.id.button);
        tv1.setText("这个是支付宝打的广告哈：" + i);
        bt.setTag(String.valueOf(i));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewFlipperActivity.this,(String)v.getTag(),Toast.LENGTH_SHORT).show();
                mFlipperView.showNext();
            }
        });
        mFlipperView.addView(view);
        mFlipperView.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mFlipperView.stopFlipping();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void handleMessage(Message msg) {

    }
}
