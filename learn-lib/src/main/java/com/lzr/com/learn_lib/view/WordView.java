package com.lzr.com.learn_lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lzr.com.learn_lib.viewutils.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class WordView extends View {

    private Paint mGridPaint;
    private Paint mGridDashPaint;
    private Paint mOutlinePaint;
    private Paint mFillPaint;
    private Paint mCenterLinePaint;
    private Paint mTouchPaint;
    private Path mTouchPath;

    //触摸画布背景
    private Bitmap touchBitmap;
    //触摸画布
    private Canvas touchCanvas;
    private int mWidth, mHeight;
    private List<List<Point>> mFrameSet;//轮廓点数据
    private List<List<Point>> mFillData = new ArrayList<>();
    //不规则范围
    private List<Region> mRegionList = new ArrayList<>();
    //边框轮廓
    private List<Path> mFramePaths = new ArrayList<>();
    //触摸点
    private float touchX, touchY;

    public WordView(Context context) {
        this(context, null);
    }

    public WordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

        mOutlinePaint = new Paint();
        mOutlinePaint.setColor(Color.DKGRAY);
        mOutlinePaint.setStrokeJoin(Paint.Join.ROUND);
        mOutlinePaint.setStrokeCap(Paint.Cap.ROUND);
        mOutlinePaint.setAntiAlias(true);
        //Paint.Style.FILL_AND_STROKE 为实心
        mOutlinePaint.setStyle(Paint.Style.STROKE);
        mOutlinePaint.setStrokeWidth(2);

        mFillPaint = new Paint();
        mFillPaint.setColor(Color.RED);
        mFillPaint.setStrokeJoin(Paint.Join.ROUND);
        mFillPaint.setStrokeCap(Paint.Cap.ROUND);
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mFillPaint.setStrokeWidth(5);

        mCenterLinePaint = new Paint();
        mCenterLinePaint.setColor(Color.BLUE);
        mCenterLinePaint.setAntiAlias(true);
        mCenterLinePaint.setStyle(Paint.Style.STROKE);
        mCenterLinePaint.setStrokeWidth(1);

        mGridPaint = new Paint();
        mGridPaint.setColor(Color.GRAY);
        mGridPaint.setAntiAlias(true);
        mGridPaint.setStyle(Paint.Style.STROKE);
        mGridPaint.setStrokeWidth(4);


        mTouchPaint = new Paint();
        mTouchPaint.setColor(Color.BLUE);
        mTouchPaint.setAntiAlias(true);
        mTouchPaint.setStyle(Paint.Style.FILL);


        mGridDashPaint = new Paint();
        mGridDashPaint.setColor(Color.GRAY);
        mGridDashPaint.setAntiAlias(true);
        mGridDashPaint.setStyle(Paint.Style.STROKE);
        mGridDashPaint.setStrokeWidth(4);
        mGridDashPaint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        readWordData();

        mTouchPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (touchBitmap == null) {
            touchBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            touchCanvas = new Canvas(touchBitmap);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mHeight = Math.min(w, h);
//        mWidth = w;
//        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
        drawWord(canvas);
        drawCenterLine(canvas);
        canvas.drawBitmap(touchBitmap, 0, 0, mTouchPaint);
        RectF bounds = new RectF();
        for (int i=0;i<mFrameSet.size();i++){
            bounds.setEmpty();
            mFramePaths.get(i).computeBounds(bounds,true);
            Region region = new Region();
            region.setPath(mFramePaths.get(i),new Region((int)bounds.left,(int)bounds.top,(int)bounds.right,(int)bounds.bottom));
            mRegionList.add(region);
        }
    }

    private void drawGrid(Canvas canvas) {
        Rect rect = new Rect(getPaddingLeft(), getPaddingTop(), mWidth - getPaddingRight(), mHeight - getPaddingBottom());
        canvas.drawRect(rect, mGridPaint);
        int viewHeight = mHeight - getPaddingBottom() - getPaddingTop();
        int viewWidth = mWidth - getPaddingLeft() - getPaddingRight();

        canvas.drawLine(viewWidth / 2 + getPaddingLeft(), getPaddingTop(), viewWidth / 2 + getPaddingLeft(), mHeight - getPaddingBottom(), mGridPaint);
        canvas.drawLine(getPaddingLeft(), viewHeight / 2 + getPaddingTop(), mWidth - getPaddingRight(), viewHeight / 2 + getPaddingTop(), mGridPaint);

        canvas.drawLine(getPaddingLeft(), getPaddingTop(), mWidth - getPaddingRight(), mHeight - getPaddingBottom(), mGridDashPaint);
        canvas.drawLine(mWidth - getPaddingRight(), getPaddingTop(), getPaddingLeft(), mHeight - getPaddingBottom(), mGridDashPaint);
    }

    /**
     * 画轮廓
     */
    private void drawWord(Canvas canvas) {
        for (int i = 0; i < mFrameSet.size(); i++) {
            Path path = new Path();
            List<Point> points = mFrameSet.get(i);
            for (int j = 0; j < points.size(); j++) {
                Point point = points.get(j);
                point = scalePoint(point);
                if (j == 0) {
                    path.moveTo(point.x, point.y);
                } else {
                    path.lineTo(point.x, point.y);
                }
            }
            path.close();
            mFramePaths.add(path);  //添加缓存
            canvas.drawPath(path, mOutlinePaint);
        }

    }

    /**
     * 画提示线
     */
    private void drawCenterLine(Canvas canvas) {
        canvas.drawPath(getStrokeCenterLine(0), mCenterLinePaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        touchX = event.getX();
        touchY = event.getY();

        Log.e("ontouch", "how touch " + event.getAction() + " ,x =" + event.getX() + ",y= " + event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchPath.reset();
                mTouchPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mTouchPath.lineTo(x,y);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * 返回指定笔画的中线
     *
     * @param stroke 笔画
     * @return 路径
     */
    private Path getStrokeCenterLine(int stroke) {
        Path path = new Path();
        if (mFillData.size() != 0) {
            List<Point> points = mFillData.get(stroke);
            Point lastPoint = new Point();
            for (int j = 0; j < points.size(); j++) {
                if (j == 0) {
                    Point p = points.get(j);
                    p = scalePoint(p);
                    Point nextP = points.get(j + 1);
                    nextP = scalePoint(nextP);
                    Rect rect = new Rect(p.x, p.y, nextP.x, nextP.y);
                    path.moveTo(rect.centerX(), rect.centerY());
                    lastPoint.set(rect.centerX(), rect.centerY());
                } else {
                    if (j % 12 == 0) {
                        Point p = points.get(j);
                        p = scalePoint(p);
                        Point nextP = points.get(j + 1);
                        nextP = scalePoint(nextP);
                        Rect rect = new Rect(p.x, p.y, nextP.x, nextP.y);
//                       path.quadTo(lastPoint.x,lastPoint.y,rect.centerX(),rect.centerY());
                        path.lineTo(lastPoint.x, lastPoint.y);
                        path.lineTo(rect.centerX(), rect.centerY());
                        lastPoint.set(rect.centerX(), rect.centerY());
                    }
                }
            }
        }
        return path;
    }


    /**
     * 缩放原数据坐标到布局大小
     */
    private Point scalePoint(Point point) {
        Point p = new Point();
        //760 是原数据默认宽高
        p.x = (int) (point.x / (760f / mWidth));
        p.y = (int) (point.y / (760f / mHeight));
        return p;
    }

    /**
     * 判断当前path 在哪一个笔画中
     *
     * @param p 路径
     * @return 笔画数
     */
    private int isInsideStroke(Path p){
        PathMeasure measure = new PathMeasure(p,true);
        return 0;
    }

    private void readWordData() {
        try {
            String assetsName = WordUtils.urlEncode('小');
            String jsonData = WordUtils.readJsonFromAssets(getContext(), assetsName);
            if (TextUtils.isEmpty(jsonData)) {
                setVisibility(View.GONE);
                throw new NullPointerException();
            }
            mFrameSet = WordUtils.extractFrame(jsonData);
            mFillData = WordUtils.extractFill(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
