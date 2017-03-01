package com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.dianshang_home_page.adapter.HomeListAdapter;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author zhouwen
 * @since 2017/2/26
 */

public class HomeRecyclerViewPage extends SupportFragment {

    private View mView = null;

    private PagerAdapter mViewPageAdapter;

    private List<View> mList = new ArrayList<>();

    private ViewPager mViewPager;

    private RecyclerView mRecyclerList;

    private ImageAdapter mAdapter;

//    private ListView mListView;

    private View mHeaderView;

    public static HomeRecyclerViewPage newInstance() {
        HomeRecyclerViewPage fragment = new HomeRecyclerViewPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.dianshang_recycler_home_page, container, false);

            initViews();
            initViewPager();
            initRecyclerListView();

        }
        return mView;
    }

    private void initRecyclerListView() {
        List<View> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(getActivity().getLayoutInflater().inflate(R.layout.recycler_item_view, null));
        }

        mAdapter = new ImageAdapter(list);

        mRecyclerList = (RecyclerView) mView.findViewById(R.id.recycler_list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL, false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerList.setLayoutManager(layoutManager);
        mRecyclerList.setItemAnimator(new DefaultItemAnimator());
        mRecyclerList.setAdapter(mAdapter);

    }

    private void initViews() {
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.header_layout, null);
    }

    private void initViewPager() {
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));

        mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);

        mViewPageAdapter = new ViewPageAdapter(mList);
        mViewPager.setAdapter(mViewPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
        private final static int VIEW_HEADER = 1, VIEW_ITEM = 0;
        private List<View> list = new ArrayList<>();
        private View headerView;

        public ImageAdapter(List<View> list) {
            this.list = list;
        }

        public void setHeaderView(View headerView) {
            this.headerView = headerView;
            notifyItemInserted(0);
        }

        @Override
        public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_view, parent, false);
            return new ImageAdapter.ViewHolder(v);
        }


        @Override
        public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
            // pass
//            if (list != null)
//                holder.tv.setText(list.get(position));
        }


        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //showToast("This is RecyclerView Item");
                    }
                });
            }
        }
    }

}
