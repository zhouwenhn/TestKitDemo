package com.chowen.apackage.testkitdemo.horizontalScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.dianshang_home_page.listview.adapter.HomeListAdapter;
import com.chowen.apackage.testkitdemo.utils.L;
import com.chowen.apackage.testkitdemo.viewpage.base.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author zhouwen
 * @since 2017/2/26
 */

public class ScrollViewPage extends SupportFragment {

    private View mView = null;

    public static ScrollViewPage newInstance() {
        ScrollViewPage fragment = new ScrollViewPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.scroller_view_page, container, false);
            initViews();
        }
        L.e("MyLinearLayout>>ScrollViewPage>"+getContext().toString());
        return mView;
    }

    private void initViews() {
        LinearLayout ll = (LinearLayout) mView.findViewById(R.id.ll);
        for (int i = 0; i < 10; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(R.mipmap.view_img);
            ll.addView(iv);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
