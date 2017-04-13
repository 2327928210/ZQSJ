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

    public static MyApp instance;
    private RequestQueue requestQueue;
    private Context context;
    
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        AutoLayoutConifg.getInstance().useDeviceSize();
    }

    /**
     * 获取请求堆栈
     *
     * @return
     */
    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public static MyApp getApp() {
        return instance;
    }



    public Context getContext() {
        return context;
    }

}
