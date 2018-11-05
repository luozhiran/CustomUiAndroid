package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class TextFlickerView extends View {
    private Paint mPaint, mPaint1;
    private int mTextWidth = 0, mTextHeight;
    private LinearGradient mLinearGradient;
    private int mSignleTextWidth;
    private Matrix mMatrix;
    private float mTranslateX = 0;
    private boolean mStopAnim;
    private int mDefaultWidth = 15;
    private int mWidth;
    private int mSpace;
    private int mMaxWidth = 90;
    private int mMaxHeight = 30;
    private int mX1, mX2, mX3;
    private int mRemoveNum = 1;

    public TextFlickerView(Context context) {
        this(context, null);
    }

    public TextFlickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(30);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);

        mPaint1 = new Paint();
        mPaint1.setTextSize(30);
        mPaint1.setColor(Color.BLACK);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setStrokeWidth(2);
        mPaint1.setAntiAlias(true);
        mMatrix = new Matrix();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = mMaxWidth;
                break;
            case MeasureSpec.EXACTLY:
                if (widthSize < mMaxWidth) {
                    width = mMaxWidth;
                } else {
                    width = widthSize;
                }
                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = mMaxHeight;
                break;
            case MeasureSpec.EXACTLY:
                if (heightSize > mMaxHeight) {
                    height = heightSize;
                } else {
                    height = mMaxHeight;
                }
                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mDefaultWidth;
        mSpace = (w - mDefaultWidth * 3) / 4;
        mX1 = mSpace + mWidth / 2;
        mX2 = 2 * mSpace + 3 * mWidth / 2;
        mX3 = 3 * mSpace + mWidth * 2 + mWidth / 2;
//        mLinearGradient = new LinearGradient(0, 0, mWidth, 0, Color.argb(255, 75, 75, 74), Color.argb(255, 142, 142, 142), Shader.TileMode.CLAMP);
        mLinearGradient = new LinearGradient(0, 0, mWidth, 0, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);

        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX1, mWidth / 2, mWidth / 2, mPaint);
        canvas.drawCircle(mX2, mWidth / 2, mWidth / 2, mPaint);
        canvas.drawCircle(mX3, mWidth / 2, mWidth / 2, mPaint);
        if (mRemoveNum == 0) {
            mTranslateX = -(mWidth + mSpace);
            mRemoveNum = 1;
        } else if (mRemoveNum == 1) {
            mTranslateX = mWidth + mSpace;
            mRemoveNum = 2;
        } else if (mRemoveNum == 2) {
            mTranslateX = mRemoveNum * (mSpace + mWidth);
            mRemoveNum = 3;
        } else if (mRemoveNum == 3) {
            mTranslateX = mRemoveNum * (mSpace + mWidth);
            mRemoveNum = 0;
        }
        mMatrix.setTranslate(mTranslateX, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        if (!mStopAnim) {
            postInvalidateDelayed(400);
        } else {
            canvas.drawCircle(mX1, mWidth / 2, mWidth / 2, mPaint1);
            canvas.drawCircle(mX2, mWidth / 2, mWidth / 2, mPaint1);
            canvas.drawCircle(mX3, mWidth / 2, mWidth / 2, mPaint1);
        }

    }

    public void startAnim() {
        if (mStopAnim) {
            mStopAnim = false;
            postInvalidateDelayed(400);
        }

    }

    public void stopAnim() {
        mStopAnim = true;
    }

}
