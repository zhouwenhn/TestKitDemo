package com.chowen.apackage.testkitdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author zhouwen
 * @since 2017/2/28
 */

public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
