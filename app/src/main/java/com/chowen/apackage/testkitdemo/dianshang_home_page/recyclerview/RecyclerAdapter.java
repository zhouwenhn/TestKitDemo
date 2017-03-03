package com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chowen.apackage.testkitdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/2.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
        implements View.OnClickListener {

    private final static int VIEW_HEADER = 1;
    private final static int VIEW_ITEM = 0;
    private List<View> mList = new ArrayList<>();
    private View mHeaderView;

    private IRecyclerListener mIRecyclerListener;

    public RecyclerAdapter(List<View> list) {
        mList = list;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setOnclickListener(IRecyclerListener iRecyclerListener) {
        mIRecyclerListener = iRecyclerListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_HEADER) {
            return new ViewHolder(mHeaderView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_view, parent, false);
            itemView.setOnClickListener(this);
            return new ViewHolder(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        }
        return VIEW_ITEM;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            return;
        }
        // TODO: 2017/3/2 positon>1的处理
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 1 : 0;
    }

    @Override
    public void onClick(View view) {
        if (mIRecyclerListener != null)
            mIRecyclerListener.onItemClickListener(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
