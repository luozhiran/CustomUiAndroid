package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.lzr.com.learn_lib.R;

public class PathMeasureView extends View {
    private String TAG = "PathMeasureView.java";

    private Paint mGrayPaint;
    private int mHeight,mWidth ;
    private int mCenterHeight,mCenterWidth;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private float mCurrentPositin;
    private float[] pos,tan;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mGrayPaint = new Paint();
        mGrayPaint.setColor(Color.GRAY);
        mGrayPaint.setStyle(Paint.Style.STROKE);
        mGrayPaint.setAntiAlias(true);
        mGrayPaint.setStrokeWidth(2);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arror,options);
        mMatrix = new Matrix();
        pos = new float[2];
        tan = new float[2];
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
        mCenterHeight = h/2;
        mCenterWidth = w/2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterWidth,mCenterHeight);
        Path path = new Path();
        path.addCircle(0,0,200,Path.Direction.CW);
        canvas.drawPath(path,mGrayPaint);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path,false);
        float distance = pathMeasure.getLength();
        if (mCurrentPositin<1){
            mCurrentPositin = mCurrentPositin+0.005f;
        }else {
            mCurrentPositin = 0;
        }
        pathMeasure.getPosTan(distance*mCurrentPositin,pos,tan);
        float desgree = (float) (Math.atan2(tan[1],tan[0])*180/Math.PI);
        mMatrix.reset();
        mMatrix.postRotate(desgree,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        mMatrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight()/2);
        canvas.drawBitmap(mBitmap,mMatrix,null);
        invalidate();
    }
}
