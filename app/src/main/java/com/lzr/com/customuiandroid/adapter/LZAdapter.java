package com.lzr.com.customuiandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzr.com.control_lib.control_adapter.BaseAdapter;
import com.lzr.com.control_lib.control_adapter.PlugAdapter;
import com.lzr.com.customuiandroid.R;
import com.lzr.com.customuiandroid.adapter.holder.LZHolder;

import java.util.List;

public class LZAdapter extends PlugAdapter<String, LZHolder> {
    private View.OnClickListener mOnClickListener;

    public LZAdapter(Context context) {
        super(context);
    }

    public LZAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @NonNull
    @Override
    public LZHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LZHolder(LayoutInflater.from(mContext).inflate(R.layout.lzr_adapter_item_view, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LZHolder holder, int position) {
        holder.mTitle.setText(getData(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
