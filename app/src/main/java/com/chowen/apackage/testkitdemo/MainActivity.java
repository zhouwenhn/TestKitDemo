package com.chowen.apackage.testkitdemo;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
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

import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;

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

        L.e("checkActivity>>>>isBackgroundRunning=" + isBackgroundRunning());
    }

    private boolean isBackgroundRunning() {
        String processName = "activity.main.permissiontest";

        L.e("checkApplication>>>"+checkApplication(processName));

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        activityManager.getAppTasks()


        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        if (activityManager == null) return false;
// get running application processes
        List<ActivityManager.RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : processList) {
            if (process.processName.startsWith(processName)) {
                boolean isBackground = process.importance != IMPORTANCE_FOREGROUND && process.importance != IMPORTANCE_VISIBLE;
                boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
                if (isBackground || isLockedState) return true;
                else return false;
            }
        }
        return false;
    }

    public boolean checkApplication(String packageName) {
        if (packageName == null || "".equals(packageName)){
            return false;
        }
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.e("onActivityResult>>>>222222" +  "data==null="+(data==null)+
//                data.getBundleExtra("bund").getString("key") +
                ">>>requestCode=" + requestCode + ">>resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }
    //经纶调起人脸setResult方法与onActivityResult方法不是同步的,不同launchmode返回时机不一样
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
        Log.e("MainActivity.this", "MainActivity>>>onDestroy");
//        Intent intent = new Intent(MainActivity.this, TestService.class);
//        stop
        super.onDestroy();
    }
}
