package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.lzr.com.learn_lib.data.HorizontalWordRegion;
import com.lzr.com.learn_lib.data.RawInfo;
import com.lzr.com.learn_lib.data.VerticalWordRegion;
import com.lzr.com.learn_lib.data.WordInfo;

import java.util.ArrayList;
import java.util.List;

public class CustomPinYinAndHanziView extends View {

    private Paint mPaint;
    private float mDenity;
    private Paint.FontMetrics mFontMetrics;
    private int mViewHeight, mViewWidth;
    private List<String> mChineseList;
    private List<String> mYinBiaoList;
    private List<Point> mChineseCoordinate;
    private List<Point> mPinyinCoordinate;
    private List<WordInfo> mHanziWordInfoList;
    private List<WordInfo> mPinyinWordInfoList;
    private List<VerticalWordRegion> mColYRange;
    private SparseArray<List<HorizontalWordRegion>> mLineXRange;
    private int mScreenHeight;
    private int mToolBarHeight = 0;
    private int mHanziSpace = 10;
    private int mPinyinSpace = 20;


    public CustomPinYinAndHanziView(Context context) {
        this(context, null);
    }

    public CustomPinYinAndHanziView(Context context, @Nullable AttributeSet attrs) {
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
        mHanziWordInfoList = new ArrayList<>();
        mPinyinWordInfoList = new ArrayList<>();
        mLineXRange = new SparseArray<>();//列数据
        mColYRange = new ArrayList<>();//行数据
        if (mToolBarHeight == 0) {
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
//         height = (int) generatingCoordinate();
//        height = (int) generatingPinYinCoordinate();
        height = (int) generatingPingYinAndHanziCoordinate();
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
        HorizontalWordRegion horizontalWordRegion;
        for (int i = 0; i < mChineseCoordinate.size(); i++) {
            //先画第一行
            if (index < count) {
                horizontalWordRegion = list.get(index);
                horizontalWordRegion.word = mChineseList.get(i);
                horizontalWordRegion.pinyin = mYinBiaoList.get(i);
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
            canvas.drawText(mYinBiaoList.get(i), mPinyinCoordinate.get(i).x, mPinyinCoordinate.get(i).y, mPaint);
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


    private float generatingPingYinAndHanziCoordinate() {
        //判断拼音和汉子是否相等
        if (mYinBiaoList == null || mChineseList == null)
            return mScreenHeight - mToolBarHeight;//拼音和汉子列表是否为null
        if (mYinBiaoList.size() != mChineseList.size())
            return mScreenHeight - mToolBarHeight;//拼音和汉子个数相等
        if (mHanziWordInfoList.size() != mPinyinWordInfoList.size())
            return mScreenHeight - mToolBarHeight;//拼音和汉子的文字信息是否相等
        int count = mHanziWordInfoList.size();
        Point pinyinPoint = null;
        Point hanziPoint = null;
        float xPinyinCoordinate = 0;
        float yPinyinCoordinate = 0;
        float xHanziCoordinate = 0;
        float yHanziCoordinate = 0;
        mChineseCoordinate.clear();
        mPinyinCoordinate.clear();
        mColYRange.clear();
        mLineXRange.clear();
        List<HorizontalWordRegion> horizontalWordRegionList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            pinyinPoint = new Point();
            hanziPoint = new Point();
            if (i == 0) {
                if (mHanziWordInfoList.get(i).width >= mPinyinWordInfoList.get(i).width) {
                    VerticalWordRegion rangeRegion = new VerticalWordRegion();
                    HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                    rangeRegion.topRange = getPaddingTop();
                    //拼音坐标
                    xPinyinCoordinate = getPaddingLeft();
                    yPinyinCoordinate = getPaddingTop() + mPinyinWordInfoList.get(i).baseLine;
                    float diff = (mHanziWordInfoList.get(i).width - mPinyinWordInfoList.get(i).width) / 2;
                    pinyinPoint.x = (int) (xPinyinCoordinate + diff);
                    pinyinPoint.y = (int) yPinyinCoordinate;
                    xPinyinCoordinate = xPinyinCoordinate + 2 * diff + mPinyinWordInfoList.get(i).width + mHanziSpace;
                    //汉子坐标
                    xHanziCoordinate = getPaddingLeft();
                    yHanziCoordinate = getPaddingTop() + mHanziWordInfoList.get(i).baseLine + pinyinPoint.y;
                    hanziPoint.x = (int) (xHanziCoordinate);
                    hanziPoint.y = (int) yHanziCoordinate;
                    xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width + mHanziSpace;
                    //记录列坐标
                    horizontalWordRegion.leftRange = hanziPoint.x;
                    horizontalWordRegion.rightRange = xHanziCoordinate;
                    horizontalWordRegion.lineNum = 1;
                    horizontalWordRegionList.add(horizontalWordRegion);
                    mLineXRange.put(1, horizontalWordRegionList);
                    // 记录行坐标
                    rangeRegion.bottomRange = rangeRegion.topRange + mPinyinWordInfoList.get(i).height + mHanziWordInfoList.get(i).height;
                    rangeRegion.colNum = 1;
                    mColYRange.add(rangeRegion);

                } else {
                    VerticalWordRegion rangeRegion = new VerticalWordRegion();
                    HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();

                    //拼音坐标
                    xPinyinCoordinate = getPaddingLeft();
                    rangeRegion.topRange = getPaddingTop();
                    yPinyinCoordinate = getPaddingTop() + mPinyinWordInfoList.get(i).baseLine;
                    pinyinPoint.x = (int) xPinyinCoordinate;
                    pinyinPoint.y = (int) yPinyinCoordinate;
                    xPinyinCoordinate = xPinyinCoordinate + mPinyinWordInfoList.get(i).width + mPinyinSpace;
                    //汉子坐标
                    float diff = (mPinyinWordInfoList.get(i).width - mHanziWordInfoList.get(i).width) / 2;
                    xHanziCoordinate = getPaddingLeft();
                    yHanziCoordinate = getPaddingTop() + mHanziWordInfoList.get(i).baseLine + pinyinPoint.y;
                    hanziPoint.x = (int) (xHanziCoordinate + diff);
                    hanziPoint.y = (int) yHanziCoordinate;
                    xHanziCoordinate = xHanziCoordinate + 2 * diff + mHanziWordInfoList.get(i).width + mPinyinSpace;
                    //记录列坐标
                    horizontalWordRegion.leftRange = pinyinPoint.x;
                    horizontalWordRegion.rightRange = xPinyinCoordinate;
                    horizontalWordRegion.lineNum = 1;
                    horizontalWordRegionList.add(horizontalWordRegion);
                    mLineXRange.put(1, horizontalWordRegionList);
                    //记录行坐标
                    rangeRegion.bottomRange = rangeRegion.topRange + mPinyinWordInfoList.get(i).height + mHanziWordInfoList.get(i).height;
                    rangeRegion.colNum = 1;
                    mColYRange.add(rangeRegion);
                }

            } else {
                float temp = Math.min(xPinyinCoordinate, xHanziCoordinate);//最大值
                float wordMaxWidth = Math.max(mHanziWordInfoList.get(i).width, mPinyinWordInfoList.get(i).width);
                if (temp + wordMaxWidth > mViewWidth - getPaddingRight() - getPaddingLeft()) {
                    horizontalWordRegionList = new ArrayList<>();//生成第二行
                    if (mHanziWordInfoList.get(i).width >= mPinyinWordInfoList.get(i).width) {
                        HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                        VerticalWordRegion rangeRegion = new VerticalWordRegion();
                        rangeRegion.topRange = mColYRange.get(mColYRange.size() - 1).bottomRange;
                        //拼音坐标
                        xPinyinCoordinate = getPaddingLeft();
                        yPinyinCoordinate = yHanziCoordinate + mPinyinWordInfoList.get(i).baseLine;
                        float diff = (mHanziWordInfoList.get(i).width - mPinyinWordInfoList.get(i).width) / 2;
                        pinyinPoint.x = (int) (xPinyinCoordinate + diff);
                        pinyinPoint.y = (int) yPinyinCoordinate;
                        xPinyinCoordinate = xPinyinCoordinate + 2 * diff + mPinyinWordInfoList.get(i).width + mHanziSpace;
                        //汉子坐标
                        xHanziCoordinate = getPaddingLeft();
                        yHanziCoordinate = yPinyinCoordinate + mHanziWordInfoList.get(i).baseLine;
                        hanziPoint.x = (int) (xHanziCoordinate);
                        hanziPoint.y = (int) yHanziCoordinate;
                        xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width + mHanziSpace;
                        //记录列坐标
                        horizontalWordRegion.leftRange = hanziPoint.x;
                        horizontalWordRegion.rightRange = xHanziCoordinate;
                        horizontalWordRegion.lineNum = 1;
                        horizontalWordRegionList.add(horizontalWordRegion);
                        mLineXRange.put(mLineXRange.size() + 1, horizontalWordRegionList);
                        //记录行坐标
                        rangeRegion.bottomRange = rangeRegion.topRange + mPinyinWordInfoList.get(i).height + mHanziWordInfoList.get(i).height;
                        rangeRegion.colNum = mColYRange.size() + 1;
                        mColYRange.add(rangeRegion);
                    } else {
                        VerticalWordRegion rangeRegion = new VerticalWordRegion();
                        HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                        rangeRegion.topRange = yHanziCoordinate;
                        //拼音坐标
                        yPinyinCoordinate = yHanziCoordinate + mPinyinWordInfoList.get(i).baseLine;
                        pinyinPoint.y = (int) yPinyinCoordinate;
                        xPinyinCoordinate = getPaddingLeft();
                        pinyinPoint.x = (int) xPinyinCoordinate;
                        xPinyinCoordinate = xPinyinCoordinate + mPinyinWordInfoList.get(i).width + mPinyinSpace;

                        //汉子坐标
                        yHanziCoordinate = yPinyinCoordinate + mHanziWordInfoList.get(i).baseLine;
                        hanziPoint.y = (int) yHanziCoordinate;
                        float diff = (mPinyinWordInfoList.get(i).width - mHanziWordInfoList.get(i).width) / 2;
                        xHanziCoordinate = getPaddingLeft();
                        hanziPoint.x = (int) (xHanziCoordinate + diff);
                        xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width + mPinyinSpace + 2 * diff;
                        //记录列坐标
                        horizontalWordRegion.leftRange = pinyinPoint.x;
                        horizontalWordRegion.rightRange = xPinyinCoordinate;
                        horizontalWordRegion.lineNum = 1;
                        horizontalWordRegionList.add(horizontalWordRegion);
                        mLineXRange.put(mLineXRange.size() + 1, horizontalWordRegionList);
                        //记录行坐标
                        rangeRegion.bottomRange = rangeRegion.topRange + mPinyinWordInfoList.get(i).height + mHanziWordInfoList.get(i).height;
                        rangeRegion.colNum = mColYRange.size() + 1;
                        mColYRange.add(rangeRegion);
                    }
                } else {
                    if (mHanziWordInfoList.get(i).width > mPinyinWordInfoList.get(i).width) {
                        //拼音坐标
                        float diff = (mHanziWordInfoList.get(i).width - mPinyinWordInfoList.get(i).width) / 2;
                        pinyinPoint.x = (int) (xPinyinCoordinate + diff);
                        pinyinPoint.y = (int) yPinyinCoordinate;
                        xPinyinCoordinate = xPinyinCoordinate + 2 * diff + mPinyinWordInfoList.get(i).width + mHanziSpace;
                        //汉子坐标
                        hanziPoint.x = (int) (xHanziCoordinate);
                        hanziPoint.y = (int) yHanziCoordinate;
                        xHanziCoordinate = xHanziCoordinate + mHanziWordInfoList.get(i).width + mHanziSpace;

                        //记录列坐标
                        HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                        horizontalWordRegion.lineNum = horizontalWordRegionList.size() + 1;
                        horizontalWordRegion.leftRange = hanziPoint.x;
                        horizontalWordRegion.rightRange = xHanziCoordinate;
                        horizontalWordRegionList.add(horizontalWordRegion);

                    } else {
                        //拼音坐标
                        pinyinPoint.x = (int) xPinyinCoordinate;
                        pinyinPoint.y = (int) yPinyinCoordinate;
                        xPinyinCoordinate = xPinyinCoordinate + mPinyinWordInfoList.get(i).width + mPinyinSpace;

                        //汉子坐标
                        float diff = (mPinyinWordInfoList.get(i).width - mHanziWordInfoList.get(i).width) / 2;
                        hanziPoint.x = (int) (xHanziCoordinate + diff);
                        hanziPoint.y = (int) yHanziCoordinate;
                        xHanziCoordinate = xHanziCoordinate + 2 * diff + mHanziWordInfoList.get(i).width + mPinyinSpace;

                        //记录列坐标
                        HorizontalWordRegion horizontalWordRegion = new HorizontalWordRegion();
                        horizontalWordRegion.lineNum = horizontalWordRegionList.size() + 1;
                        horizontalWordRegion.leftRange = pinyinPoint.x;
                        horizontalWordRegion.rightRange = xPinyinCoordinate;
                        horizontalWordRegionList.add(horizontalWordRegion);
                    }

                }
            }

            mChineseCoordinate.add(hanziPoint);
            mPinyinCoordinate.add(pinyinPoint);
        }
        return Math.max(yHanziCoordinate, mScreenHeight - mToolBarHeight);
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
        info.descent = metrics.descent;
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
