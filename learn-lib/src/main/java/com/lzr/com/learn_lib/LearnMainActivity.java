package com.lzr.com.learn_lib;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzr.com.control_lib.BaseActivity;
import com.lzr.com.control_lib.HandlerActivity;
import com.lzr.com.learn_lib.learn_adapter.LearnAdapter;
import com.lzr.com.learn_lib.uiactivity.ClockUiActivity;
import com.lzr.com.learn_lib.uiactivity.DrawBitmapActivity;
import com.lzr.com.learn_lib.uiactivity.DrawUseActivity;

public class LearnMainActivity extends HandlerActivity {
    private RecyclerView mRecycle;
    private LearnAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_learn_main;
    }

    @Override
    public void initView() {
        mRecycle = findViewById(R.id.recycle);
        mAdapter = new LearnAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycle.setLayoutManager(mLayoutManager);
        mRecycle.addItemDecoration(new LinearSpacingItem());
        mRecycle.setAdapter(mAdapter);
        mAdapter.setOnClickListener(v -> {
            String str = mAdapter.getData((Integer) v.getTag());
            switch (str) {
                case "clock":
                    Intent intent = new Intent(this, ClockUiActivity.class);
                    startActivity(intent);
                    break;
                case "画布使用教程":
                     intent = new Intent(this, DrawUseActivity.class);
                    startActivity(intent);
                    break;
                case "画图片":
                    intent = new Intent(this, DrawBitmapActivity.class);
                    startActivity(intent);
                    break;
            }
        });
    }

    @Override
    public void initData() {
        mAdapter.addData("clock");
        mAdapter.addData("画布使用教程");
        mAdapter.addData("画图片");
        mAdapter.notifyDataSetChanged();
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
