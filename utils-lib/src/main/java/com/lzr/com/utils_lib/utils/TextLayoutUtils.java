package com.lzr.com.utils_lib.utils;

import android.text.Layout;
import android.widget.TextView;

public class TextLayoutUtils {

    /**
     * 返回(x,y)坐标的字或字母
     * @param textView
     * @param x
     * @param y
     * @return
     */
    public static int getPreciseOffset(TextView textView, int x, int y) {
        Layout layout = textView.getLayout();
        if (layout != null) {
            int topVisiableLine = layout.getLineForVertical(y);//y坐标所在字的行数
            int offset = layout.getOffsetForHorizontal(topVisiableLine, x);//x坐标在水平方向上的偏移字数（最接近的字偏移量）
            int offsetX = (int) layout.getPrimaryHorizontal(offset);//通过字偏移量反算x坐标偏移
            if (offsetX > x) {//两个偏移量不相等，则使用最近的字
                return layout.getOffsetToLeftOf(offset);
            } else {
                return offset;
            }

        }
        return -1;
    }
}
