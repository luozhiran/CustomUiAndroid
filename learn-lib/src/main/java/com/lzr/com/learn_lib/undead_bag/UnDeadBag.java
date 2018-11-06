package com.lzr.com.learn_lib.undead_bag;

import com.lzr.com.ui_utils_lib.DensityData;

public class UnDeadBag {

    private static class SingletonHolder{
        private static final UnDeadBag instance = new UnDeadBag();
    }
    private UnDeadBag(){

    }
    public static final UnDeadBag getInstance(){
        return SingletonHolder.instance;
    }

    public DensityData ResourcesDensityData;

}
