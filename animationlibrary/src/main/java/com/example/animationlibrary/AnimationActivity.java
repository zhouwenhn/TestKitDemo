package com.example.animationlibrary;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by zhouwen on 2017/7/7.
 *
 */

public class AnimationActivity extends Activity {
    ImageView mIvRing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.animation_main_layout);
        mIvRing = (ImageView) findViewById(R.id.iv_ring);
        initAnimation();
        super.onCreate(savedInstanceState);
    }

    private void initAnimation() {
        //拉升
        ObjectAnimator oa = ObjectAnimator.ofFloat(mIvRing, "scaleX", 1.0f, 1.2f, 1.0f);
        oa.setRepeatCount(ValueAnimator.INFINITE);
        oa.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa2 = ObjectAnimator.ofFloat(mIvRing, "scaleY", 1.0f, 1.2f, 1.0f);
        oa2.setRepeatCount(ValueAnimator.INFINITE);
        oa2.setRepeatMode(ValueAnimator.REVERSE);

        //浮动
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(mIvRing, "translationX",-8.0f, 8.0f, -8.0f);
        oa3.setRepeatCount(ValueAnimator.INFINITE);
        oa3.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator oa4= ObjectAnimator.ofFloat(mIvRing, "translationY", -8.0f, 8.0f, -8.0f);
        oa4.setRepeatCount(ValueAnimator.INFINITE);
        oa4.setRepeatMode(ValueAnimator.REVERSE);

        AnimatorSet set= new AnimatorSet();
        set.playTogether(oa, oa2, oa3, oa4);
        set.setStartDelay(1000);
        set.setDuration(4000);
        set.setInterpolator(new LinearInterpolator());
        set.start();
    }
}
