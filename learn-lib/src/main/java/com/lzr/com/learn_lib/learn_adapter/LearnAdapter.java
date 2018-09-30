package com.lzr.com.learn_lib.learn_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzr.com.control_lib.control_adapter.PlugAdapter;
import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.learn_adapter.holder.LearnHolder;

import java.util.List;

public class LearnAdapter extends PlugAdapter<String, LearnHolder> {

    private View.OnClickListener mOnClickListener;

    public LearnAdapter(Context context) {
        super(context);
    }

    public LearnAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @NonNull
    @Override
    public LearnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearnHolder(LayoutInflater.from(mContext).inflate(R.layout.lzr_adapter_item_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LearnHolder holder, int position) {
        holder.mTitle.setText(getData(position));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mOnClickListener);
    }
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
