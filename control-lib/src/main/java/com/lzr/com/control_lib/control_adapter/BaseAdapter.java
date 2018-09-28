package com.lzr.com.control_lib.control_adapter;

import java.util.List;

public interface BaseAdapter<T> {
    T getData(int position);
    int getSize();
    int getPosition(T t);
    void addData(T t);
    void addData(List<T> list);
}
