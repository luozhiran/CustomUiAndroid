package com.lzr.com.learn_lib.view.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public abstract class BaseHandlerView extends View {
    protected Handler mHandler;

    public BaseHandlerView(Context context) {
        super(context);
        mHandler = new Handler();
    }

    public BaseHandlerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    public abstract void handleMessage(Message msg);
}
