package com.example.haier.sheji.homepager.host.Hot_Fragment_Second;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.haier.sheji.MyApp;
import com.example.haier.sheji.R;
import com.example.haier.sheji.homepager.host.ProgressBar.CircularProgress;
import com.example.haier.sheji.url.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import org.json.JSONException;
import org.json.JSONObject;

public class Second_WebActivity extends AppCompatActivity {

    private ImageView iamge;//头部视图
    private RequestQueue requestQueue;//网络请求
    private String secondUrl;//上一级传来的url
    private  ImageLoader imageLoader;//图片框架
    private String secondUrl2;
    private String secondUrl3;
    private WebView webView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case  1:
                    webViewGoBack();
                    break;
            }
        }
    };
    private CircularProgress circularProgressBar;

    private void webViewGoBack() {
        webView.goBack();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__web);
        iamge = (ImageView) findViewById(R.id.Html_Hot_img_src);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scoll_view);
        circularProgressBar = (CircularProgress) findViewById(R.id.progressbar);
        //__________布局加载webView____________________________
        //  webView = (WebView) findViewById(R.id.webView);
        /***********************************************************************/
        //++++++++++++++++++++new webWiew+++++++++
        webView = new WebView(this);
        setContentView(webView);
        if (circularProgressBar != null) {
            circularProgressBar.setVisibility(View.VISIBLE);
        }
        //++++++++++++++++++++new webWiew+++++++++
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSet = webView.getSettings();
        webSet.setSupportZoom(true); // 支持缩放
        webSet.setAllowFileAccess(true); // 设置可以访问文件
        webSet.setJavaScriptEnabled(true); // 启用JavaScript
        webSet.setBlockNetworkImage(false); // 限制网络图片
        webSet.setBuiltInZoomControls(true); // 控制页面缩放
        webSet.setLoadWithOverviewMode(true); // 设置webview加载的页面的模式，
        webSet.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM); // 设置默认的缩放级别
        webSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //__________布局加载webView____________________________

        //接收传入的数据
        Intent intent = getIntent();
        String query_string = intent.getStringExtra("query_string");
        //拼接后的第二页url
        secondUrl = Constants.Home_Hot_Saecond + query_string;//这个是用来获取头部的图片
        Log.e("TAG", "onCreate:app网址 "+secondUrl);
        //这个是WebView的网址
        secondUrl2 = Constants.Home_Hot_Saecond2+query_string;
        //webview的网址得重新拼接
        secondUrl3 = secondUrl2.replace("amp;","");
        Log.e("TAG", "WebView实际网址: "+ secondUrl3);
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webView.loadUrl(secondUrl3);

        //设置Web视图

        /*********************************************************************************/
        /**
         * 如果希望点击链接由自己处理，而不是新开Android的系统browser中响应该链接。
         * 给WebView加一个事件监听对象（WebViewClient)并重写其中的一些方法：
         * shouldOverrideUrlLoading：对网页中超链接按钮的响应。
         * 当按下某个连接时WebViewClient会调用这个方法，并传递参数：按下的url。
         */
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);   //在当前的webview中跳转到新的url

                return true;
            }
        });
        /*********************************************************************************/

        /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/
        /**
         * 可以返回Fragment
         */
        webView.setOnKeyListener(new View.OnKeyListener() {
            //设置回退
            //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                    // webView.goBack(); //goBack()表示返回WebView的上一页面
                    handler.sendEmptyMessage(1);
                    return true;
                }

                return false;
            }
        });
        /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/
        //volley请求
        requestQueue = MyApp.getApp().getRequestQueue();
        VolleyRequest();
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //   Log.e(TAG, "pull_to_refresh_view====>onTouch");
                webView.onTouchEvent(event);
                return false;
            }
        });
    }

    private void VolleyRequest() {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, secondUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONObject Objectdata = jsonObject.getJSONObject("data");
                            String img_src = Objectdata.getString("img_src");//头部的照片
                            //-----------设置图片--------------------------------------------------------------
                            imageLoader = ImageLoader.getInstance();
                            DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                                    .cacheInMemory(true)                //内存缓存
                                    .cacheOnDisk(true)                  //磁盘缓存
                                    .bitmapConfig(Bitmap.Config.RGB_565)//解码方式
                                    .resetViewBeforeLoading(true)       //加载前是否重置,为了放置图片错位
                                    //.showImageOnLoading(R.mipmap.ic_launcher) //默认图片
                                    // .showImageOnFail(R.mipmap.ic_launcher)    //失败图片
                                    .showImageForEmptyUri(R.mipmap.ic_launcher)  //url地址为空的时候显示的图片
                                    .imageScaleType(ImageScaleType.NONE) //缩放类型
                                    //.displayer(new FadeInBitmapDisplayer(3*1000))//显示前的处理【CircleBitmapDisplayer,FadeInBitmapDisplayer,RoundedBitmapDisplayer】
                                    .build();
                            imageLoader.displayImage(img_src,iamge,displayImageOptions);
                            //-----------------------------------------------------------------------------------------

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        stringRequest.setTag("request");
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll("request");
    }
}