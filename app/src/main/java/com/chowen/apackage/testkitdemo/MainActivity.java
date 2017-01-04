package com.chowen.apackage.testkitdemo;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;

import com.chowen.apackage.testkitdemo.treadtest.MThread;
import com.chowen.apackage.testkitdemo.treadtest.MyThread2;

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
    }

    private void testCaseThread() {
        MThread myThead = new MThread();
        Thread thread = new Thread(myThead);
        MyThread2 thread2 = new MyThread2();
        thread.start();
        thread.setPriority(Thread.MIN_PRIORITY);
        thread2.start();
        thread2.setPriority(Thread.MAX_PRIORITY);
    }
}
