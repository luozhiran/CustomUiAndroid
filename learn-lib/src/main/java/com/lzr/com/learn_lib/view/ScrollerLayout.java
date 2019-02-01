package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ScrollerLayout extends ViewGroup {
    private int mLeftLimit, mRightLimit;
    private Scroller mScroller;
    private int mTouchSlop;
    private int mTouchX, mTouchY;

    public ScrollerLayout(Context context) {
        this(context, null);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("onMeasure", getMeasureMode(widthMeasureSpec) + " " + getMeasureMode(heightMeasureSpec));
        int childNum = getChildCount();
        for (int i = 0; i < childNum; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childNum = getChildCount();
        View view = null;
        for (int i = 0; i < childNum; i++) {
            view = getChildAt(i);
            view.layout(i * view.getMeasuredWidth(), 0, (i + 1) * view.getMeasuredWidth(), view.getMeasuredHeight());
            mRightLimit = +view.getWidth();
        }
        mLeftLimit = getChildAt(0).getLeft();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = (int) ev.getX();
                mTouchY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getX() - mTouchX) > mTouchY) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    int lastMoveX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = (int) event.getX();
                mTouchY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int scrollX = (int) (lastMoveX - event.getX());
                if (scrollX + getScrollX() <= mLeftLimit) {
                    scrollTo(mLeftLimit, 0);
                    return true;
                } else if (scrollX + getScrollX() >= mRightLimit) {
                    scrollTo(mRightLimit, 0);
                    return true;
                } else {
                    scrollTo(scrollX, 0);
                }
                lastMoveX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    public String getMeasureMode(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (MeasureSpec.UNSPECIFIED == mode) {
            return "unspecified";
        } else if (MeasureSpec.AT_MOST == mode) {
            return "AT_MOST";
        } else if (MeasureSpec.EXACTLY == mode) {
            return "EXACTILY";
        }
        return "";
    }

}
