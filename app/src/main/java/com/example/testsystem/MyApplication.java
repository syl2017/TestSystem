package com.example.testsystem;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * @author syl
 * @time 2019/12/26 14:00
 * @detail
 */
public class MyApplication extends Application {
    private  static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext() {
        return context;
    }
}
