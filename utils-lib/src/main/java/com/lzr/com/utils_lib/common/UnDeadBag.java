package com.lzr.com.utils_lib.common;


import android.app.Application;

import com.lzr.com.utils_lib.utils.UISizeUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnDeadBag {//存储一些生命周期较长的变量或者对象

    private Map<String, GlobalTriggerListener> mTriggerListenerMap;
    private Map<String, String> mOption;
    private Application mApplication;

    private static class SingletonHolder {
        private static final UnDeadBag instance = new UnDeadBag();
    }

    public void init(Application application) {
        mApplication = application;
        UnDeadBag.getInstance().ResourcesDensityData = UISizeUtils.getScreenDensity_ByResources(mApplication);
        UnDeadBag.getInstance().statusBarHeight = UISizeUtils.getStatusBarHeight(mApplication);
    }

    public Application getApplication() {
        return mApplication;
    }

    private UnDeadBag() {
        mTriggerListenerMap = new ConcurrentHashMap<>();
        mOption = new ConcurrentHashMap<>();
    }

    public static final UnDeadBag getInstance() {
        return SingletonHolder.instance;
    }

    public DensityData ResourcesDensityData;

    public int statusBarHeight = 0;

    public void addTriggerListener(String key, GlobalTriggerListener listener) {
        if (mTriggerListenerMap.containsKey(key)) {
            throw new IllegalArgumentException("key 重复");
        } else {
            mTriggerListenerMap.put(key, listener);
        }
    }

    public void removeTriggerListner(String key) {
        mTriggerListenerMap.remove(key);
    }

    public interface GlobalTriggerListener<T> {

        void trigger(T value);
    }

}
