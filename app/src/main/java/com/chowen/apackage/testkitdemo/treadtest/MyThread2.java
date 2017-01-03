package com.chowen.apackage.testkitdemo.treadtest;

/**
 * Created by zhouwen on 2017/1/2.
 * Description:
 */

public class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.activeCount() + "thread======BBB");
        }
    }
}
