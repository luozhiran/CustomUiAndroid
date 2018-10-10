package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 快照(save)和回滚(restore)使用教程
 *
 * save 把当前的画布的状态进行保存，然后放入特定的栈中
 * saveLayerXxx 新建一个图层，并放入特定的栈中
 * restore  把栈中最顶层的画布状态取出来，并按照这个状态恢复当前的画布
 * restoreToCount 弹出指定位置及其以上所有的状态，并按照指定位置的状态进行恢复
 * getSaveCount  获取栈中内容的数量(即保存次数)
 */
public class DrawUseView extends View {

    public DrawUseView(Context context) {
        super(context);
    }

    public DrawUseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
