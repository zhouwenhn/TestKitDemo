package com.chowen.apackage.testkitdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.GradientDrawable;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview.IRecyclerListener;
import com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview.RecyclerAdapter;
import com.chowen.apackage.testkitdemo.utils.L;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author zhouwen
 * @since 2017/2/26
 */

public class ClipDrawableViewPage extends SupportFragment {

    private View mView = null;


    public static ClipDrawableViewPage newInstance() {
        ClipDrawableViewPage fragment = new ClipDrawableViewPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.clip_drawable_layout, container, false);
            initViews();
        }
        return mView;
    }

    private void initViews() {

        final ImageView iv2 = (ImageView) mView.findViewById(R.id.iv_clip2);
        final ClipDrawable clipDrawable = (ClipDrawable) iv2.getDrawable();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (clipDrawable.getLevel() == 10000){
                    iv2.setBackgroundColor(getResources().getColor(R.color.color0));
                    handler.removeCallbacks(this);
                } else {
                    clipDrawable.setLevel(clipDrawable.getLevel()+1000);
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);




        final ProgressBar pb = (ProgressBar) mView.findViewById(R.id.pb_view);
        ClipDrawable cd = new ClipDrawable(getResources().getDrawable(R.drawable.volume_slider_shallow),
                Gravity.BOTTOM, ClipDrawable.VERTICAL);
        final ClipDrawable cd2 = new ClipDrawable(getResources().getDrawable(R.drawable.volume_slider_red), Gravity.BOTTOM,
                ClipDrawable.VERTICAL);
        pb.setProgressDrawable(cd);

        ValueAnimator o = ValueAnimator.ofInt(1, 100);
        o.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                L.e("ValueAnimator#onAnimationUpdate>>value=" + value);
//                ImageView pb = (ImageView)mView.findViewById(R.id.iv);
//                ClipDrawable cd = (ClipDrawable) pb.getBackground();
//                cd.setLevel(value*100);
                if (value > 50)
                    pb.setProgressDrawable(cd2);
                pb.setProgress(value);
            }
        });
        o.setDuration(10000);
        o.start();




        ImageView iv = (ImageView) mView.findViewById(R.id.iv_img);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0.8f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(iv, "scaleY", 1f, 0.8f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(400);
        set.playTogether(objectAnimator, objectAnimator2);
        set.start();

        initIvv();


    }

    private void initIvv() {
        ImageView ivv = (ImageView) mView.findViewById(R.id.iv_bnt);
        ivv.setVisibility(View.VISIBLE);
        ObjectAnimator ob= ObjectAnimator.ofFloat(ivv, "alpha", 0.5f, 1.0f);
        ob.setDuration(300);

        int point[] = new int[2];
        ivv.getLocationInWindow(point);
        L.e("getLocationInWindow>>x=" + point[0] + ">y=" + point[1]);
        ObjectAnimator ob2 = ObjectAnimator.ofFloat(ivv, "translationX", point[0], point[0]+100);
        ob2.setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(ob, ob2);
        set.start();


        ImageView ivv2 = (ImageView) mView.findViewById(R.id.iv_bnt2);
        ivv2.setVisibility(View.VISIBLE);
        ObjectAnimator o = ObjectAnimator.ofFloat(ivv2, "alpha", 0.5f, 1.0f);
        o.setDuration(300);
        int point2[] = new int[2];
        ivv2.getLocationInWindow(point2);
        ObjectAnimator o2 = ObjectAnimator.ofFloat(ivv2, "translationX", point2[0],  point2[0]-100);
        o2.setDuration(500);
        AnimatorSet set2 = new AnimatorSet();
        set2.playTogether(o, o2);
        set2.start();

    }
}
