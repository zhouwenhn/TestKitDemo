package com.chowen.apackage.testkitdemo.horgridview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.animator.AnimatorPage;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/28.
 */

public class HorizontalGridViewFragment extends SupportFragment {
    private View mView;

//    List<CityItem> cityList;
//    RelativeLayout itmel;
//    GridView gridView;
//    int NUM = 2;

    HorizontalScrollView horizontalScrollView;
    GridView gridView;
    DisplayMetrics dm;
    private int NUM = 4; // 每行显示个数
    private int hSpacing = 20;// 水平间距

    public static HorizontalGridViewFragment newInstance() {
        HorizontalGridViewFragment fragment = new HorizontalGridViewFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView != null){
            mView = inflater.inflate(R.layout.gridview_page, container, false);
            gridView = (GridView) mView.findViewById(R.id.grid);
            horizontalScrollView = (HorizontalScrollView) mView.findViewById(R.id.scrollView);
            horizontalScrollView.setHorizontalScrollBarEnabled(false);// 隐藏滚动条
            getScreenDen();
            setValue();
//            setData();
            setGridView();
        }

        return mView;
    }
    private void setValue() {
        GridViewAdapter adapter = new GridViewAdapter(getContext());
        int count = adapter.getCount();
        int columns = (count % 2 == 0) ? count / 2 : count / 2 + 1;
        gridView.setAdapter(adapter);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(columns * dm.widthPixels / NUM,
                LinearLayout.LayoutParams.WRAP_CONTENT);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(columns * dm.widthPixels / NUM,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(params);
        gridView.setColumnWidth(dm.widthPixels / NUM);
        // gridView.setHorizontalSpacing(hSpacing);
        gridView.setStretchMode(GridView.NO_STRETCH);
        if (count <= 3) {
            gridView.setNumColumns(count);
        } else {
            gridView.setNumColumns(columns);
        }
        gridView.notifyAll();
    }

    private void getScreenDen() {
        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
    }


    /**设置数据*/
//    private void setData() {
//        cityList = new ArrayList<CityItem>();
//        CityItem item = new CityItem();
//        item.setCityName("深圳");
//        item.setCityCode("0755");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("上海");
//        item.setCityCode("021");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("广州");
//        item.setCityCode("020");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("北京");
//        item.setCityCode("010");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("武汉");
//        item.setCityCode("027");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("孝感");
//        item.setCityCode("0712");
//        cityList.add(item);
//        cityList.addAll(cityList);
//    }


    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
        //////////////////
//        GridViewAdapter adapter = new GridViewAdapter(getContext(),
//                cityList);
//        int count = adapter.getCount();
//        int columns = (count % 2 == 0) ? count / 2 : count / 2 + 1;
//        gridView.setAdapter(adapter);
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(columns * dm.widthPixels / NUM,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        gridView.setLayoutParams(params);
//        gridView.setColumnWidth(dm.widthPixels / NUM);
//        gridView.setStretchMode(GridView.NO_STRETCH);
//        if (count <= 3) {
//            gridView.setNumColumns(count);
//        } else {
//            gridView.setNumColumns(columns);
//        }


       ////////////////

//        int size = cityList.size();
//        int length = 100;
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        float density = dm.density;
//        int gridviewWidth = (int) (size * (length + 4) * density);
//        int itemWidth = (int) (length * density);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
//        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
//        gridView.setColumnWidth(itemWidth); // 设置列表项宽
//        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
//        gridView.setStretchMode(GridView.NO_STRETCH);
//        gridView.setNumColumns(size); // 设置列数量=列表集合数
//
//        GridViewAdapter adapter = new GridViewAdapter(getContext(),
//                cityList);
//        gridView.setAdapter(adapter);
    }

    /**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CityItem> list;
        public GridViewAdapter(Context _context) {
//            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.gridview_list_item, null);
//            TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
//            TextView tvCode = (TextView) convertView.findViewById(R.id.tvCode);
//            CityItem city = list.get(position);
//
//            tvCity.setText(city.getCityName());
//            tvCode.setText(city.getCityCode());
            return convertView;
        }
    }

    public class CityItem {
        private String cityName;
        private String cityCode;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }
    }
}
