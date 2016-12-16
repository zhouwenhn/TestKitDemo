package com.chowen.apackage.testkitdemo.viewpage.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.bar.Bar;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.BasePagerAdapter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public abstract class BaseViewPagerFragment extends SupportFragment implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    protected SparseArray<Fragment> mViewArray = new SparseArray<Fragment>();

    protected Bar mBar;

    protected View mTabScrollBar;

    protected ViewPager mViewPager;

    protected RadioGroup mRadioGroup;

    protected int mTabScrollBarWidth;

    private String[] mTabs;

    private View mView = null;

    private int mNavItemWidth;

    private int mTopNavIndicatorMargin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.view_pager_base, container, false);
            initViews();
            initTabsItem();
            initScrollBar();
        }
        return mView;
    }

    private void initViews() {
        mBar = (Bar) mView.findViewById(R.id.ll_top_bar);
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.rg_radio_group);
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager = (ViewPager) mView.findViewById(R.id.lvp_contribute_pager);
        mViewPager.setOnPageChangeListener(this);
        initViewPagers();
        BasePagerAdapter pagerAdapter = new BasePagerAdapter(getFragmentManager());
        pagerAdapter.setPages(mViewArray);
        mViewPager.setAdapter(pagerAdapter);
        mBar.setBarListener(new Bar.BarListener() {
            @Override
            public void backOnClickListener() {
                Toast.makeText(getContext(),"back", Toast.LENGTH_LONG).show();
            }

            @Override
            public void moreOnClickListener() {
                Toast.makeText(getContext(),"more", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initTabsItem() {
        mTabs = getTabs();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int indicatorWidth = dm.widthPixels / mTabs.length;
        mRadioGroup.removeAllViews();

        for (int i = 0; i < mTabs.length; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setId(i);
            rb.setText(mTabs[i]);
            rb.setTextSize(18);
            rb.setTextColor(getContext().getResources().getColor(R.color.nav_text_color));
            rb.setGravity(Gravity.CENTER);
            rb.setButtonDrawable(null);
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

    /**
     * 初始化views
     */
    protected abstract void initViewPagers();

    /**
     * tab items
     * @return tabs
     */
    protected abstract @NonNull String[] getTabs();

    /**
     * CheckedChanged 选择
     * @param radioGroup rediogroup
     * @param position 位置index
     */
    protected abstract void onRadioGroupCheckedChanged(RadioGroup radioGroup, int position);

    /**
     * onPageScrolled 滑动
     * @param position 位置index
     * @param positionOffset 当前页面滑动比例
     * @param positionOffsetPixels 当前页面滑动像素
     */
    protected abstract void onViewPagerScrolled(int position, float positionOffset, int positionOffsetPixels);

    /**
     * onPageSelected
     * @param position 位置index
     */
    protected void onViewPagerSelected(int position) {

    }

    /**
     * onPageScrollStateChanged
     * @param state 0（END）,1(PRESS) , 2(UP)
     */
    protected void onViewPagerScrollStateChanged(int state) {

    }

    /**
     * 设置界面预加载个数
     * @param isAllPageLimit true 全部加载，false加载一个
     */
    protected void setAlwaysKeepPager(boolean isAllPageLimit) {
        if(isAllPageLimit) {
            mViewPager.setOffscreenPageLimit(mViewArray.size());
        } else {
            mViewPager.setOffscreenPageLimit(1);
        }

    }

    /**
     * 设置界面预加载个数
     * @param pageLimit 预加载界面个数
     */
    protected void setAlwaysKeepPager(int pageLimit) {
        mViewPager.setOffscreenPageLimit(pageLimit);
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
