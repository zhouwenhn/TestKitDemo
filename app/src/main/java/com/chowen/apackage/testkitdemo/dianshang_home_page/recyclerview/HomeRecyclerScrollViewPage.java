package com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.utils.L;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author zhouwen
 * @since 2017/2/26
 */

public class HomeRecyclerScrollViewPage extends SupportFragment {

    private View mView = null;

    private PagerAdapter mViewPageAdapter;

    private SparseArray<View> mList = new SparseArray<>();

    private ViewPager mViewPager;

    private RecyclerView mRecyclerList;

    private RecyclerAdapter mAdapter;

    private View mHeaderView;

    private GridLayoutManager layoutManager;

    //    private LinearLayoutManager layoutManager;
    private StaggeredGridLayoutManager sLayoutManager;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    public static HomeRecyclerScrollViewPage newInstance() {
        HomeRecyclerScrollViewPage fragment = new HomeRecyclerScrollViewPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.dianshang_recycler_home_page, container, false);

            initRecyclerListView();
        }
        return mView;
    }

    private void initRecyclerListView() {
        mRecyclerList = (RecyclerView) mView.findViewById(R.id.recycler_list);

        List<View> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(getActivity().getLayoutInflater().inflate(R.layout.recycler_item_view, null));
        }

//        layoutManager = new LinearLayoutManager(getContext());
//        sLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerList.setLayoutManager(layoutManager);
        mRecyclerList.setItemAnimator(new DefaultItemAnimator());
        mRecyclerList.setHasFixedSize(true);

        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.header_layout, mRecyclerList, false);

        mAdapter = new RecyclerAdapter(list);
        mAdapter.setHeaderView(mHeaderView);
        mRecyclerList.setAdapter(mAdapter);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isHeaderPosition(position) ? layoutManager.getSpanCount() : 1;
            }
        });

        initHeaderViewPager();

        mAdapter.setOnclickListener(new IRecyclerListener() {
            @Override
            public void onItemClickListener(View v) {
                L.e("onItemClickListener>>>"+ v.getId());
            }
        });
    }

    private boolean isHeaderPosition(int position) {
        return position == 0;
    }

    private void initHeaderViewPager() {
        mList.append(0, getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.append(1, getActivity().getLayoutInflater().inflate(R.layout.home_view_page2, null));
        mList.append(2, getActivity().getLayoutInflater().inflate(R.layout.home_view_page3, null));
        mList.append(3, getActivity().getLayoutInflater().inflate(R.layout.home_view_page4, null));

        mViewPager = (ViewPager) mHeaderView.findViewById(R.id.view_pager);

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
                switch (state) {
//                    case ViewPager.SCROLL_STATE_DRAGGING:
//                        mHandler.removeMessages(0);
//                        break;
//                    case ViewPager.SCROLL_STATE_IDLE:
//                        mHandler.sendEmptyMessageDelayed(0, 2000);
//                        break;
                    default:
                        break;
                }
            }
        });

        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHandler.removeMessages(0);
    }
}
