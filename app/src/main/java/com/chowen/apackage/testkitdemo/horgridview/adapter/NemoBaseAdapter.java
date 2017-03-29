package com.chowen.apackage.testkitdemo.horgridview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhouwen on 2017/3/25.
 */
public abstract class NemoBaseAdapter<T> extends BaseAdapter {

    public List<T> mDataList = null;

    public Context mContext;

    private int mItemRes;

    public NemoBaseAdapter(Context context, @NonNull List<T> data, @LayoutRes int itemViewRes) {
        mContext = context;
        mDataList = data;
        mItemRes = itemViewRes;
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mDataList != null ? mDataList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderHelper viewHolderHelper = ViewHolderHelper.getViewHolder(
                mContext, position, mItemRes, convertView, parent);
        buildItemView(viewHolderHelper, getItem(position), position);
        return viewHolderHelper.getConvertView();
    }

    @Override
    public boolean isEnabled(int position) {
        return position < mDataList.size();
    }

    public void add(T item) {
        mDataList.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> items) {
        mDataList.addAll(items);
        notifyDataSetChanged();
    }

    public void set(T oldItem, T newItem) {
        set(mDataList.indexOf(oldItem), newItem);
    }

    public void set(int index, T item) {
        mDataList.set(index, item);
        notifyDataSetChanged();
    }

    public T getList() {
        return (T)mDataList;
    }

    public void remove(T item) {
        mDataList.remove(item);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mDataList.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> items) {
        mDataList.clear();
        mDataList.addAll(items);
        notifyDataSetChanged();
    }

    public boolean contains(T item) {
        return mDataList.contains(item);
    }

    /**
     * Clear data list
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    protected abstract void buildItemView(ViewHolderHelper viewHolder, T item,int position);
}
