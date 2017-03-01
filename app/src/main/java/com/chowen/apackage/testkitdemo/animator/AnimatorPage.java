package com.chowen.apackage.testkitdemo.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class AnimatorPage extends SupportFragment {

    private static final int DURATION_TIME = 5000;

    private View mView = null;

    private TextView mTv;

    TextView mTextview;

    public static AnimatorPage newInstance() {
        AnimatorPage fragment = new AnimatorPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.animator_layout, container, false);
            initViews();
            initAnimators();
        }
        return mView;
    }

    private void initViews() {
        mTv = (TextView) mView.findViewById(R.id.tv);
        mView.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1
//                ObjectAnimator oba = ObjectAnimator.ofFloat(mTv, "alpha",1f, 0f, 1f);
                ObjectAnimator oba = ObjectAnimator.ofFloat(mTv, "rotation", 0f, 360f);
                oba.setDuration(DURATION_TIME);
                oba.start();
            }
        });
        mView.findViewById(R.id.btn_start_translation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextview = (TextView) mView.findViewById(R.id.tv_translation);
                float curTranslationX = mTextview.getTranslationX();
                ObjectAnimator oba = ObjectAnimator.ofFloat(mTextview, "translationX",
                        curTranslationX, -800, curTranslationX);
                oba.setDuration(DURATION_TIME);
                oba.start();
            }
        });

        mView.findViewById(R.id.btn_start_scaleY).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator o = ObjectAnimator.ofFloat(mView.findViewById(R.id.tv_scaleY),
//                        "scaleY", 1f, 5f, 1f);
                ObjectAnimator o = ObjectAnimator.ofFloat(mView.findViewById(R.id.tv_scaleY),
                        "scaleX", 1f, 5f, 1f);
                o.setDuration(DURATION_TIME);
                o.start();
            }
        });

        mView.findViewById(R.id.btn_start_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textview = (TextView) mView.findViewById(R.id.tv_a);
                float curTranslationX = textview.getTranslationX();
                ObjectAnimator moveIn = ObjectAnimator.ofFloat(textview, "translationX", -500f, 0f);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(textview, "rotation", 0f, 360f);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0f, 1f);
                AnimatorSet set = new AnimatorSet();
                set.play(moveIn).with(fadeInOut).after(rotate);
                set.setDuration(DURATION_TIME);
                set.start();
                set.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });
           //加载属性动画xml
        Animator animator = AnimatorInflater.loadAnimator(getContext(), R.animator.animator_value);
        animator.setTarget(mTv);
        animator.start();
    }

    private void initAnimators() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f, 0, 1f);
        animator.setDuration(1000 * 2);
        animator.setTarget(mTextview);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                L.e("animation>>>" + animation.getAnimatedValue());
            }
        });
    }

}
