package com.lzr.com.learn_lib.learn_adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzr.com.learn_lib.R;

public class LearnHolder extends RecyclerView.ViewHolder {
    public TextView mTitle;

    public LearnHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }


    private void initView(@NonNull final View itemView) {
        mTitle = itemView.findViewById(R.id.title);
    }
}
