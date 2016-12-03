package com.chowen.apackage.testkitdemo.viewpage.base.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhouwen on 2016/12/3.
 * Description:
 */

public class BasePagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> mPages;

    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setPages(@NonNull SparseArray<Fragment> pages) {
        mPages = pages;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return mPages.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mPages.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
