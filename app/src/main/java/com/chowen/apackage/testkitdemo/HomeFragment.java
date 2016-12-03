package com.chowen.apackage.testkitdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chowen.apackage.testkitdemo.viewpage.ViewPagerFragmentTest;
import com.chowen.apackage.testkitdemo.viewpage.base.ViewPagerFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public class HomeFragment extends SupportFragment {

    private View mView = null;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (mView == null) {
            mView = inflater.inflate(R.layout.home_page, container, false);
        }
        initViews();
        return mView;
    }

    private void initViews() {
        mView.findViewById(R.id.btn_view_pager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(ViewPagerFragment.newInstance());
//                start(ViewPagerFragmentTest.newInstance());
            }
        });
    }
}
