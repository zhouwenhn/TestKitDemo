package com.chowen.apackage.testkitdemo;

import android.app.Application;

/**
 * @author zhouwen  gzzhouwen1@corp.netease.com
 * @since 2017/2/28
 */

public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}
