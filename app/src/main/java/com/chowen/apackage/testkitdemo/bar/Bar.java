package com.chowen.apackage.testkitdemo.bar;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chowen.apackage.testkitdemo.R;

/**
 * Created by zhouwen on 2016/12/16.
 * Description:
 */

public class Bar extends RelativeLayout implements View.OnClickListener {

    private BarListener mBarListener;

    private ImageView mIvBack;

    private ImageView mIvMore;

    private TextView mTvTitle;

    public Bar(Context context) {
        this(context, null);
    }

    public Bar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        View barLayout = LayoutInflater.from(getContext()).inflate(R.layout.bar, null);
        mIvBack = (ImageView) barLayout.findViewById(R.id.iv_back);
        mIvMore = (ImageView) barLayout.findViewById(R.id.iv_more);
        mTvTitle = (TextView) barLayout.findViewById(R.id.tv_title);
        mIvBack.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
        this.addView(barLayout);
    }

    public void setBarListener(BarListener barListener) {
        mBarListener = barListener;
    }

    public void setBarTitle(CharSequence title) {
        mTvTitle.setText(title);
    }

    public void setBarTitle(@StringRes int resId) {
        mTvTitle.setText(getResources().getText(resId));
    }

    public void setBarTitleColor(@ColorRes int colorId) {
        mTvTitle.setTextColor(getResources().getColor(colorId));
    }

    public void setBarTitleSize(@DimenRes int size) {
        mTvTitle.setTextSize(getResources().getDimension(size));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                mBarListener.backOnClickListener();
                break;
            case R.id.iv_more:
                mBarListener.moreOnClickListener();
                break;
        }
    }

    public interface BarListener {

        void backOnClickListener();

        void moreOnClickListener();
    }

}
