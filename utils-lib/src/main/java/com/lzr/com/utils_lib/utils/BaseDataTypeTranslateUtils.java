package com.lzr.com.utils_lib.utils;

import java.text.DecimalFormat;

public class BaseDataTypeTranslateUtils {
    public static int ERROR = -1;
    public static final String ONEO_DECIMAL = "#.0";
    public static final String TWO_DECIMAL = "#.00";
    public static final String THREE_DECIMAL = "#.000";
    public static final String FOUR_DECIMAL = "#.0000";

    public static int toInt(String v) {
        try {
            return Integer.valueOf(v);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public static float toFloat(String v) {
        try {
            return Float.valueOf(v);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public static double toDouble(String v) {
        try {
            return Double.valueOf(v);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}
