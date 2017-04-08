package com.example.haier.sheji;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by haier on 2017/4/8.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
