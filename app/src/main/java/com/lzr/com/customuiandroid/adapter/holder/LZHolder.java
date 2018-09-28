package com.lzr.com.customuiandroid.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzr.com.customuiandroid.R;

public class LZHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;

    public LZHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(@NonNull final View itemView) {
        mTitle = itemView.findViewById(R.id.title);
    }

}
