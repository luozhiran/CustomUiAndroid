package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;

public class Bezier extends View {
    private String tag = "Bezier";

    private Paint mWhitePaint, mRedPaint, mYellowPaing, mBluePaint, mGrayPaint;
    private Paint mWhiteFillPaint, mRedFillPaint, mYellowFillPaing, mBlueFillPaint, mGrayFillPaint;
    private int strockWidth = 2;
    private int mWidth, mHeight;
    private int centerX, centerY;


    public Bezier(Context context) {
        this(context, null);
    }

    public Bezier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mWhitePaint = new Paint();
        mWhitePaint.setStyle(Paint.Style.STROKE);
        mWhitePaint.setStrokeWidth(strockWidth);
        mWhitePaint.setColor(Color.WHITE);

        mRedPaint = new Paint();
        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setStrokeWidth(strockWidth);
        mRedPaint.setColor(Color.RED);

        mYellowPaing = new Paint();
        mYellowPaing.setStyle(Paint.Style.STROKE);
        mYellowPaing.setStrokeWidth(strockWidth);
        mYellowPaing.setColor(Color.YELLOW);

        mBluePaint = new Paint();
        mBluePaint.setStyle(Paint.Style.STROKE);
        mBluePaint.setStrokeWidth(strockWidth);
        mBluePaint.setColor(Color.BLUE);

        mGrayPaint = new Paint();
        mGrayPaint.setStyle(Paint.Style.STROKE);
        mGrayPaint.setStrokeWidth(strockWidth);
        mGrayPaint.setColor(Color.GRAY);


        mWhiteFillPaint = new Paint();
        mWhiteFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWhiteFillPaint.setStrokeWidth(strockWidth);
        mWhiteFillPaint.setColor(Color.WHITE);

        mRedFillPaint = new Paint();
        mRedFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRedFillPaint.setStrokeWidth(strockWidth);
        mRedFillPaint.setColor(Color.RED);

        mYellowFillPaing = new Paint();
        mYellowFillPaing.setStyle(Paint.Style.FILL_AND_STROKE);
        mYellowFillPaing.setStrokeWidth(strockWidth);
        mYellowFillPaing.setColor(Color.YELLOW);

        mBlueFillPaint = new Paint();
        mBlueFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBlueFillPaint.setStrokeWidth(strockWidth);
        mBlueFillPaint.setColor(Color.BLUE);

        mGrayFillPaint = new Paint();
        mGrayFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mGrayFillPaint.setStrokeWidth(strockWidth);
        mGrayFillPaint.setColor(Color.GRAY);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
