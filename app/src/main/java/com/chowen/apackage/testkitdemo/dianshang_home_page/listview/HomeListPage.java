package com.chowen.apackage.testkitdemo.dianshang_home_page.listview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.dianshang_home_page.listview.adapter.HomeListAdapter;
import com.chowen.apackage.testkitdemo.utils.L;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author zhouwen
 * @since 2017/2/26
 */

public class HomeListPage extends SupportFragment {

    private View mView = null;

    private PagerAdapter mViewPageAdapter;

    private SparseArray<View> mList = new SparseArray<>();

    private ViewPager mViewPager;

    private ListView mListView;

    private View mListContainer;

    private View mHeaderView;

    private int mPosition = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, false);
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };

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
            mView = inflater.inflate(R.layout.dianshang_listview_home_page, container, false);

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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.e("onItemClick>>>" + position);
            }
        });
    }

    private void initViewPager() {
        mList.append(0, getActivity().getLayoutInflater().inflate(R.layout.home_view_page, null));
        mList.append(1, getActivity().getLayoutInflater().inflate(R.layout.home_view_page2, null));
        mList.append(2, getActivity().getLayoutInflater().inflate(R.layout.home_view_page3, null));
        mList.append(3, getActivity().getLayoutInflater().inflate(R.layout.home_view_page4, null));

        mViewPager = (ViewPager) mHeaderView.findViewById(R.id.view_pager);

        mViewPageAdapter = new ViewPageAdapter(mList);
        mViewPager.setAdapter(mViewPageAdapter);

        mHandler.sendEmptyMessageDelayed(0, 2000);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                L.e("onPageScrollStateChanged>>>" + state);
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mHandler.removeMessages(0);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        break;
                    default:
                        break;
                }

            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHandler.removeMessages(0);
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
