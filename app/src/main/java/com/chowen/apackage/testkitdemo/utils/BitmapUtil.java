package com.chowen.apackage.testkitdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Copyright (c) 2017.  All rights reserved.
 * Created by zhouwen on 2017/3/27.
 */

public class BitmapUtil {

    public void saveToFile(Bitmap bitmap, String path){
        try {
            FileOutputStream fo = new FileOutputStream(new File(path));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fo);
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmap(String path){
        return BitmapFactory.decodeFile(path);
    }


}
