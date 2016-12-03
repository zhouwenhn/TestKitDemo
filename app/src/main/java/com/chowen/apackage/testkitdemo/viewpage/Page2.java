package com.chowen.apackage.testkitdemo.viewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chowen.apackage.testkitdemo.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public class Page2 extends SupportFragment {

    private View mView = null;


    public static Page2 newInstance() {

        Bundle args = new Bundle();
        Page2 fragment = new Page2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.view_page2, container, false);
        }
        return mView;
    }

}
