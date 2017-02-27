package com.chowen.apackage.testkitdemo.dianshang_home_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.dianshang_home_page.adapter.HomeListAdapter;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author zhouwen  gzzhouwen1@corp.netease.com
 * @since 2017/2/26
 */

public class HomeListPage extends SupportFragment {

    private View mView = null;

    private PagerAdapter mViewPageAdapter;

    private List<View> mList = new ArrayList<>();

    private ViewPager mViewPager;

    private ListView mListView;

    private View mListContainer;

    private View mHeaderView;

    public static HomeListPage newInstance() {
        HomeListPage fragment = new HomeListPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.dianshang_home_page, container, false);

            initViews();
            initViewPager();
            initListView();

        }
        return mView;
    }

    private void initViews() {
        mListContainer = mView.findViewById(R.id.list_container);
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.header_layout, null);
    }

    private void initListView() {
        mListView = (ListView) mView.findViewById(R.id.list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("天猫");
        }
        HomeListAdapter homeListAdapter = new HomeListAdapter(getContext(), list);

        mListView.addHeaderView(mHeaderView, null, false);
        mListView.setAdapter(homeListAdapter);
    }

    private void initViewPager() {
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.add(getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));

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

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
