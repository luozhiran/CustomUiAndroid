package com.lzr.com.control_lib.control_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class PlugAdapter<T,VH extends RecyclerView.ViewHolder> extends  RecyclerView.Adapter<VH> implements BaseAdapter<T> {

    protected Context mContext;
    protected List<T> mList;

    public PlugAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public PlugAdapter(Context context, List<T> list) {
        this(context);
        if (list != null) {
            list.addAll(list);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public T getData(int position) {
        return mList.get(position);
    }

    @Override
    public int getSize() {
        return mList.size();
    }

    @Override
    public int getPosition(T t) {
        if (t != null) {
            return mList.indexOf(t);
        } else {
            return -1;
        }
    }

    @Override
    public void addData(T t) {
        if (t != null) {
            mList.add(t);
        }
    }

    @Override
    public void addData(List<T> list) {
        if (list != null) {
            mList.addAll(list);
        }
    }
}
