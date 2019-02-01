package com.lzr.global;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalMVTool {

    private Map<String, GlobalTriggerListener> mTriggerListenerMap;
    public Application mApplication;
    private DensityData mResourcesDensityData;
    private Map<String, String> mStateMachine;
    private Map<String, SharedPreferences> mSharePreferenceMap;

    private GlobalMVTool() {
        this.mTriggerListenerMap = new ConcurrentHashMap();
        this.mStateMachine = new ConcurrentHashMap();
        this.mSharePreferenceMap = new ConcurrentHashMap();
    }

    public void init(Application application) {
        this.mApplication = application;
        this.mResourcesDensityData = UISizeUtils.getScreenDensity_ByResources(application);
    }

    public void addState(String key, String value) {
        if (this.mStateMachine.containsKey(key)) {
            throw new IllegalArgumentException("state Machine Key has exit");
        } else {
            this.mStateMachine.put(key, value);
        }
    }

    public String getState(String key) {
        return (String)this.mStateMachine.get(key);
    }

    public void removeState(String key) {
        this.mStateMachine.remove(key);
    }

    public boolean isContainState(String key) {
        return this.mStateMachine.containsKey(key);
    }

    public DensityData getResourcesDensityData() {
        return this.mResourcesDensityData;
    }

    public void addTriggerListener(String key, GlobalTriggerListener listener) {
        if (this.mTriggerListenerMap.containsKey(key)) {
//            throw new IllegalArgumentException("Trigger Listener Key has exit");
        } else {
            this.mTriggerListenerMap.put(key, listener);
        }
    }

    public void removeTriggerListner(String key) {
        this.mTriggerListenerMap.remove(key);
    }

    public boolean isTriggerKey(String key) {
        return this.mTriggerListenerMap.containsKey(key);
    }

    public GlobalTriggerListener getGlobalTriggerListener(String key) {
        return (GlobalTriggerListener)this.mTriggerListenerMap.get(key);
    }

    public void addShare(String key, SharedPreferences sp) {
        this.mSharePreferenceMap.put(key, sp);
    }

    public SharedPreferences getSharePreference(String key) {
        return (SharedPreferences)this.mSharePreferenceMap.get(key);
    }

    public static final GlobalMVTool getInstance() {
        return GlobalMVTool.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final GlobalMVTool instance = new GlobalMVTool();

        private SingletonHolder() {
        }
    }
}
