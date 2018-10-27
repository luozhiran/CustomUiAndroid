package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class RegionClickView extends View {
    private Paint mPaint;
    private Path mPath;
    private Region mRegion;

    public RegionClickView(Context context) {
        this(context, null);
    }

    public RegionClickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xFF4E5268);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPath = new Path();
        mRegion = new Region();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath.addCircle(w / 2, h / 2, 200, Path.Direction.CW);
        Region globalRegion = new Region(0, 0, w, h);
        mRegion.setPath(mPath, globalRegion);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int X = (int) event.getX();
            int Y = (int) event.getY();
            if (mRegion.contains(X,Y)){
                Toast.makeText(this.getContext(),"圆被点击",Toast.LENGTH_SHORT).show();
            }

        } else if (action == MotionEvent.ACTION_MOVE) {

        } else if (action == MotionEvent.ACTION_UP) {

        }
        return true;
    }

}
