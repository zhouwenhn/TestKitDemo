package com.chowen.apackage.testkitdemo.horgridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.horgridview.adapter.NemoBaseAdapter;
import com.chowen.apackage.testkitdemo.horgridview.adapter.ViewHolderHelper;
import com.chowen.apackage.testkitdemo.utils.L;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/28.
 */

public class HorizontalFragment extends SupportFragment {
    private View mView;


    public static HorizontalFragment newInstance() {
        HorizontalFragment fragment = new HorizontalFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.scrollview_page, container, false);

        }

        return mView;
    }

}
