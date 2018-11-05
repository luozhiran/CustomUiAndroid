package com.lzr.com.learn_lib;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzr.com.control_lib.HandlerActivity;
import com.lzr.com.learn_lib.funactivity.ForceKillActivity;
import com.lzr.com.learn_lib.learn_adapter.LearnAdapter;
import com.lzr.com.learn_lib.uiactivity.BezierActivity;
import com.lzr.com.learn_lib.uiactivity.CanvasVonvertTouchActivity;
import com.lzr.com.learn_lib.uiactivity.ClockUiActivity;
import com.lzr.com.learn_lib.uiactivity.DrawAcreActivity;
import com.lzr.com.learn_lib.uiactivity.DrawBitmapActivity;
import com.lzr.com.learn_lib.uiactivity.DrawLineActivity;
import com.lzr.com.learn_lib.uiactivity.DrawUseActivity;
import com.lzr.com.learn_lib.uiactivity.OnlyHanziActivity;
import com.lzr.com.learn_lib.uiactivity.OnlyPingyinActivity;
import com.lzr.com.learn_lib.uiactivity.PathMeasureActivity;
import com.lzr.com.learn_lib.uiactivity.PinYinAndHanziActivity;
import com.lzr.com.learn_lib.uiactivity.RegionActivity;
import com.lzr.com.learn_lib.uiactivity.RemoteControlMenuActivity;
import com.lzr.com.learn_lib.uiactivity.TextActivity;
import com.lzr.com.learn_lib.uiactivity.TextFlickerActivity;
import com.lzr.com.learn_lib.uiactivity.TouchLineActivity;
import com.lzr.com.learn_lib.uiactivity.RegionClickActivity;
import com.lzr.com.learn_lib.uiactivity.WordActivity;

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
                case "弧形练习":
                    intent = new Intent(this, DrawAcreActivity.class);
                    startActivity(intent);
                    break;
                case "路劲练习":
                    intent = new Intent(this, DrawLineActivity.class);
                    startActivity(intent);
                    break;
                case "贝塞尔曲":
                    intent = new Intent(this, BezierActivity.class);
                    startActivity(intent);
                    break;
                case "汉子描红":
                    intent = new Intent(this, TextActivity.class);
                    startActivity(intent);
                    break;
                case "汉子书写":
                    intent = new Intent(this, WordActivity.class);
                    startActivity(intent);
                    break;
                case "PathMeasure使用":
                    intent = new Intent(this, PathMeasureActivity.class);
                    startActivity(intent);
                    break;
                case "touch划线":
                    intent = new Intent(this, TouchLineActivity.class);
                    startActivity(intent);
                    break;
                case "强制杀死进程":
                    intent = new Intent(this, ForceKillActivity.class);
                    startActivity(intent);
                    break;
                case "Region类使用":
                    intent = new Intent(this, RegionActivity.class);
                    startActivity(intent);
                    break;
                case "简单事件":
                    intent = new Intent(this, RegionClickActivity.class);
                    startActivity(intent);
                    break;
                case "坐标系变换测试":
                    intent = new Intent(this, CanvasVonvertTouchActivity.class);
                    startActivity(intent);
                    break;
                case "控制菜单":
                    intent = new Intent(this, RemoteControlMenuActivity.class);
                    startActivity(intent);
                    break;
                case "文字闪烁":
                    intent = new Intent(this, TextFlickerActivity.class);
                    startActivity(intent);
                    break;
                case "汉子排版":
                    intent = new Intent(this, OnlyHanziActivity.class);
                    startActivity(intent);
                    break;
                case "拼音排版":
                    intent = new Intent(this, OnlyPingyinActivity.class);
                    startActivity(intent);
                    break;
                case "文字带拼音":
                    intent = new Intent(this, PinYinAndHanziActivity.class);
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
        mAdapter.addData("弧形练习");
        mAdapter.addData("路劲练习");
        mAdapter.addData("贝塞尔曲");
        mAdapter.addData("汉子描红");
        mAdapter.addData("汉子书写");
        mAdapter.addData("PathMeasure使用");
        mAdapter.addData("touch划线");
        mAdapter.addData("强制杀死进程");
        mAdapter.addData("Region类使用");
        mAdapter.addData("简单事件");
        mAdapter.addData("坐标系变换测试");
        mAdapter.addData("控制菜单");
        mAdapter.addData("文字闪烁");
        mAdapter.addData("汉子排版");
        mAdapter.addData("拼音排版");
        mAdapter.addData("文字带拼音");
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
