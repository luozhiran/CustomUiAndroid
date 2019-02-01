package com.lzr.com.customuiandroid;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.lzr.com.control_lib.HandlerActivity;
import com.lzr.com.customuiandroid.adapter.LZAdapter;
import com.lzr.com.learn_lib.LearnMainActivity;


public class MainActivity extends HandlerActivity {

    private RecyclerView mRecycle;
    private LZAdapter mLzAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mRecycle = findViewById(R.id.recycle);
        mLzAdapter = new LZAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycle.setLayoutManager(mLayoutManager);
        mRecycle.addItemDecoration(new LinearSpacingItem());
        mRecycle.setAdapter(mLzAdapter);
        mLzAdapter.setOnClickListener(v -> {
            String str = mLzAdapter.getData((Integer) v.getTag());
            switch (str) {
                case "learn-lib":
                    Intent intent = new Intent(this, LearnMainActivity.class);
                    startActivity(intent);
                    break;
                case "release-lib":
//                    startActivity(new Intent(this,VideoPlayActivity.class));
                    startActivity(new Intent(this,TestActivity.class));
                    break;
            }
        });
//        mVideo.setVideoPath(Environment.getExternalStorageState()+"/.yqtec/ProxyBuffer/61mp4/AQJY/30.MP4");

    }


    @Override
    public void initData() {
        mLzAdapter.addData("learn-lib");
        mLzAdapter.addData("release-lib");
        mLzAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleMessage(Message msg) {

    }

    //设置recycleview的边界
    private static class LinearSpacingItem extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int specialPosition = parent.getChildLayoutPosition(view);
            if (specialPosition == 0) {
                outRect.top = 10;
            } else {
                outRect.top = 20;
            }
            outRect.left = 40;
            outRect.right = 40;
        }
    }


}
