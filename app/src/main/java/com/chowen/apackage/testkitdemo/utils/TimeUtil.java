package com.chowen.apackage.testkitdemo.utils;

import android.content.ContentResolver;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Created by zhouwen on 2017/4/10.
 */

public class TimeUtil {

    public static CharSequence getCurrent(Context context){

// 01 02 ---09  10-12   下午：1-12

        /////////
        //通过DateFormat获取系统的时间
       // String currentTime=DateFormat.format("yyyy-MM-dd hh-mm-ss", new Date());
//        currentTime="通过DateFormat获取的时间:\n"+currentTime;
//通过SimpleDateFormat获取24小时制时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
        String currentTime1 ="\n"+"通过SimpleDateFormat获取24小时制时间：\n"+sdf.format(new Date());
//通过SimpleDateFormat获取12小时制时间
        sdf=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss", Locale.getDefault());
        String currentTime ="\n"+"通过SimpleDateFormat获取12小时制时间：\n"+sdf.format(new Date());


L.e(currentTime+">>"+currentTime1);

        ///////////





        String sayHelloContent = "";
//        long currentTime = System.currentTimeMillis();
//        SimpleDateFormat df = new SimpleDateFormat("HH",Locale.getDefault());
//        CharSequence sequence = df.format(new Date());
//        Calendar cal = Calendar.getInstance();// 当前日期
//        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时

//        L.e("hour>>>"+hour);
//        if (hour >=6 && hour < 11 ){
//            sayHelloContent = "Hi,xx早上好！";
//        } else if (hour>=11 && hour < 13){
//            sayHelloContent = "Hi,xx中午好！";
//        } else if (hour >=13 && hour < 19){
//            sayHelloContent = "Hi,xx下午好！";
//        } else if (hour >=22 && hour < 2){
//            sayHelloContent = "晚安！xx";
//        } else if (hour >=2 && hour < 6){
//            sayHelloContent = "xxx,注意休息哦！";
//        }
        return sayHelloContent;
    }
}
