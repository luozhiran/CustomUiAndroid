package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lzr.com.learn_lib.viewutils.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class TouchLineView extends View {
    private Paint mRedPaint;
    private Path mPath;
    private float mTouchX,mTouchY;
    //触摸画布背景
    private Bitmap mTouchBitmap;
    private Canvas mTouchCanvas;

    public TouchLineView(Context context) {
        this(context,null);
    }

    public TouchLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mRedPaint = new Paint();
        mRedPaint.setColor(Color.RED);
        mRedPaint.setStrokeJoin(Paint.Join.ROUND);
        mRedPaint.setStrokeCap(Paint.Cap.ROUND);
        mRedPaint.setAntiAlias(true);
        mRedPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRedPaint.setStrokeWidth(10);
        mPath = new Path();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    private List<List<Point>> mFrameData = new ArrayList<>();
    private List<List<Point>> mFillData = new ArrayList<>();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mTouchBitmap == null){
            mTouchBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
            mTouchCanvas = new Canvas(mTouchBitmap);
            mTouchCanvas.drawColor(0, PorterDuff.Mode.CLEAR);

            //JSON 数据解析
            String assetsName = null;
            try {
                assetsName = WordUtils.urlEncode('小');
                String jsonData = WordUtils.readJsonFromAssets(getContext(), assetsName);
                if (TextUtils.isEmpty(jsonData)) {
                    setVisibility(View.GONE);
                    throw new NullPointerException();
                }
                mFrameData = WordUtils.extractFrame(jsonData);
                mFillData = WordUtils.extractFill(jsonData);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Point> points = mFillData.get(0);
        for (int j = 0; j < points.size() - 1-100; j++) {
//            if (j%3==0) {
                Point p = points.get(j);
                p = scalePoint(p);
                Point nextP = points.get(j + 1);
                nextP = scalePoint(nextP);
                mTouchCanvas.drawLine(p.x, p.y, nextP.x, nextP.y, mRedPaint);
//            }
        }
        canvas.drawBitmap(mTouchBitmap, 0, 0, mRedPaint);
    }

    /**
     * 缩放原数据坐标到布局大小
     */
    private Point scalePoint(Point point) {
        Point p = new Point();
        //760 是原数据默认宽高
        p.x = (int) (point.x / (760f / getWidth()));
        p.y = (int) (point.y / (760f / getHeight()));
        return p;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        mTouchX = event.getX();
        mTouchY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                mTouchCanvas.drawPath(mPath,mRedPaint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
