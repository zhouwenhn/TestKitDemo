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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.viewpage.Page1;
import com.chowen.apackage.testkitdemo.viewpage.Page2;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.BasePagerAdapter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public class ViewPagerFragment extends SupportFragment implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    private SparseArray<Fragment> mViewArray = new SparseArray<Fragment>();

    private View mView = null;

    private View mTopLineView;

    private ViewPager mViewPager;

    private RadioGroup mRadioGroup;

    private int mNavItemWidth;

    private int mTopNavIndicatorMargin;

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.view_pager, container, false);
        }
        initViews();
        initScrollBar();
        initViewPagers();
        return mView;
    }

    private void initViewPagers() {
        mViewArray.append(0, new Page1());
        mViewArray.append(1, new Page2());
        BasePagerAdapter pagerAdapter = new BasePagerAdapter(getChildFragmentManager());
        pagerAdapter.setPages(mViewArray);
        mViewPager.setAdapter(pagerAdapter);
    }

    private void initViews() {
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.rg_radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager = (ViewPager) mView.findViewById(R.id.lvp_contribute_pager);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initScrollBar() {
        mTopLineView = mView.findViewById(R.id.top_line_view);
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        mNavItemWidth = dm.widthPixels / 2;
        int navIndicatorWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 64, getContext().getResources()
                        .getDisplayMetrics());
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mTopLineView
                .getLayoutParams();
        mTopNavIndicatorMargin = (mNavItemWidth - navIndicatorWidth) / 2;
        lp.leftMargin = mTopNavIndicatorMargin;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int position) {
        RadioButton btn = (RadioButton) mRadioGroup.getChildAt(position);
        btn.setChecked(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mTopLineView
                .getLayoutParams();
        lp.leftMargin = (mNavItemWidth * position)
                + mTopNavIndicatorMargin
                + (int) (positionOffset * (mTopLineView.getWidth() + mTopNavIndicatorMargin * 2));
        mTopLineView.requestLayout();
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
