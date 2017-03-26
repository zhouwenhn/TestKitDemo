package com.chowen.apackage.testkitdemo.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.utils.L;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author zhouwen
 * @since 2017/2/26
 */

public class PathPage extends SupportFragment {

    private static final int DURATION_TIME = 5000;

    private View mView = null;



    public static PathPage newInstance() {
        PathPage fragment = new PathPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.path_layout, container, false);
        }
        return mView;
    }



}
