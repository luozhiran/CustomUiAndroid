package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class HorizontalProgressBar extends View {

    private Paint mLinePaint, mProgressPaint, mBackgroundPaint, mTextPaint;
    private int mWidth, mHeight;
    private RectF mRectF, mBackRectF, mTipRectF;
    private int mMaxProgress = 100;
    private int mProgress;
    private float mOffSize;
    private float mDrowProgressWidth;
    private int mMinHeight = 60;//进度条的高 60-45 = 15
    private float[] mRadiusArray = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
    private int mTipWidth = 60, mTipHeight = 30, mSanJiaoHeight = 15;
    private String mProgressValue;

    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mLinePaint = getPaint(Color.RED, 3, Paint.Style.STROKE);
        mProgressPaint = getPaint(Color.RED, 3, Paint.Style.FILL);
        mBackgroundPaint = getPaint(Color.GRAY, 3, Paint.Style.FILL);
        mTextPaint = getPaint(Color.WHITE, 1, Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(20);
        mRectF = new RectF();
        mTipRectF = new RectF();
    }

    public void setRectPaint(int color, int strokeWidth, Paint.Style style) {
        mLinePaint = getPaint(color, 3, Paint.Style.STROKE);

    }

    public void setBackgroundPaint(int color, int strokeWidth, Paint.Style style) {
        mBackgroundPaint = getPaint(Color.GRAY, 3, Paint.Style.FILL);
    }

    public void setProgressPaint(int color, int strokeWidth, Paint.Style style) {
        mProgressPaint = getPaint(color, 3, Paint.Style.FILL);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBackRectF = new RectF();
        if (mDrowProgressWidth == 0) {
            mOffSize = 1f * mWidth / mMaxProgress;
            mDrowProgressWidth = mProgress * mOffSize;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
        setMaxProgress(100);
        setRadius(0, 10, 10, 0);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTip(canvas);
        drawLine(canvas);
        drawProgress(canvas);
    }

    private Paint getPaint(int color, float strokeWidth, Paint.Style style) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        return paint;
    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            width = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.5);
        } else if (mode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            width = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.5);
        }
        mWidth = width - getPaddingLeft() - getPaddingRight();
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else if (mode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            height = height;
        }
        height = Math.max(height, mMinHeight);
        mHeight = height - getPaddingTop() - getPaddingBottom();
        return height;
    }

    private void drawTip(Canvas canvas) {
        if (mDrowProgressWidth >= mTipWidth / 2) {

            if (mDrowProgressWidth < mWidth - mTipWidth / 2) {
                mTipRectF.set(getPaddingLeft() + mDrowProgressWidth - mTipWidth / 2, getPaddingTop(), getPaddingLeft() + mTipWidth + mDrowProgressWidth - mTipWidth / 2, getPaddingTop() + mTipHeight);
                canvas.drawRoundRect(mTipRectF, 2, 2, mProgressPaint);

                Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
                int baseline = (int) ((mTipRectF.bottom + mTipRectF.top - fontMetrics.bottom - fontMetrics.top) / 2);
                canvas.drawText(mProgressValue, mTipRectF.centerX(), baseline, mTextPaint);


                Path path = new Path();
                float tipWidth = mTipWidth / 2;
                path.moveTo((float) (getPaddingLeft() + tipWidth - tipWidth * 0.4 + mDrowProgressWidth - mTipWidth / 2), getPaddingTop() + mTipHeight);
                path.lineTo(getPaddingLeft() + tipWidth + mDrowProgressWidth - mTipWidth / 2, getPaddingTop() + mTipHeight + mSanJiaoHeight);//三角形高8px
                path.lineTo((float) (getPaddingLeft() + tipWidth + tipWidth * 0.4 + mDrowProgressWidth - mTipWidth / 2), getPaddingTop() + mTipHeight);
                canvas.drawPath(path, mProgressPaint);
            } else {
                mTipRectF.set(getPaddingLeft() + mWidth - mTipWidth , getPaddingTop(), getPaddingLeft() + mWidth, getPaddingTop() + mTipHeight);
                canvas.drawRoundRect(mTipRectF, 2, 2, mProgressPaint);

                Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
                int baseline = (int) ((mTipRectF.bottom + mTipRectF.top - fontMetrics.bottom - fontMetrics.top) / 2);
                canvas.drawText(mProgressValue, mTipRectF.centerX(), baseline, mTextPaint);


                Path path = new Path();
                float tipWidth = mTipWidth / 2;
                path.moveTo((float) (getPaddingLeft() + tipWidth - tipWidth * 0.4 + mWidth - mTipWidth), getPaddingTop() + mTipHeight);
                path.lineTo(getPaddingLeft() + mWidth - mTipWidth / 2, getPaddingTop() + mTipHeight + mSanJiaoHeight);//三角形高8px
                path.lineTo((float) (getPaddingLeft() + tipWidth + tipWidth * 0.4 + mWidth - mTipWidth), getPaddingTop() + mTipHeight);
                canvas.drawPath(path, mProgressPaint);
            }

        } else {
            mTipRectF.set(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + mTipWidth, getPaddingTop() + mTipHeight);
            canvas.drawRoundRect(mTipRectF, 2, 2, mProgressPaint);

            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            int baseline = (int) ((mTipRectF.bottom + mTipRectF.top - fontMetrics.bottom - fontMetrics.top) / 2);
            canvas.drawText("0%", mTipRectF.centerX(), baseline, mTextPaint);


            Path path = new Path();
            float tipWidth = mTipWidth / 2;
            path.moveTo((float) (getPaddingLeft() + tipWidth - tipWidth * 0.4), getPaddingTop() + mTipHeight);
            path.lineTo(getPaddingLeft() + tipWidth, getPaddingTop() + mTipHeight + mSanJiaoHeight);//三角形高8px
            path.lineTo((float) (getPaddingLeft() + tipWidth + tipWidth * 0.4), getPaddingTop() + mTipHeight);
            canvas.drawPath(path, mProgressPaint);
        }

    }

    private void drawLine(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getPaddingLeft(), getPaddingTop() + mTipHeight + mSanJiaoHeight);
        path.lineTo(getPaddingLeft() + mWidth, getPaddingTop() + mTipHeight + mSanJiaoHeight);
        path.lineTo(getPaddingLeft() + mWidth, getPaddingTop() + mHeight - 2);
        path.lineTo(getPaddingLeft(), getPaddingTop() + mHeight - 2);
        path.close();
        canvas.drawPath(path, mLinePaint);
    }

    private void drawProgress(Canvas canvas) {
        mBackRectF.set(getPaddingLeft(), getPaddingTop() + mTipHeight + mSanJiaoHeight, getPaddingLeft() + mWidth, getPaddingTop() + mHeight - 2);
        canvas.drawRoundRect(mBackRectF, 0, 0, mBackgroundPaint);

        mRectF.set(getPaddingLeft(), getPaddingTop() + mTipHeight + mSanJiaoHeight, getPaddingLeft() + mDrowProgressWidth, getPaddingTop() + mHeight - 2);
        Path path = new Path();
        if (mDrowProgressWidth == mWidth) {
            path.addRoundRect(mRectF, new float[]{0, 0, 0, 0, 0, 0, 0, 0}, Path.Direction.CW);
        } else {
            path.addRoundRect(mRectF, mRadiusArray, Path.Direction.CW);
        }
        canvas.clipPath(path);
        canvas.drawPath(path, mProgressPaint);

    }

    public void setRadius(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        mRadiusArray[0] = leftTop;
        mRadiusArray[1] = leftTop;
        mRadiusArray[2] = rightTop;
        mRadiusArray[3] = rightTop;
        mRadiusArray[4] = rightBottom;
        mRadiusArray[5] = rightBottom;
        mRadiusArray[6] = leftBottom;
        mRadiusArray[7] = leftBottom;
    }

    public void setMaxProgress(int maxProgress) {
        mMaxProgress = maxProgress;
        mOffSize = 1f * mWidth / mMaxProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        mProgressValue = progress + "%";
        mDrowProgressWidth = mProgress * mOffSize;
        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }
}
