package com.chowen.apackage.testkitdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author zhouwen  gzzhouwen1@corp.netease.com
 * @since 2017/2/25
 */

public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e("TestService.this","MainActivity>>>onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e("TestService.this","TestService>>>onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TestService.this","TestService>>>onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e("TestService.this","TestService>>>onStart");
        super.onStart(intent, startId);
    }
}
