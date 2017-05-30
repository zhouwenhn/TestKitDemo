package com.chowen.apackage.testkitdemo.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.rxbus.StudentEvent;
import com.chowen.apackage.testkitdemo.utils.L;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.HashSet;
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

    private TextView mTextview;

    private boolean mIsRepeat = false;

    private Animation animation2;

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

        List<String> list1 = new ArrayList<>();
//        StudentEvent s1 = new StudentEvent("1", "chowen");
//        StudentEvent s12 = new StudentEvent("33", "chowen");
//        String d = new String("chowen");
//        list1.add(d);
//        list1.add("chowen2");
//
//        List<String> list2 = new ArrayList<>();
////        StudentEvent s2 = new StudentEvent("1", "chowen");
////        StudentEvent s22 = new StudentEvent("333", "chowen");
//        list2.add("chowen");
//        list2.add("chowen3");
//
//
//        L.e(">>>>>>"+ list1.contains(list2.get(0))+">>>"+list2.contains(list1.get(1)));
//
//        L.e(">>>>>22222>"+ list1.removeAll(list2));
//        list1.addAll(list2);
//        for (int i = 0; i < list1.size(); i++) {
//            L.e(">>>>>33333>"+ list1.get(i));
//        }


        for (int i = 0; i < 1000; i++) {
            list1.add("add" + i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            L.e("for>" + list1.get(i));
        }
        L.e("for cost_time>" + (System.currentTimeMillis() - start));

        long sta = System.currentTimeMillis();
        for (String s : list1) {
            L.e("foreach>>>>>33333>" + s);
        }
        L.e("foreach cost_time>" + (System.currentTimeMillis() - sta));
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

                Rect rect = new Rect();
                v.getLocalVisibleRect(rect);
                L.e("chowen#getLocalVisibleRect>>" + rect);
                v.getGlobalVisibleRect(rect);
                L.e("chowen#getGlobalVisibleRect>>" + rect);

            }
        });
        mView.findViewById(R.id.btn_start_translation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextview = (TextView) mView.findViewById(R.id.tv_translation);
                int[] point = new int[2];
                mTextview.getLocationOnScreen(point);
                int[] point2 = new int[2];
                mTextview.getLocationInWindow(point2);
                mTextview.setPivotX(point[0] + 100);
                mTextview.setTranslationX(100f);
//                mTextview.setPivotY(point[1]);
//                mTextview.setRotationY(160);
//                mTextview.setRotationX(160);
//                mTextview.setScaleX(2f);
                L.e("btn_start_translation>>>" + point[0] + "--" + point[1]);
                L.e("btn_start_translation>>222>" + point2[0] + "--" + point2[1]);
                float curTranslationX = mTextview.getTranslationX();
                ObjectAnimator oba = ObjectAnimator.ofFloat(mTextview, "translationY",
                        curTranslationX, -200, curTranslationX + 100);
                oba.setDuration(DURATION_TIME);
                oba.start();
            }
        });

        mView.findViewById(R.id.btn_start_scaleY).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ObjectAnimator o = ObjectAnimator.ofFloat(mView.findViewById(R.id.tv_scaleY),
//                        "scaleY", 1f, 5f, 1f);
                LinearLayout ll = (LinearLayout) mView.findViewById(R.id.ll);
                ll.setHapticFeedbackEnabled(true);
                ll.setPivotX(-88);
                ll.setPivotY(99);
                ObjectAnimator o = ObjectAnimator.ofFloat(ll,
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
                moveIn.setDuration(8000);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(textview, "rotation", 0f, 360f);
                rotate.setDuration(4000);
                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0f, 1f);
                fadeInOut.setDuration(1000 * 10);
                AnimatorSet set = new AnimatorSet();
                set.play(rotate).with(fadeInOut).after(moveIn);
//                set.setDuration(DURATION_TIME);
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

        mView.findViewById(R.id.path).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(PathPage.newInstance());
            }
        });

        mView.findViewById(R.id.btn_start_r).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView iv = (ImageView) mView.findViewById(R.id.iv_r);
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotation);
                iv.startAnimation(animation);

            }
        });


        mView.findViewById(R.id.btn_start_t).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.anim_translation);
                final View imageView = mView.findViewById(R.id.iv_scan);
                imageView.startAnimation(animation2);
                animation2.setRepeatCount(Animation.INFINITE);
                animation2.setRepeatMode(Animation.RESTART);

                animation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        L.e("onAnimationStart>>>>");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        Animation animation1= AnimationUtils.loadAnimation(getContext(), R.anim.anim_translation2);
