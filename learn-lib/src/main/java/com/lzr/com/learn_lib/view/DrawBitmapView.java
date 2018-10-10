package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lzr.com.learn_lib.R;

public class DrawBitmapView extends BaseHandlerView {
    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mTotalPage = 13;
    private int mCurrentPage = 1;
    private int mAnimState;
    private boolean isCheck;
    private int mAnimDuration = 500;

    public DrawBitmapView(Context context) {
        this(context, null);
    }

    public DrawBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mAnimState = ANIM_NULL;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.Checkmark);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(0xffFF5317);
        mPaint.setAntiAlias(true);

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
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 240, mPaint);
        int slideHeight = mBitmap.getHeight();
        Rect src = new Rect(slideHeight * mCurrentPage, 0, slideHeight * (mCurrentPage + 1), slideHeight);
        Rect dst = new Rect(-200, -200, 200, 200);
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    public void check() {
        if (ANIM_NULL == mAnimState && !isCheck) {
            isCheck = true;
            mCurrentPage = 0;
            mAnimState = ANIM_CHECK;
            mHandler.sendEmptyMessageAtTime(1, mAnimDuration / mTotalPage);
        }
    }

    public void unCheck() {
        if (ANIM_NULL == mAnimState && isCheck) {
            isCheck = false;
            mCurrentPage = mTotalPage - 1;
            mAnimState = ANIM_UNCHECK;
            mHandler.sendEmptyMessageAtTime(1, mAnimDuration / mTotalPage);
        }
    }

    @Override
    public void handleMessage(Message msg) {
        if (mCurrentPage < mTotalPage && mCurrentPage >= 0) {
            if (mAnimState == ANIM_NULL) return;
            invalidate();
            if (mAnimState == ANIM_CHECK) {
                mCurrentPage++;
            } else if (mAnimState == ANIM_UNCHECK) {
                mCurrentPage--;
            }
            mHandler.sendEmptyMessageAtTime(1, mAnimDuration / mTotalPage);
        } else {
            if (isCheck) {
                mCurrentPage = mTotalPage - 1;
            } else {
                mCurrentPage = 0;
            }
            invalidate();
            mAnimState = ANIM_NULL;
        }
    }
}
