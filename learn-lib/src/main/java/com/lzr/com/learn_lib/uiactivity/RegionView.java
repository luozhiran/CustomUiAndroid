package com.lzr.com.learn_lib.uiactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RegionView extends View {

    private Paint mBluePaint;
    private Paint mYellowPaint;

    public RegionView(Context context) {
        this(context, null);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBluePaint = new Paint();
        mBluePaint.setColor(Color.BLUE);
        mBluePaint.setStrokeWidth(10);
        mBluePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBluePaint.setAntiAlias(true);

        mYellowPaint = new Paint();
        mYellowPaint.setColor(Color.YELLOW);
        mYellowPaint.setStrokeWidth(10);
        mYellowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mYellowPaint.setAntiAlias(true);
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
        Region region = new Region(0,0,200,200);
        drawRegion(region, canvas, mYellowPaint);
    }

    private void drawRegion(Region region, Canvas canvas, Paint paint) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
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
