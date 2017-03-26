package com.chowen.apackage.testkitdemo.horizontalScrollView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.chowen.apackage.testkitdemo.utils.L;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/9.
 */

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context) {

        super(context);
        L.e("MyLinearLayout>>>"+context.toString());
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        L.e("MyLinearLayout>>>"+context.toString());
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        L.e("MyLinearLayout>>>"+context.toString());
    }
}
