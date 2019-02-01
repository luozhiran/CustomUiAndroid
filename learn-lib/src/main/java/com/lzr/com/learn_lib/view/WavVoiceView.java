package com.lzr.com.learn_lib.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lzr.com.utils_lib.common.UnDeadBag;

import java.util.Random;

public class WavVoiceView extends View {

    private static final String TAG = "WavVoiceView";
    private int[] mAddValueArray = {10,12,14,16,18,20,22,24,26,28,30};
    private int[] mAddShare = {2,3,4,5,6,7};
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mViewWidth, mViewHeight;
    private int RECT_WIDTH = 3;
    private int RECT_HEIGHT = 3 * RECT_WIDTH;
    private int MAX_HEIGHT = RECT_HEIGHT * 10;
    private int MIN_WIDTH = 200;
    private int SPACE_OFFSET = 20;
    private int mRectNum;
    private int startX, startY;
    private Random mRandom;
    private int mValue;
    private boolean mAnimIsRunning;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1){
                if (mAnimIsRunning) {
                    mHandler.sendEmptyMessageDelayed(1, 100);
                    invalidate();
                }
            }else {
                mHandler.sendEmptyMessageDelayed(2, 200);
                invalidate();
            }

            return true;
        }
    });

    public WavVoiceView(Context context) {
        this(context, null);
    }

    public WavVoiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = getPaint(Color.BLUE, Paint.Style.FILL, 2);
        setBackgroundColor(Color.parseColor("#f3f3f3"));
        mRandom = new Random();
    }

    private Paint getPaint(int color, Paint.Style style, int strokeWidth) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        paint.setDither(true);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        print(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mViewWidth = w - getPaddingLeft() - getPaddingRight();
        mViewHeight = h - getPaddingTop() - getPaddingBottom();
        mRectNum = mWidth / (RECT_WIDTH + SPACE_OFFSET);
        startX = (mViewWidth - (RECT_WIDTH + SPACE_OFFSET) * mRectNum + SPACE_OFFSET) / 2;
        startY = getPaddingTop() + (mViewHeight-RECT_HEIGHT)/2;
        Log.e(TAG, mRectNum + "");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAnimIsRunning){
            drawRectAnim(canvas);
        }else {
            drawRect(canvas);
        }
    }


    private void drawRect(Canvas canvas) {
        RectF rect = null;
        int cx = startX;
        int cy = startY;
        int value = 1;
//        for (int i = 0; i < mRectNum; i++) {
//            value = mRandom.nextInt(mAddShare.length);
//            rect = new RectF(cx, cy, cx + RECT_WIDTH, cy - (RECT_HEIGHT+mAddShare[value]));
//            cx = cx + RECT_WIDTH + SPACE_OFFSET;
//            canvas.drawRoundRect(rect, RECT_WIDTH / 2, RECT_WIDTH / 2, mPaint);
//        }

        rect = new RectF(cx, 39, cx + 20, 0);

        canvas.drawRoundRect(rect, RECT_WIDTH / 2, RECT_WIDTH / 2, mPaint);
    }

    private void drawRectAnim(Canvas canvas) {
        RectF rect = null;
        int cx = startX;
        int cy = startY;
        int value=1;
        for (int i = 0; i < mRectNum; i++) {
            value =  mRandom.nextInt(mAddValueArray.length);
            rect = new RectF(cx, cy, cx + RECT_WIDTH, cy - (RECT_HEIGHT+mAddValueArray[value]));
            cx = cx + RECT_WIDTH + SPACE_OFFSET;
            canvas.drawRoundRect(rect, RECT_WIDTH / 2, RECT_WIDTH / 2, mPaint);
        }
    }

    public void startAnim() {
        mAnimIsRunning = true;
        mHandler.sendEmptyMessage(1);
    }

    public void stopAnim(){
        mAnimIsRunning = false;
    }

    public void initAnim(){
        mHandler.sendEmptyMessageDelayed(2, 200);
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
