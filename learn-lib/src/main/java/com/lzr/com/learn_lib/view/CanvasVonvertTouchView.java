package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lzr.com.learn_lib.viewutils.CanvasAidUtils;

//画布变换后坐标转换问题
public class CanvasVonvertTouchView extends View {
    private Paint mPaint;
    float down_x = -1;
    float down_y = -1;

    public CanvasVonvertTouchView(Context context) {
        this(context, null);
    }

    public CanvasVonvertTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xFF4E5268);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);

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
        float x = down_x;
        float y = down_y;
        drawTouchCoordinateSpace(canvas);       // 绘制触摸坐标系 灰色
        // ▼注意画布平移
        canvas.translate(getWidth()/2, getHeight()/2);

        drawTranslateCoordinateSpace(canvas);	 // 绘制平移后的坐标系，红色

        if (x == -1 && y == -1) return;          // 如果没有就返回

        Log.e("CanvasVonvertTouchView",x+"  "+y);
        canvas.drawCircle(x-getWidth()/2,y-getHeight()/2,20,mPaint); // 在触摸位置绘制一个小圆
    }
    /**
     *  绘制触摸坐标系，灰色，为了能够显示出坐标系，将坐标系位置稍微偏移了一点
     */
    private void drawTouchCoordinateSpace(Canvas canvas) {
        canvas.save();
        canvas.translate(10,10);
        CanvasAidUtils.set2DAxisLength(1000, 0, 550, 0);
        CanvasAidUtils.setLineColor(Color.GRAY);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
        canvas.restore();
    }

    /**
     * 绘制平移后的坐标系，红色
     */
    private void drawTranslateCoordinateSpace(Canvas canvas) {
        CanvasAidUtils.set2DAxisLength(500, 500, 275, 275);
        CanvasAidUtils.setLineColor(Color.RED);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                down_x = event.getX();
                down_y = event.getY();
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                down_x = down_y = -1;
                invalidate();
                break;
        }
        return true;

    }
}
