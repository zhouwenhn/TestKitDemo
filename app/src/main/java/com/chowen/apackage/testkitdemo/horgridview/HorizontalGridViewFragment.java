package com.chowen.apackage.testkitdemo.horgridview;

import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.horgridview.adapter.NemoBaseAdapter;
import com.chowen.apackage.testkitdemo.horgridview.adapter.ViewHolderHelper;
import com.chowen.apackage.testkitdemo.utils.BitmapUtil;
import com.chowen.apackage.testkitdemo.utils.L;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/28.
 */

public class HorizontalGridViewFragment extends SupportFragment {
    private View mView;

    List<CityItem> cityList;
//    RelativeLayout itmel;
//    GridView gridView;
//    int NUM = 2;

    HorizontalScrollView horizontalScrollView;
    GridView gridView;
    DisplayMetrics dm;
    NemoBaseAdapter adapter;
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
        if (mView == null) {
            mView = inflater.inflate(R.layout.gridview_page, container, false);
            gridView = (GridView) mView.findViewById(R.id.grid);
            horizontalScrollView = (HorizontalScrollView) mView.findViewById(R.id.scrollView);
            horizontalScrollView.setHorizontalScrollBarEnabled(false);// 隐藏滚动条

            mView.findViewById(R.id.ll_view).getBackground().setAlpha(200);//0~255透明度值 ，0为完全透明，255为不透明

            initViews();
            setData();
            setValue();
        }

        return mView;
    }

    private void initViews() {
        MyGridView gv = (MyGridView) mView.findViewById(R.id.grid);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CityItem cityItem = cityList.get(position);
                cityItem.cityName = "广州";
                cityItem.cityCode = "12345";
                adapter.notifyDataSetChanged();

                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_LONG).show();

            }
        });
        ((ImageView)mView.findViewById(R.id.iv)).setImageBitmap(BitmapUtil.toRoundBitmap(
                BitmapFactory.decodeResource(getResources(),R.mipmap.view_img)));
        ((ImageView)mView.findViewById(R.id.iv2)).setImageBitmap(BitmapUtil.centerSquareScaleBitmap(
                BitmapFactory.decodeResource(getResources(),R.mipmap.test_pic),400));
        ((ImageView)mView.findViewById(R.id.iv3)).setImageBitmap(BitmapUtil.centerSquareScaleBitmap(
                BitmapFactory.decodeResource(getResources(),R.mipmap.test_pic1),400));
        ((ImageView)mView.findViewById(R.id.iv4)).setImageBitmap(BitmapUtil.centerSquareScaleBitmap(
                BitmapFactory.decodeResource(getResources(),R.mipmap.test_pic2),400));
//        ((ImageView)mView.findViewById(R.id.iv2)).setImageBitmap(BitmapUtil.cropBitmap(
//                BitmapFactory.decodeResource(getResources(),R.mipmap.view_img)));
    }

    private void setValue() {
        adapter = new NemoBaseAdapter<CityItem>(getActivity(), cityList, R.layout.grid_item_view) {
            @Override
            protected void buildItemView(ViewHolderHelper viewHolder, CityItem item, int position) {
                ((TextView) viewHolder.getItemView(R.id.text)).setText(item.cityName + position);
            }
        };
        gridView.setAdapter(adapter);

//        L.e(">getWidth>>>"+ gridView.getColumnWidth());

        int count = adapter.getCount();
        int columns;
//        if (count == 2){
//            columns = 2;
//            count = 3;
//        } else {

        columns = (count % 2 == 0) ? count / 2 : count / 2 + 1;
//        }
        L.e("columns * 240>>>>" + "columns>>" + columns + ">count>>" + count);

        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(columns * 260,//dm.widthPixels / NUM
                LinearLayout.LayoutParams.WRAP_CONTENT);
        L.e("columns>>>>" + columns * 260 + ">>>>>" + count);
        gridView.setLayoutParams(params);
        gridView.setColumnWidth(220);
        gridView.setStretchMode(GridView.NO_STRETCH);

        if (count < 3) {
            gridView.setNumColumns(count);
        } else {
            gridView.setNumColumns(columns);
        }
    }

    //ment>>setValue#106: columns * 240>>>>columns>>2>count>>3

    /**
     * 设置数据
     */
    private void setData() {
        cityList = new ArrayList<CityItem>();
        CityItem item = new CityItem();
        item.setCityName("深圳");
        item.setCityCode("0755");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("上海");
        item.setCityCode("021");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("广州");
        item.setCityCode("020");
        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("北京");
//        item.setCityCode("010");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("武汉");
//        item.setCityCode("027");
//        cityList.add(item);
////
////
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
//        item = new CityItem();
//        item.setCityName("北京");
//        item.setCityCode("010");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("武汉2");
//        item.setCityCode("027");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("武汉");
//        item.setCityCode("027");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("孝感2");
//        item.setCityCode("0712");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("孝感");
//        item.setCityCode("0712");
//        cityList.add(item);
//        item = new CityItem();
//        item.setCityName("武汉");
//        item.setCityCode("027");
//        cityList.add(item);

//        Collections.reverse(cityList);

//        List<CityItem> lists = new ArrayList<>();
//        for (int i = cityList.size(); i > 0; i--) {
//            lists.add(cityList.get(i-1));
//        }
//        cityList.clear();
//        cityList.addAll(lists);

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
