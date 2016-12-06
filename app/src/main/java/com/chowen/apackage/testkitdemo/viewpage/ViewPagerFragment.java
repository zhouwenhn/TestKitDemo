package com.chowen.apackage.testkitdemo.viewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.chowen.apackage.testkitdemo.viewpage.base.BaseViewPagerFragment;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public class ViewPagerFragment extends BaseViewPagerFragment {

    private SparseArray<Fragment> mViews = new SparseArray<Fragment>();

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mTabs = new String[]{"消息","发现","关注","我的"};
        mTabScrollBarWidth = 24;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected SparseArray<Fragment> initViewPagers() {
        mViews.append(0, new Page1());
        mViews.append(1, new Page2());
        mViews.append(2, new Page2());
        mViews.append(3, new Page2());
        return mViews;
    }

    @Override
    protected void onRadioGroupCheckedChanged(RadioGroup radioGroup, int position) {
    }

    @Override
    protected void onViewPagerScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

}
