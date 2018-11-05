package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lzr.com.learn_lib.data.RawInfo;
import com.lzr.com.learn_lib.data.WordInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HanZiView extends View {

    private Paint mPaint;
    private float mDenity;
    private Paint.FontMetrics mFontMetrics;
    private int mViewHeight, mViewWidth;
    private List<String> mChineseList;
    private List<String> mYinBiaoList;
    private List<Point> mChineseCoordinate;
    private List<WordInfo> mWordInfoList;
    private int mScreenHeight;
    private int mToolBarHeight=0;
    private List<WordInfo> mHanziWordInfoList;
    public HanZiView(Context context) {
        this(context, null);
    }

    public HanZiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDenity = getResources().getDisplayMetrics().density;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(mDenity);
        mPaint.setTextSize(60);
        mFontMetrics = mPaint.getFontMetrics();
        mChineseCoordinate = new ArrayList<>();
        mWordInfoList = new ArrayList<>();
        mHanziWordInfoList = new ArrayList<>();
        if (mToolBarHeight ==0) {
            mToolBarHeight = getStatusBarHeight(getContext());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                mViewWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = widthSize;
                mViewWidth = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
         height = (int) generatingCoordinate();
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                mViewHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                mViewHeight = heightSize;
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        setMeasuredDimension(widthSize, height);
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
        int i = 0;
        for (Point point : mChineseCoordinate) {
            canvas.drawText(mChineseList.get(i), point.x, point.y, mPaint);
            i++;
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

    public void setText(String text) {
        mChineseList = getStringArray(text);
    }

    public void setYinBiao(String yinbiao, String spiteRegular) {
        if (!TextUtils.isEmpty(yinbiao)) {
            String[] splitResult = yinbiao.split(spiteRegular);
            if (splitResult != null && splitResult.length > 0) {
                mYinBiaoList = Arrays.asList(splitResult);
            }
        }
    }



    private float generatingCoordinate() {
        //判断拼音和汉子是否相等
        int count = mHanziWordInfoList.size();
        Point hanziPoint = null;
        float xHanziCoordinate = 0;
        float yHanziCoordinate = 0;
        mChineseCoordinate.clear();
        for (int i = 0; i < count; i++) {
            hanziPoint = new Point();
            if (i == 0) {
                xHanziCoordinate = getPaddingLeft();
                yHanziCoordinate = getPaddingTop() + mHanziWordInfoList.get(i).baseLine;
                hanziPoint.x = (int) xHanziCoordinate;
                hanziPoint.y = (int) yHanziCoordinate;
                xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width ;
            } else {
                if (xHanziCoordinate +mHanziWordInfoList.get(i).width> mViewWidth - getPaddingRight() - getPaddingLeft()) {
                    yHanziCoordinate = yHanziCoordinate + mHanziWordInfoList.get(i).height;
                    hanziPoint.y = (int) yHanziCoordinate;
                    xHanziCoordinate = getPaddingLeft();
                    hanziPoint.x = (int) xHanziCoordinate;
                    xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width ;
                } else {
                    hanziPoint.x = (int) xHanziCoordinate;
                    hanziPoint.y = (int) yHanziCoordinate;
                    xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width ;
                }
            }

            mChineseCoordinate.add(hanziPoint);

        }
        return Math.max(yHanziCoordinate, mScreenHeight-mToolBarHeight);
    }

    /**
     * 测量单个文字信息
     *
     * @param word
     * @return
     */
    private WordInfo getFontMetrics(String word) {
        float width, height;
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        height = metrics.bottom - metrics.top;
        float baseLine = -metrics.top;
        width = mPaint.measureText(word);
        WordInfo info = new WordInfo();
        info.space = mPaint.getFontSpacing();
        info.baseLine = baseLine;
        info.height = height;
        info.width = width;
        return info;
    }

    public RawInfo getChineseTexttypesetting(WordInfo info, List<String> list) {
        RawInfo rawInfo = new RawInfo();
        rawInfo.colNum = (int) ((mViewWidth - getPaddingLeft() - getPaddingRight()) / info.width);
        int va1 = list.size() / rawInfo.colNum;
        int va2 = list.size() % rawInfo.colNum;
        if (va2 == 0) {
            rawInfo.rawNum = va1;
        } else {
            rawInfo.rawNum = va1 + 1;
        }
        return rawInfo;
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

    private List<String> getStringArray(String chinese) {
        if (!TextUtils.isEmpty(chinese)) {
            List<String> mChineseCharList = new ArrayList<>();
            char[] chars = chinese.toCharArray();
            String str;
            for (int i = 0; i < chars.length; i++) {
                str = String.valueOf(chars[i]);
                if (!TextUtils.isEmpty(str)) {
                    mHanziWordInfoList.add(getFontMetrics(str));
                    mChineseCharList.add(str);
                }
            }
            return mChineseCharList;
        }
        return null;
    }
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
