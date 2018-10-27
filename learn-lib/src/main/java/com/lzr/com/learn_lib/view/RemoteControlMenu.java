package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;

public class RemoteControlMenu extends View {
    private Paint mPaint;
    Path mUpPath, mDownPath, mLeftPath, mRightPath, mCenterPath, mBigCenterPath;
    Region mUpRegion, mDownRegion, mLeftRegion, mRightRegion, mCenterRegion;
    private int mWidth, mHeight;
    private int mMinWidth;


    public RemoteControlMenu(Context context) {
        this(context, null);
    }

    public RemoteControlMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xFF4E5268);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mUpPath = new Path();
        mDownPath = new Path();
        mLeftPath = new Path();
        mRightPath = new Path();
        mCenterPath = new Path();

        mUpRegion = new Region();
        mDownRegion = new Region();
        mLeftRegion = new Region();
        mRightRegion = new Region();
        mCenterRegion = new Region();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Region globalRegion = new Region(-w, -h, w, h);
        mMinWidth = Math.min(w, h);
        int br = mMinWidth / 3;
        RectF bigCircle = new RectF(-br, -br, br, br);
        int sr = mMinWidth / 5;
        RectF smallCircle = new RectF(-sr, -sr, sr, sr);

        mCenterPath.addCircle(0,0,mMinWidth*0.14f, Path.Direction.CW);
        mCenterRegion.setPath(mCenterPath,globalRegion);

        float bigSweepAngle = 80;
        float smallSweepAngle = -84;
        mRightPath.addArc(bigCircle,-40,bigSweepAngle);
        mRightPath.arcTo(smallCircle,40,smallSweepAngle);
        mRightPath.close();
        mRightRegion.setPath(mRightPath,globalRegion);

        mDownPath.addArc(bigCircle,50,bigSweepAngle);
        mDownPath.arcTo(smallCircle,130,smallSweepAngle);
        mDownPath.close();
        mDownRegion.setPath(mDownPath,mDownRegion);

        mLeftPath.addArc(bigCircle,140,bigSweepAngle);
        mLeftPath.arcTo(smallCircle,220,smallSweepAngle);
        mLeftPath.close();
        mLeftRegion.setPath(mLeftPath,mDownRegion);

        mUpPath.addArc(bigCircle,230,bigSweepAngle);
        mUpPath.arcTo(smallCircle,310,smallSweepAngle);
        mUpPath.close();
        mUpRegion.setPath(mUpPath,mDownRegion);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawPath(mCenterPath,mPaint);
        canvas.drawPath(mRightPath,mPaint);
        canvas.drawPath(mDownPath,mPaint);
        canvas.drawPath(mLeftPath,mPaint);
        canvas.drawPath(mUpPath,mPaint);
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
