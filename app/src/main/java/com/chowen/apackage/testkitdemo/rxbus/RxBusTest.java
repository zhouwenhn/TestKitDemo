package com.chowen.apackage.testkitdemo.rxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chowen.apackage.testkitdemo.utils.L;

import me.yokeyword.fragmentation.SupportFragment;
import rx.functions.Action1;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/26.
 */

public class RxBusTest extends SupportFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        Observable rxSbscription= RxBus.getInstance().toObserverable(StudentEvent.class)
//                .subscribe(new Action1<StudentEvent>() {
//                    @Override
//                    public void call(StudentEvent studentEvent) {
//                        L.e("id:"+ studentEvent.getId()+"  name:"+ studentEvent.getName());
//                    }
//                });

        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
