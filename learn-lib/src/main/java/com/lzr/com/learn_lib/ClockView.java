package com.lzr.com.learn_lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lzr.com.ui_utils_lib.DensityData;
import com.lzr.com.ui_utils_lib.UISizeUtils;

public class ClockView extends View {
    private Paint mWhitePaint;
    private Paint mBlackPaint;
    private Paint mRedPaint;
    private Paint mCenterPaint;
    private Paint mTextPaint;
    private String tag = "ClockView.java";
    private int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;
    private String[] times = {"6", "7", "8", "9", "10", "11", "12", "1", "2", "3", "4", "5"};
    private DensityData mDensityData;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        mDensityData = UISizeUtils.getScreenDensity_ByResources(context);
        Log.i(tag, mDensityData.toString());
    }

    private void init() {
        mWhitePaint = new Paint();
        mWhitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWhitePaint.setColor(Color.WHITE);
        mWhitePaint.setStrokeWidth(8);

        mBlackPaint = new Paint();
        mBlackPaint.setStyle(Paint.Style.STROKE);
        mBlackPaint.setStrokeWidth(8);
        mBlackPaint.setColor(Color.BLACK);

        mRedPaint = new Paint();
        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setStrokeWidth(12);
        mRedPaint.setColor(Color.RED);

        mCenterPaint = new Paint();
        mCenterPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCenterPaint.setStrokeWidth(8);
        mCenterPaint.setColor(Color.BLACK);

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setStrokeWidth(2);
        mTextPaint.setTextSize(30);
        mTextPaint.setAntiAlias(false);

        mTextPaint.setColor(Color.BLACK);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = UISizeUtils.getMeasureMode(widthMeasureSpec);
        int widthSize = UISizeUtils.getMeasureSize(widthMeasureSpec);
        int heightMode = UISizeUtils.getMeasureMode(heightMeasureSpec);
        int heightSize = UISizeUtils.getMeasureSize(heightMeasureSpec);
        Log.i(tag, "宽度:" + widthSize + "  高度：" + heightSize);

        Log.i(tag, "宽测量模式：" + getMeasureMode(widthMode) + " 高测量模式:" + getMeasureMode(heightMode));
        int width = 0;
        int height = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = 200;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            height = 200;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(tag, "确定控件  宽：" + w + " 高：" + h);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        initPadding();
        Log.i(tag, "绘制  宽：" + getWidth() / 2 + " 高：" + getHeight() / 2);
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        canvas.translate(x, y);
        int radus = getWidth() / 2 - 10;
        canvas.drawCircle(0, 0, radus, mBlackPaint);
        canvas.drawCircle(0, 0, radus - 40, mBlackPaint);
        canvas.drawCircle(0, 0, 10, mCenterPaint);

        for (int i = 0; i < 12; i++) {
            canvas.drawLine(0, radus - 60, 0, radus, mRedPaint);
            Rect rect = new Rect();
            mTextPaint.getTextBounds(times[i], 0, times[i].length(), rect);
            canvas.drawText(times[i], -(rect.right - rect.left) / 2, radus - 80, mTextPaint);
            canvas.rotate(30);
        }


    }


    private void initPadding() {
        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingRight = getPaddingRight();
        mPaddingBottom = getPaddingBottom();
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
