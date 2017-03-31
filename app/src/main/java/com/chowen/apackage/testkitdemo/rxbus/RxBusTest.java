package com.chowen.apackage.testkitdemo.rxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chowen.apackage.testkitdemo.R;
import com.chowen.apackage.testkitdemo.horgridview.HorizontalGridViewFragment;
import com.chowen.apackage.testkitdemo.utils.L;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.functions.Action1;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/26.
 */

public class RxBusTest extends SupportFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_rxbus_view, container, false);
//        RxBus.getInstance().toObserverable(StudentEvent.class)
//                .subscribe(new Action1<StudentEvent>() {
//                    @Override
//                    public void call(StudentEvent studentEvent) {
//                        L.e("id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName());
//                    }
//                });
        view.findViewById(R.id.bt_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().post(new StudentEvent("1233","chowen"));
            }
        });

        return view;

    }

    public static RxBusTest newInstance() {
        RxBusTest fragment = new RxBusTest();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
}
