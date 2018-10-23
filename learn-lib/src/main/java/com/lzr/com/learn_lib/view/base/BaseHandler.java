package com.lzr.com.learn_lib.view.base;


import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class BaseHandler extends Handler {
    private WeakReference<BaseHandlerView> handlerViewWeakReference;

    public BaseHandler(BaseHandlerView view) {
        handlerViewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void handleMessage(Message msg) {
        if (handlerViewWeakReference != null && handlerViewWeakReference.get() != null) {
            handlerViewWeakReference.get().handleMessage(msg);
        }
    }
}
