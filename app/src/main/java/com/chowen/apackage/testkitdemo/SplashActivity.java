package com.chowen.apackage.testkitdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.chowen.apackage.testkitdemo.treadtest.MThread;
import com.chowen.apackage.testkitdemo.treadtest.MyThread2;

import me.yokeyword.fragmentation.SupportActivity;
//秒开，替换系统主题
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            public void run() {
               Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

}
