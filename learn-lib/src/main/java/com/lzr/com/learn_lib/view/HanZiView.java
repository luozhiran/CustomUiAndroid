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
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.lzr.com.learn_lib.data.HorizontalWordRegion;
import com.lzr.com.learn_lib.data.RawInfo;
import com.lzr.com.learn_lib.data.VerticalWordRegion;
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
    private List<Point> mChineseCoordinate;
    private List<WordInfo> mWordInfoList;
    private int mScreenHeight;
    private int mToolBarHeight=0;
    private List<WordInfo> mHanziWordInfoList;
    private List<VerticalWordRegion> mColYRange;
    private SparseArray<List<HorizontalWordRegion>> mLineXRange;

    private int mHanziSpace = 10;
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
        mColYRange = new ArrayList<>();//行数据
        mLineXRange = new SparseArray<>();//列数据
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
        int line = 1;
        int index = 0;
        List<HorizontalWordRegion> list = mLineXRange.get(line);
        int count = list.size();
        HorizontalWordRegion horizontalWordRegion = null;
        for (int i=0;i<mChineseCoordinate.size();i++){
            if (index < count) {
                horizontalWordRegion = list.get(index);
                horizontalWordRegion.word = mChineseList.get(i);
                index++;
                if (index == count) {
                    line++;
                    index = 0;
                    list = mLineXRange.get(line);
                    if (list!=null) {
                        count = list.size();
                    }
                }
            }
            canvas.drawText(mChineseList.get(i), mChineseCoordinate.get(i).x, mChineseCoordinate.get(i).y, mPaint);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int lineNum = getColNum(event.getY());
                if (lineNum != -1) {
                    HorizontalWordRegion region = getLineNum(lineNum, event.getX());
                    if (region != null) {
                        Toast.makeText(getContext(), lineNum + "行" + region.lineNum + "列"+" "+region.word, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "" + lineNum, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(event);
    }

    public HorizontalWordRegion getLineNum(int colNum, float x) {
        List<HorizontalWordRegion> list = mLineXRange.get(colNum);
        for (HorizontalWordRegion hr : list) {
            if (hr.leftRange <= x && x <= hr.rightRange) {
                return hr;
            }
        }
        return null;
    }
    public int getColNum(float y) {
        for (VerticalWordRegion re : mColYRange) {
            if (re.topRange <= y && y <= re.bottomRange) {
                return re.colNum;
            }
        }
        return -1;
    }

    public void setText(String text) {
        mChineseList = getStringArray(text);
    }

    private float generatingCoordinate() {
        //判断拼音和汉子是否相等
        int count = mHanziWordInfoList.size();
        Point hanziPoint = null;
        float xHanziCoordinate = 0;
        float yHanziCoordinate = 0;
        mColYRange.clear();
        mLineXRange.clear();
        mChineseCoordinate.clear();
        List<HorizontalWordRegion> horizontalWordRegionList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            hanziPoint = new Point();
            if (i == 0) {
                HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                VerticalWordRegion rangeRegion = new VerticalWordRegion();
                rangeRegion.topRange = getPaddingTop();
                //汉子坐标
                xHanziCoordinate = getPaddingLeft();
                yHanziCoordinate = getPaddingTop() + mHanziWordInfoList.get(i).baseLine;
                hanziPoint.x = (int) xHanziCoordinate;
                hanziPoint.y = (int) yHanziCoordinate;
                xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width + mHanziSpace;

                //记录列坐标
                horizontalWordRegion.leftRange = hanziPoint.x;
                horizontalWordRegion.rightRange = xHanziCoordinate;
                horizontalWordRegion.lineNum = 1;
                horizontalWordRegionList.add(horizontalWordRegion);
                mLineXRange.put(1, horizontalWordRegionList);
                // 记录行坐标
                rangeRegion.bottomRange = rangeRegion.topRange +mHanziWordInfoList.get(i).height;
                rangeRegion.colNum = 1;
                mColYRange.add(rangeRegion);
            } else {
                if (xHanziCoordinate +mHanziWordInfoList.get(i).width> mViewWidth - getPaddingRight() - getPaddingLeft()) {
                    horizontalWordRegionList = new ArrayList<>();//生成第二行
                    HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                    VerticalWordRegion rangeRegion = new VerticalWordRegion();
                    //汉子坐标
                    rangeRegion.topRange = mColYRange.get(mColYRange.size() - 1).bottomRange;
                    yHanziCoordinate = yHanziCoordinate + mHanziWordInfoList.get(i).height;
                    hanziPoint.y = (int) yHanziCoordinate;
                    xHanziCoordinate = getPaddingLeft();
                    hanziPoint.x = (int) xHanziCoordinate;
                    xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width + mHanziSpace;
                    //记录列坐标
                    horizontalWordRegion.leftRange = hanziPoint.x;
                    horizontalWordRegion.rightRange = xHanziCoordinate;
                    horizontalWordRegion.lineNum = 1;
                    horizontalWordRegionList.add(horizontalWordRegion);
                    mLineXRange.put(mLineXRange.size() + 1, horizontalWordRegionList);
                    //记录行坐标
                    rangeRegion.bottomRange = rangeRegion.topRange + mHanziWordInfoList.get(i).height ;
                    rangeRegion.colNum = mColYRange.size() + 1;
                    mColYRange.add(rangeRegion);
                } else {
                    VerticalWordRegion rangeRegion = new VerticalWordRegion();
                    rangeRegion.topRange = yHanziCoordinate;
                    //汉子坐标
                    hanziPoint.x = (int) xHanziCoordinate;
                    hanziPoint.y = (int) yHanziCoordinate;
                    xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width + mHanziSpace ;
                    //记录列坐标
                    HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                    horizontalWordRegion.lineNum = horizontalWordRegionList.size() + 1;
                    horizontalWordRegion.leftRange = hanziPoint.x;
                    horizontalWordRegion.rightRange = xHanziCoordinate;
                    horizontalWordRegionList.add(horizontalWordRegion);
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
