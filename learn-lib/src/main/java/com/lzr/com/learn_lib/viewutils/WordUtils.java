package com.lzr.com.learn_lib.viewutils;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class WordUtils {
    /**
     * Url 编码
     *
     * @param c 字符
     * @return 编码结果
     * @throws Exception 编码异常
     */
    public static String urlEncode(char c) throws Exception {
        String encode = URLEncoder.encode(String.valueOf(c), "utf-8");
        encode = encode.replaceAll("%", "");
        return encode.toLowerCase();
    }

    /**
     * 从JSON中读取Frame数据
     *
     * @param json 数据
     * @return 集合里面存储的是每个笔顺对应的点数据
     */
    public static List<List<Point>> extractFrame(String json) {
        List<List<Point>> mFrame = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);
            JSONArray out = data.getJSONArray("frame");
            for (int i = 0; i < out.length(); i++) {
                List<Point> ps = new ArrayList<>();
                JSONArray frame = out.getJSONArray(i);
                for (int j = 0; j < frame.length(); j++) {
                    JSONArray pointArr = frame.getJSONArray(j);
                    Point p = new Point();
                    p.set(pointArr.getInt(0), pointArr.getInt(1));
                    ps.add(p);
                }
                mFrame.add(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mFrame;
    }

    /**
     * 从assets文件夹读取数据
     *
     * @param context 上下文
     * @param name    文件名
     * @return 数据
     */
    public static String readJsonFromAssets(Context context, String name) {
        String json = "";
        try {
            StringBuilder builder = new StringBuilder();
            InputStreamReader streamReader = new InputStreamReader(context.getAssets().open(name), "UTF-8");
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("");
            }
            json = builder.toString();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 从JSON中读取Fill数据
     *
     * @param json 数据
     * @return 多笔画对应的填充数据
     */
   public static List<List<Point>> extractFill(String json) {
        List<List<Point>> mFrame = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONArray("data").getJSONObject(0);
            JSONArray out = data.getJSONArray("fill");
            for (int i = 0; i < out.length(); i++) {
                List<Point> ps = new ArrayList<>();
                JSONArray frame = out.getJSONArray(i);
                for (int j = 0; j < frame.length(); j++) {
                    JSONArray pointArr = frame.getJSONArray(j);
                    Point p = new Point();
                    p.set(pointArr.getInt(0), pointArr.getInt(1));
                    ps.add(p);
                }
                mFrame.add(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mFrame;
    }

    /**
     * 分割List
     *
     * @param list       集合
     * @param startIndex 起点
     * @param endIndex   尾点
     * @return 子集合
     */
    public static List<Point> subList(List<Point> list, int startIndex, int endIndex) {
        List<Point> subList = new ArrayList<>();
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex >= list.size()) {
            endIndex = list.size() - 1;
        }
        for (int i = startIndex; i < list.size() && i < endIndex; i++) {
            Point p = list.get(i);
            subList.add(p);
        }
        //subList = list.subList(startIndex, endIndex);
        return subList;
    }
    /**
     * 将点连成线
     *
     * @param points 一系列点
     * @return 路径
     */
    public static Path generatePathByPoint(List<Point> points,int width,int height) {
        Path path = new Path();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            p = scalePoint(p,width,height);
            if (i == 0) {
                path.moveTo(p.x, p.y);
            } else {
                path.lineTo(p.x, p.y);
            }
        }
        path.close();
        return path;
    }


    /**
     * 将点连成线
     *
     * @param points 一系列点
     * @return 路径
     */
    public static Path generateScalePathByPoint(List<Point> points,int width,int height) {
        Path path = new Path();
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            p = scalePoint(p,width,height);
            if (i == 0) {
                path.moveTo(p.x-30, p.y-30);
            } else {
                path.lineTo(p.x-30, p.y-30);
                path.lineTo(p.x, p.y);
                path.lineTo(p.x+30, p.y+30);
            }
        }
        path.close();
        return path;
    }

    /**
     * 缩放原数据坐标到布局大小
     */
    private static Point scalePoint(Point point,int width,int height) {
        Point p = new Point();
        //760 是原数据默认宽高
        p.x = (int) (point.x / (760f / width));
        p.y = (int) (point.y / (760f / height));
        return p;
    }

    /**
     * 将路径转变成不规则区域
     *
     * @param path 路径
     * @return 区域
     */
    public static Region pathToRegion(Path path) {
        RectF bounds = new RectF();
        bounds.setEmpty();
        path.computeBounds(bounds, true);
        Region region = new Region();
        region.setPath(path, new Region((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom));
        return region;
    }
}
