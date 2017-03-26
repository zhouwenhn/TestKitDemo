package com.chowen.apackage.testkitdemo.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.chowen.apackage.testkitdemo.utils.L;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/24.
 */

public class AnimatorView extends RelativeLayout{

    private Paint mPaint = new Paint();

    private Path mPath = new Path();

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
            postDelayed(runnable, 2000);
        }
    };


    public AnimatorView(Context context) {
        super(context);
        init();
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//
//    }

    private void init() {
        mPaint.setColor(Color.RED);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(640,640);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2, getHeight()/2);
        L.e("canvas>>>>>>"+getWidth()/2);

        Path path = new Path();                     // 创建Path

        path.lineTo(200, 200);                      // lineTo
        path.lineTo(200,0);

        canvas.drawPath(path, mPaint);

//        mPath.moveTo(140,180);
//        mPath.lineTo(240,180);
//        mPath.close();
//
//        mPath.addRect(-100,-100,100,100, Path.Direction.CW);
////        canvas.save();
//        canvas.drawPath(mPath, mPaint);
//        canvas.restore();

//        ObjectAnimator oa = ObjectAnimator.ofFloat(this,"translationY",360);
//        ValueAnimator v = ValueAnimator.ofInt(0,100);
//        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int value = (int) animation.getAnimatedValue();
//                mPath.moveTo(40,80);
//                mPath.lineTo(140,80*value);
//            }
//        });
//        v.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                canvas.drawPath(mPath, mPaint);
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onAnimationEnd(Animator animation) {
////              long an = animation.getTotalDuration();
//                mPath.moveTo(40,80);
//                mPath.lineTo(140,80);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        v.start();
//        handler.postDelayed(runnable, 100);
    }
}
