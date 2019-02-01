package com.lzr.com.learn_lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class WavView extends View{
    //画笔
    private Paint mPaint;
    private Paint circlrPaint;

    /**
     * 声音音柱高度
     */
    private float[] voiceLength = new float[]{0.8f, 1.3f, 0.9f, 1.6f, 1, 2, 1};

    /**
     * 颜色值
     */
    private int[] colorS = new int[]{0xff2EBEB0, 0xff28B3DB, 0xff25A1F0, 0xff2695F0,
            0xff25A1F0, 0xff26ADF0, 0xff2EBEB0};
    private int rect_width = 6;  //矩形的宽度
    private int rect_distance;  //矩形之间的间距
    private float radius;  //圆角
    private float widthRect;  //矩形占据总宽度7*8
    private float distanceRect; //间隔间距的总尺寸6*12
    private float drawWidth;  //绘制的区域宽度尺寸 宽+间距，抖动部分总长度
    private int soundNum = 1;
    private int rect_height_defaule;
    private int textSize = 18;
    private String text_desc = "请讲话";
    private boolean isRun = true;
    private MyThread myThread;
    private int current_point = 0;
    private boolean isTranslate = false;

    public WavView(Context context) {
        this(context, null);
    }

    public WavView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WavView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化画笔，计算绘制尺寸
     */
    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setDither(true);// 设置抖动,颜色过渡更均匀

        circlrPaint = new Paint();
        circlrPaint.setAntiAlias(true);
        circlrPaint.setStrokeCap(Paint.Cap.ROUND);
        circlrPaint.setDither(true);// 设置抖动,颜色过渡更均匀

        rect_distance = rect_width * 2;  //矩形之间的间距
        radius = rect_width / 2;  //圆角banjing
        widthRect = (voiceLength.length) * rect_width;  //矩形占据总宽度7*8
        distanceRect = (voiceLength.length - 1) * rect_distance; //间隔间距的总尺寸6*12
        drawWidth = widthRect + distanceRect;  //绘制的区域宽度尺寸 宽+间距
        rect_height_defaule = rect_width * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (myThread == null) {
            myThread = new MyThread();
            myThread.start();
        } else {
            drawCircle(canvas);//画圆形波浪
            if (isTranslate) {
                drawWaitState(canvas);
            } else {
                drawVoice(canvas);//画圆中间的抖动音
            }
        }
    }

    /**
     * 开启一个子线程绘制ui
     */
    private class MyThread extends Thread {
        @Override
        public void run() {
            while (isRun) {
                logic();
                postInvalidate();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void logic() {
        current_point++;
        if (current_point > voiceLength.length) {
            current_point = 0;
        }
    }

    /***
     * 外部调用 音量大小值
     * @param soundNum
     */
    public void setVoiceNum(int soundNum) {
        isTranslate = false;
        this.soundNum = soundNum;
        invalidate();
    }

    /**
     * 是否是翻译状态
     */
    public void transLateState() {
        isTranslate = true;
    }

    /**
     * 绘制等待状态
     *
     * @param canvas
     */
    private void drawWaitState(Canvas canvas) {
        int height = getHeight();
        float transSize = getWidth() / 2 - (drawWidth / 2);
        canvas.translate(transSize, 0);
        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < voiceLength.length; i++) {
            float height_up = (height / 2) - (rect_height_defaule / 2);
            float height_down = (height / 2) + (rect_height_defaule / 2);
            RectF oval3 = new RectF(0, height_up, rect_width, height_down);// 设置个新的长方形
            if (current_point == i) {
                mPaint.setColor(colorS[colorS.length - 1]);
            } else {
                mPaint.setColor(Color.GRAY);
            }
            canvas.drawRoundRect(oval3, radius, radius, mPaint);//第二个参数是x半径，第三个参数是y半径
            canvas.translate((rect_distance + rect_width), 0);
        }
        canvas.restore();
    }

    /**
     * 绘制声音波浪圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
//        int rectWidth = (voiceLength.length * rect_distance)
//                + (voiceLength.length * rect_width);  //计算圆的直径=7个间距+7个宽度
//        Log.e("SoundView", "rect_width:" + rect_width);
//
//        circlrPaint.setColor(0x992695F0);
//        //语音外圆  蓝色
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (rectWidth * 1 / 2) + (soundNum * 2), circlrPaint);  //绘制外圈
//        //语言内圆  白色
//        circlrPaint.setColor(Color.WHITE);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (rectWidth * 1 / 2), circlrPaint);  //绘制内圈
//        //绘制文字
//        circlrPaint.setColor(0xff2695F0);
//        circlrPaint.setTextSize(textSize);
//        Rect bounds = new Rect();
//        circlrPaint.getTextBounds(text_desc, 0, text_desc.length(), bounds);//获取字体尺寸同measureText函数
//        canvas.drawText(text_desc, (getWidth() / 2) - (bounds.width() / 2), (getHeight() / 2 - rectWidth / 2 + (bounds.height() * 2)), circlrPaint);
    }

    /**
     * 改变text值
     *
     * @param text_desc
     */
    public void setTextView(String text_desc) {
        this.text_desc = text_desc;
    }


    /***
     * 绘制抖动声音
     * @param canvas
     */
    private void drawVoice(Canvas canvas) {
        float height_up = 0;
        float height_down = 0;
        int height = getHeight();
        float transSize = getWidth() / 2 - (drawWidth / 2);
        canvas.translate(transSize, 0);//平移到原点
        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < voiceLength.length; i++) {
            mPaint.setColor(colorS[i]);
            height_up = (height / 2) - (voiceLength[i] * soundNum);
            height_down = (height / 2) + (voiceLength[i] * soundNum);
            RectF oval3 = new RectF(0, height_up, rect_width, height_down);// 设置个新的长方形
            canvas.drawRoundRect(oval3, radius, radius, mPaint);//第二个参数是x半径，第三个参数是y半径
            canvas.translate((rect_distance + rect_width), 0);//每次画完一个平移rect_distance + rect_width
        }
        canvas.restore();
    }

}
