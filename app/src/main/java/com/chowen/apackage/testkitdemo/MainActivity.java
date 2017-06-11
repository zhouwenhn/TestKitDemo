package com.chowen.apackage.testkitdemo;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chowen.apackage.testkitdemo.treadtest.MThread;
import com.chowen.apackage.testkitdemo.treadtest.MyThread2;
import com.chowen.apackage.testkitdemo.utils.L;

import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
//        MainActivity.this.reportFullyDrawn();
        testCaseThread();
        //test service
        Intent intent = new Intent(MainActivity.this, TestService.class);
        startService(intent);


        checkActivity();
    }

    private void checkActivity() {
        Intent intent = new Intent();
        intent.setClassName("activity.main.permissiontest", "activity.main.permissiontest.MainActivity");
        L.e("checkActivity>>>>"+(getPackageManager().resolveActivity(intent, 0) == null));
        if (getPackageManager().resolveActivity(intent, 0) == null) {
            L.e("checkActivity>>>>不存在");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.e("onActivityResult>>>>222222"+
                data.getBundleExtra("bund").getString("key")+">>>requestCode="+requestCode+">>resultCode="+resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    //test thread
    private void testCaseThread() {
        MThread myThead = new MThread();
        Thread thread = new Thread(myThead);
        MyThread2 thread2 = new MyThread2();
        thread.start();
        thread.setPriority(Thread.MIN_PRIORITY);
        thread2.start();
        thread2.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    protected void onDestroy() {
        Log.e("MainActivity.this","MainActivity>>>onDestroy");
//        Intent intent = new Intent(MainActivity.this, TestService.class);
//        stop
        super.onDestroy();
    }
}
