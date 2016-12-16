package com.chowen.apackage.testkitdemo.viewpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chowen.apackage.testkitdemo.bar.Bar;
import com.chowen.apackage.testkitdemo.viewpage.base.BaseViewPagerFragment;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public class ViewPagerFragment extends BaseViewPagerFragment {

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
        mTabScrollBarWidth = 24;
        initBars();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initBars() {
//        mBar.setBarListener(new Bar.BarListener() {
//            @Override
//            public void backOnClickListener() {
//                Toast.makeText(getContext(),"back", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void moreOnClickListener() {
//                Toast.makeText(getContext(),"more", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void initViewPagers() {
        mViewArray.append(0, new Page1());
        mViewArray.append(1, new Page2());
        mViewArray.append(2, new Page2());
        mViewArray.append(3, new Page2());
    }

    @NonNull
    @Override
    protected String[] getTabs() {
        return new String[]{"消息","发现","关注","我的"};
    }

    @Override
    protected void onRadioGroupCheckedChanged(RadioGroup radioGroup, int position) {
    }

    @Override
    protected void onViewPagerScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

}
