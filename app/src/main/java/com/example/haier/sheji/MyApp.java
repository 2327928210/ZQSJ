package com.example.haier.sheji;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by haier on 2017/4/8.
 */

public class MyApp extends Application {

    private static MyApp app;
    private RequestQueue requestQueue;
    private Context context;
    
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = this;
        initVolley();
        AutoLayoutConifg.getInstance().useDeviceSize();
    }

    private void initVolley() {

        requestQueue = Volley.newRequestQueue(this);

    }

    public static MyApp getApp() {
        return app;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public Context getContext() {
        return context;
    }

}
