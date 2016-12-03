package com.chowen.apackage.testkitdemo.viewpage.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.BasePagerAdapter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public abstract class BaseViewPagerFragment extends SupportFragment implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    protected SparseArray<Fragment> mViewArray = new SparseArray<Fragment>();

    protected String[] mTabs;

    protected View mTabScrollBar;

    protected ViewPager mViewPager;

    protected RadioGroup mRadioGroup;

    protected int mTabScrollBarWidth;

    private View mView = null;

    private int mNavItemWidth;

    private int mTopNavIndicatorMargin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.view_pager_base, container, false);
        }
        initViewPager();
        addRadioItem();
        initScrollBar();

        return mView;
    }

    private void initViewPager() {
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.rg_radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager = (ViewPager) mView.findViewById(R.id.lvp_contribute_pager);
        mViewPager.setOnPageChangeListener(this);
        mViewArray = initViewPagers();
        BasePagerAdapter pagerAdapter = new BasePagerAdapter(getFragmentManager());
        pagerAdapter.setPages(mViewArray);
        mViewPager.setAdapter(pagerAdapter);
    }

    public void addRadioItem() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int indicatorWidth = dm.widthPixels / mTabs.length;
        mRadioGroup.removeAllViews();

        for (int i = 0; i < mTabs.length; i++) {
            RadioButton rb = new RadioButton(getContext());
//                    (RadioButton) LayoutInflater.
//                    from(getContext()).inflate(R.layout.radio_button, mRadioGroup);
//                    new RadioButton(getContext());
            rb.setId(i);
            rb.setText(mTabs[i]);
//            rb.setPadding(0, 20, 0, 0);
            rb.setLayoutParams(new LinearLayout.LayoutParams(indicatorWidth,
                    ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));

            mRadioGroup.addView(rb);
        }
    }

    private void initScrollBar() {
        mTabScrollBar = mView.findViewById(R.id.scroll_bar_view);
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        mNavItemWidth = dm.widthPixels / mTabs.length;
        int navIndicatorWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, mTabScrollBarWidth, getContext().getResources()
                        .getDisplayMetrics());
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mTabScrollBar
                .getLayoutParams();
        mTopNavIndicatorMargin = (mNavItemWidth - navIndicatorWidth) / 2;
        lp.leftMargin = mTopNavIndicatorMargin;
    }

    protected abstract SparseArray<Fragment> initViewPagers();

    protected abstract void onRadioGroupCheckedChanged(RadioGroup radioGroup, int position);

    protected abstract void onViewPagerScrolled(int position, float positionOffset, int positionOffsetPixels);

    protected void onViewPagerSelected(int position) {

    }

    protected void onViewPagerScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int position) {
        RadioButton btn = (RadioButton) mRadioGroup.getChildAt(position);
        btn.setChecked(true);
        onRadioGroupCheckedChanged(radioGroup, position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mTabScrollBar
                .getLayoutParams();
        lp.leftMargin = (mNavItemWidth * position)
                + mTopNavIndicatorMargin
                + (int) (positionOffset * (mTabScrollBar.getWidth() + mTopNavIndicatorMargin * 2));
        mTabScrollBar.requestLayout();
        onViewPagerScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        onViewPagerSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        onViewPagerScrollStateChanged(state);
    }
}
