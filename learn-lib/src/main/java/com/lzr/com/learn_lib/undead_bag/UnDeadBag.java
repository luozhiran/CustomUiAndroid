package com.lzr.com.learn_lib.undead_bag;

import android.graphics.Paint;

import com.lzr.com.ui_utils_lib.DensityData;

public class UnDeadBag {//存储一些生命周期较长的变量或者对象

    private static class SingletonHolder{
        private static final UnDeadBag instance = new UnDeadBag();
    }
    private UnDeadBag(){

    }
    public static final UnDeadBag getInstance(){
        return SingletonHolder.instance;
    }

    public DensityData ResourcesDensityData;
    public int statusBarHeight = 0;

}
