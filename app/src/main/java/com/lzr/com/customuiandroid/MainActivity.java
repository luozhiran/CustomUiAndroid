package com.lzr.com.customuiandroid;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzr.com.customuiandroid.adapter.LZAdapter;
import com.lzr.com.customuiandroid.base.BaseActivity;
import com.lzr.com.customuiandroid.base.HandlerActivity;

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

        });
    }

    @Override
    public void initData() {
        mLzAdapter.addData("第一个");
        mLzAdapter.notifyItemChanged(mLzAdapter.getSize() - 1);
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
