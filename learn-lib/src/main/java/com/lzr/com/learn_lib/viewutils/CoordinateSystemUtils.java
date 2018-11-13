package com.lzr.com.learn_lib.viewutils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.lzr.com.learn_lib.undead_bag.UnDeadBag;

public class CoordinateSystemUtils {

    private static final int startX = 50, startY = 50;
    private static final  int spaceX = 50,spaceY = 20;

    public static void drawX(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(UnDeadBag.getInstance().ResourcesDensityData.getWidth() - 80, startY);

        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setTextSize(20);
        bluePaint.setStrokeWidth(1);
        bluePaint.setStyle(Paint.Style.FILL);

        int count = (UnDeadBag.getInstance().ResourcesDensityData.getWidth()-80-startX)/spaceX;
        float wordWidth = 0;
        for (int i=0;i<count;i++){
            path.moveTo(startX+i*spaceX, startY - 10);
            path.lineTo(startX+i*spaceX, startY);
            wordWidth = bluePaint.measureText(String.valueOf(startX+i*spaceX));
            canvas.drawText(String.valueOf(startX+i*spaceX), startX+i*spaceX-wordWidth/2, startY - 10, bluePaint);
        }

        path.moveTo(UnDeadBag.getInstance().ResourcesDensityData.getWidth() - 90,startY-10);
        path.lineTo(UnDeadBag.getInstance().ResourcesDensityData.getWidth() - 80, startY);

        path.moveTo(UnDeadBag.getInstance().ResourcesDensityData.getWidth() - 90,startY+10);
        path.lineTo(UnDeadBag.getInstance().ResourcesDensityData.getWidth() - 80, startY);
        canvas.drawText("X",UnDeadBag.getInstance().ResourcesDensityData.getWidth() - 90,startY-paint.measureText("X")-15,paint);
        canvas.drawPath(path, paint);
    }

    public static void drawY(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(startX, UnDeadBag.getInstance().ResourcesDensityData.getHeight() - 80);
        int count = (UnDeadBag.getInstance().ResourcesDensityData.getHeight()-80-startX)/spaceY;
        float wordWidth = 0;

        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bluePaint.setTextSize(20);
        bluePaint.setStrokeWidth(1);
        bluePaint.setStyle(Paint.Style.FILL);

        float wordHeight = (bluePaint.getFontMetrics().bottom-bluePaint.getFontMetrics().top);

        for (int i=0;i<count;i++){
            path.moveTo(startX-10,startY+i*spaceY);
            path.lineTo(startX,startY+i*spaceY);
            wordWidth = bluePaint.measureText(String.valueOf(startY+i*spaceY));
            canvas.drawText(String.valueOf(startY+i*spaceY), startX-wordWidth-10, startY+i*spaceY+wordHeight/4, bluePaint);
        }

        path.moveTo(startX-10,UnDeadBag.getInstance().ResourcesDensityData.getHeight() - 90);
        path.lineTo(startX, UnDeadBag.getInstance().ResourcesDensityData.getHeight() - 80);

        path.moveTo(startX+10,UnDeadBag.getInstance().ResourcesDensityData.getHeight() - 90);
        path.lineTo(startX, UnDeadBag.getInstance().ResourcesDensityData.getHeight() - 80);


        canvas.drawText("Y",startX-paint.measureText("Y")-15,UnDeadBag.getInstance().ResourcesDensityData.getHeight() - 80,paint);

        canvas.drawPath(path, paint);
    }
}
