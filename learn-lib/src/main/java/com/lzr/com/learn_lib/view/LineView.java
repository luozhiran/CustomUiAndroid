package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LineView extends View {

    private String tag = "LineView";

    private Paint mWhitePaint, mRedPaint, mYellowPaing, mBluePaint, mGrayPaint;
    private Paint mWhiteFillPaint, mRedFillPaint, mYellowFillPaing, mBlueFillPaint, mGrayFillPaint;
    private int strockWidth = 2;
    private int mWidth, mHeight;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
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
        Log.e(tag, getMeasureMode(widthMeasureSpec) + " " + getMeasureMode(heightMeasureSpec));
        Log.e(tag, MeasureSpec.getSize(widthMeasureSpec) + "  " + MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRectCircle(canvas);
        drawArc(canvas);
        drawCircle(canvas);
    }

    private void drawRectCircle(Canvas canvas) {
        Path path = new Path();
        Path src = new Path();
        path.addRect(10, 10, 110, 110, Path.Direction.CW);
        src.addCircle(60, 60, 25, Path.Direction.CW);
        path.addPath(src, 0, 50);
        canvas.drawPath(path, mRedPaint);
    }

    private void drawArc(Canvas canvas) {
        Path path = new Path();
        path.moveTo(110,110);
        path.lineTo(200, 200);
        RectF rectF = new RectF(200,200,400,400);
        path.arcTo(rectF,180,180);
        if (path.isEmpty())
        canvas.drawPath(path, mGrayPaint);
    }

    private void drawCircle(Canvas canvas){
        Path path = new Path();
        path.moveTo(125,228);
        path.lineTo(119,233);
        path.lineTo(122,241);
        path.lineTo(131,252);
        path.lineTo(144,272);
        path.lineTo(156,309);
        path.lineTo(168,358);
        path.lineTo(178,414);
        path.lineTo(189,457);
        path.lineTo(201,468);
        path.lineTo(210,446);
        path.lineTo(207,386);
        path.lineTo(199,310);
        path.lineTo(188,237);
        path.lineTo(160,229);
        path.lineTo(138,226);
        path.lineTo(125,228);

        path.close();
        canvas.drawPath(path,mRedFillPaint);
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