//                        TranslateAnimation  translateAnimation = new TranslateAnimation(0,0,700,10);
//                        translateAnimation.setFillAfter(false);
//                        translateAnimation.setDuration(3000);

                        mIsRepeat = !mIsRepeat;
                        if (mIsRepeat) {
                            animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.anim_translation2);
                            animation2.setRepeatCount(Animation.INFINITE);
                            animation2.setRepeatMode(Animation.RESTART);
                        } else {
                            animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.anim_translation);
                            animation2.setRepeatCount(Animation.INFINITE);
                            animation2.setRepeatMode(Animation.RESTART);
                        }
                        imageView.startAnimation(animation2);
                        L.e("onAnimationEnd>>>>mIsRepeat=" + mIsRepeat);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        L.e("onAnimationRepeat>>>>");
                        mIsRepeat = !mIsRepeat;
                        if (mIsRepeat) {
                            animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.anim_translation2);
                        } else {
                            animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.anim_translation);
                        }
                        imageView.startAnimation(animation2);

                    }
                });
            }
        });
        mView.findViewById(R.id.btn_start_f).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatAnim(mView.findViewById(R.id.iv_r), 500);
            }
        });
        mView.findViewById(R.id.btn_start_s).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleAnim(mView.findViewById(R.id.iv_r), 1000);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void scaleAnim(View view, int delay) {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2.0f);
        objectAnimator.setDuration(2000);
//        objectAnimator.setRepeatCount(Animation.INFINITE);
//        objectAnimator.setRepeatMode(Animation.INFINITE);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 2.0f);
        objectAnimator1.setDuration(2000);
//        objectAnimator1.setRepeatMode(Animation.INFINITE);
//        objectAnimator1.setRepeatCount(Animation.INFINITE);

        int point[] = new int[2];
        view.getLocationInWindow(point);
L.e("getLocationInWindow>>x="+point[0]+">y="+point[1]);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "translationY", point[1]-696-100);
        objectAnimator2.setDuration(6000);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "translationX", point[0]-284);
        objectAnimator3.setDuration(6000);

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(objectAnimator).with(objectAnimator1).after(objectAnimator2).with(objectAnimator3);
//        animatorSet.playTogether(objectAnimator, objectAnimator1);
//        animatorSet.play(objectAnimator).before(objectAnimator1);
        animatorSet.playSequentially(objectAnimator, objectAnimator1,objectAnimator2,objectAnimator3);
        animatorSet.setStartDelay(delay);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                L.e("onAnimationStart>>>222");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                L.e("onAnimationEnd>>>222");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                L.e("onAnimationCancel>>>2222");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                L.e("onAnimationRepeat>>>2222");
            }
        });

        animatorSet.addPauseListener(new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {

            }

            @Override
            public void onAnimationResume(Animator animation) {

            }
        });

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                L.e("onAnimationStart>>>");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                L.e("onAnimationEnd>>>");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                L.e("onAnimationCancel>>>");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                L.e("onAnimationRepeat>>>");
            }
        });

        mView.findViewById(R.id.btn_start_sa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                ValueAnimator objectAnimator4 = ValueAnimator.ofFloat(1.0f, 0.2f);
                ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.1f);
                objectAnimator4.setDuration(3000);
                objectAnimator4.start();
                objectAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        L.e("onAnimationUpdate>>onAnimationUpdate="+value);
                        mView.findViewById(R.id.iv_r).setScaleX(value);
                        mView.findViewById(R.id.iv_r).setScaleY(value);
                        mView.findViewById(R.id.iv_r).setAlpha(value);
                    }
                });
            }
        });
        final View    view1   = mView.findViewById(R.id.iv_rotation);
        mView.findViewById(R.id.btn_start_rr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ObjectAnimator objectA= ObjectAnimator.ofFloat(view1,
                        "rotationY", view1.getRotationY(), view1.getRotationY()+90);
                objectA.setDuration(2000);
                objectA.start();

                ValueAnimator valueAnimator = ValueAnimator.ofFloat(view1.getRotationX(), view1.getRotationX()+360);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float a = (float) animation.getAnimatedValue();
                        view1.setRotationX(a);

                    }
                });
                valueAnimator.setDuration(6000);
                valueAnimator.start();

            }
        });
    }

    private void floatAnim(View view, int delay) {
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationXAnim = ObjectAnimator.ofFloat(view, "translationX", -8.0f, 8.0f, -8.0f);
        translationXAnim.setDuration(1500);
        translationXAnim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        translationXAnim.setRepeatMode(ValueAnimator.INFINITE);//
//        translationXAnim.start();
        animators.add(translationXAnim);
        ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(view, "translationY", -4.0f, 4.0f, -4.0f);
        translationYAnim.setDuration(1000);
        translationYAnim.setRepeatCount(ValueAnimator.INFINITE);
        translationYAnim.setRepeatMode(ValueAnimator.INFINITE);
//        translationYAnim.start();
        animators.add(translationYAnim);

        AnimatorSet btnSexAnimatorSet = new AnimatorSet();
        btnSexAnimatorSet.playTogether(animators);
        btnSexAnimatorSet.setStartDelay(delay);
        btnSexAnimatorSet.start();
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

    private List<StudentEvent> getUpdate() {
        return new ArrayList<>();
    }

    private void sort(List<StudentEvent> updateDatas) {

        List<StudentEvent> cache = getUpdate();

        List<StudentEvent> pickCacheLists = new ArrayList<>();

        for (int i = 0; i < cache.size(); i++) {
            StudentEvent cacheitem = cache.get(i);
            for (int j = 0; j < updateDatas.size(); j++) {
                if (cacheitem.getId() == updateDatas.get(j).getId()) {
                    pickCacheLists.add(cacheitem);
                }
            }
        }
    }

}
