package com.chowen.apackage.testkitdemo.viewpage.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.chowen.apackage.testkitdemo.utils.L;

import java.util.List;

/**
 * @author zhouwen
 * @since 2017/2/26
 */

public class ViewPageAdapter extends PagerAdapter {

    List<View> mList;

    public ViewPageAdapter(List<View> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         ((ViewPager) container).removeView(mList.get(position % mList.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        L.e("instantiateItem>>>>"+position);
        position %= mList.size();
        View imageView = mList.get(position);
        ((ViewPager) container).addView(imageView);
        return imageView;
    }
}
