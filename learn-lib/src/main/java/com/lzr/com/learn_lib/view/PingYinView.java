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

public class PingYinView extends View {

    private Paint mPaint;
    private float mDenity;
    private Paint.FontMetrics mFontMetrics;
    private int mViewHeight, mViewWidth;
    private List<String> mChineseList;
    private List<String> mYinBiaoList;
    private List<Point> mChineseCoordinate;
    private List<Point> mPinyinCoordinate;
    private List<WordInfo> mWordInfoList;
    private int mScreenHeight;
    private int mToolBarHeight=0;
    private List<WordInfo> mPinyinWordInfoList;
    private int mPinyinSpace = 20;
    public PingYinView(Context context) {
        this(context, null);
    }

    public PingYinView(Context context, @Nullable AttributeSet attrs) {
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
        mPinyinCoordinate = new ArrayList<>();
        mPinyinWordInfoList = new ArrayList<>();
        mWordInfoList = new ArrayList<>();
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
           height= (int) generatingPinYinCoordinate();

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
        for (Point point : mPinyinCoordinate) {
            if (!TextUtils.isEmpty(mYinBiaoList.get(i).trim())) {
                canvas.drawText(mYinBiaoList.get(i), point.x, point.y, mPaint);
            }
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
            mYinBiaoList = new ArrayList<>();
            if (splitResult != null && splitResult.length > 0) {
                for (String s : splitResult) {
                    if (!TextUtils.isEmpty(s)) {
                        mPinyinWordInfoList.add(getFontMetrics(s));
                        mYinBiaoList.add(s);
                    }
                }
            }
        }
    }


    private float generatingPinYinCoordinate() {
        if (mYinBiaoList != null && !mYinBiaoList.isEmpty()) {
            mPinyinCoordinate.clear();
            //计算每个文字坐标
            Point pinyinPoint = null;
            float xPinyinCoordinate = 0;
            float yPinyinCoordinate = 0;
            for (int i=0;i<mYinBiaoList.size();i++){
                pinyinPoint = new Point();
                if (i == 0){
                    xPinyinCoordinate = getPaddingLeft();
                    yPinyinCoordinate = getPaddingTop()+mPinyinWordInfoList.get(i).baseLine;
                    pinyinPoint.x = (int) xPinyinCoordinate;
                    pinyinPoint.y = (int) yPinyinCoordinate;
                    xPinyinCoordinate = xPinyinCoordinate+mPinyinWordInfoList.get(i).width+10;
                }else {

                if (xPinyinCoordinate+mPinyinWordInfoList.get(i).width>mViewWidth - getPaddingRight() - getPaddingLeft()){
                    yPinyinCoordinate = yPinyinCoordinate +mPinyinWordInfoList.get(i).height;
                    pinyinPoint.y = (int) yPinyinCoordinate;
                    xPinyinCoordinate = getPaddingLeft();
                    pinyinPoint.x = (int) xPinyinCoordinate;
                    xPinyinCoordinate = xPinyinCoordinate+mPinyinWordInfoList.get(i).width+10;
                }else {
                    pinyinPoint.x = (int) xPinyinCoordinate;
                    pinyinPoint.y = (int) yPinyinCoordinate;
                    xPinyinCoordinate = xPinyinCoordinate+mPinyinWordInfoList.get(i).width+10;
                }
                }
                mPinyinCoordinate.add(pinyinPoint);
            }


            return Math.max(yPinyinCoordinate, mScreenHeight-mToolBarHeight);
        }
        return mScreenHeight-mToolBarHeight;
    }


    private float generatingCoordinate() {
        if (mChineseList != null && !mChineseList.isEmpty()) {
            mChineseCoordinate.clear();
            WordInfo info = getFontMetrics(mChineseList.get(0));
            Point point = null;
            float xCoordinate = 0;
            float yCoordinate = 0;
            for (int i = 0; i < mChineseList.size(); i++) {
                if (!TextUtils.isEmpty(mChineseList.get(i))) {
                    point = new Point();
                    if (i == 0) {
                        xCoordinate = getPaddingLeft();
                        yCoordinate = getPaddingTop() + info.baseLine;
                        point.x = (int) xCoordinate;
                        point.y = (int) yCoordinate;
                    } else {
                        xCoordinate = xCoordinate + info.width;
                        if (xCoordinate > mViewWidth - getPaddingRight() - getPaddingLeft()) {//文字排版大于view宽度时，对文字进行换行
                            yCoordinate = yCoordinate + info.height;
                            point.y = (int) yCoordinate;
                            xCoordinate = getPaddingLeft();
                            point.x = (int) xCoordinate;
                        } else {
                            point.x = (int) xCoordinate;
                            point.y = (int) yCoordinate;
                        }
                    }
                    mChineseCoordinate.add(point);
                }
            }

            return Math.max(yCoordinate, mScreenHeight-mToolBarHeight);
        }
        return mScreenHeight-mToolBarHeight;
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
            for (int i = 0; i < chars.length; i++) {
                mChineseCharList.add(String.valueOf(chars[i]));
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
