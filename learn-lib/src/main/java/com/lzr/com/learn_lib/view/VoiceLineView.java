package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class VoiceLineView extends View {
    private static final String TAG = "VoiceLineView";
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mViewWidth, mViewHeight;
    private int mItemWidth = 10;
    private int mItemHeight = 15;

    public VoiceLineView(Context context) {
        this(context, null);
    }

    public VoiceLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = getPaint(Color.BLUE, Paint.Style.FILL, 2);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        mViewWidth = w - getPaddingLeft() - getPaddingRight();
        mViewHeight = h - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    private Paint getPaint(int color, Paint.Style style, int strokeWidth) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setDither(true);
        return paint;
    }

    private void print(int widthMeasureSpec, int heightMeasureSpec) {
        StringBuilder sb = new StringBuilder();
        sb.append("宽测量模式:").append(getMeasureMode(MeasureSpec.getMode(widthMeasureSpec)))
                .append("  ")
                .append("宽 = ").append(String.valueOf(MeasureSpec.getSize(widthMeasureSpec)))
                .append("------")
                .append("高测量模式:").append(getMeasureMode(MeasureSpec.getMode(heightMeasureSpec)))
                .append("  ")
                .append("高 =")
                .append(String.valueOf(MeasureSpec.getSize(heightMeasureSpec)));
        Log.e(TAG, sb.toString());
    }


    private String getMeasureMode(int mode) {
        String result = "";
        switch (mode) {
            case MeasureSpec.AT_MOST:
                result = "AT_MOST";
                break;
            case MeasureSpec.EXACTLY:
                result = "EXACTLY";
                break;
            case MeasureSpec.UNSPECIFIED:
                result = "UNSPECIFIED";
                break;
        }
        return result;
    }
}
