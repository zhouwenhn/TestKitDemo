package com.chowen.apackage.testkitdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chowen.apackage.testkitdemo.utils.L;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/6/6.
 */

public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("action.intent.recevier")){
            L.e("TestReceiver>>>"+intent.getAction());
            context.sendBroadcast(new Intent("action.test.receiver"));
        }
    }
}
