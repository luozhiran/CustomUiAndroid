package com.lzr.com.learn_lib.viewutils;

import android.content.Context;
import android.graphics.Point;

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
}
