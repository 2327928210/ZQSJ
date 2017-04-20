package com.example.haier.sheji.homepager.host.Hot_Fragment_Second;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.haier.sheji.MyApp;
import com.example.haier.sheji.R;
import com.example.haier.sheji.url.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SecondWebViewActivity extends AppCompatActivity {

    private String query_string;//第一页传来的数据，拼接之后才能当网址用
    private ImageView iv_img_src;//头部的照片
    private TextView tv_title;//标题
    private ImageView  iv_head_img;//头像
    private TextView   tv_nickname;//公众号的名字
    private TextView   tv_pubtime;//时间
    private TextView   tv_content;//内容
    private RequestQueue requestQueue;//网络加载
    private  String SecondUrl;//第二页的url
    private ImageLoader imageLoader;
    private String content;//内容，html的内容
    private int widthPixels;//获取屏幕宽度的像素
    private Spanned spanned;



    private Map<String,Drawable> map = new HashMap<>();//存放图片的集合
    private String contrnt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_web_view);
        initView();//初始化控件

        Intent intent = getIntent();
        query_string = intent.getStringExtra("query_string");
        SecondUrl = Constants.Home_Hot_Saecond + query_string;//拼接后的第二页url
        Log.e("TAG", "SecondUrl: " + SecondUrl);
        Log.e("TAG", "onClick:c成功接收第一页传来的数据 " + query_string);
        requestQueue = MyApp.getApp().getRequestQueue();//调用请求
        VollsyRequest();
        Log.e("TAG", "onCreate: content"+content );//context在这里还是空的,当初在这里写Html就导致空指针异常

    }
    private void initView() {
        iv_img_src = (ImageView) findViewById(R.id.Html_Hot_img_src);
        tv_title = (TextView) findViewById(R.id.Html_Hot_title);
        iv_head_img = (ImageView) findViewById(R.id.Html_Hot_head_img);
        tv_nickname = (TextView) findViewById(R.id.Html_Hot_nickname);
        tv_pubtime = (TextView) findViewById(R.id.Html_Hot_pubtime);
        tv_content = (TextView) findViewById(R.id.Html_Hot_content );
    }

    private void VollsyRequest() {
        StringRequest stringRequest =  new StringRequest(StringRequest.Method.GET,
                Constants.Home_Hot_Saecond+query_string,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //---------------开始解析啦————————————————————
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject Objectdata = jsonObject.getJSONObject("data");
                            String title = Objectdata.getString("title");//标题
                            tv_title.setText(title);
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
                            imageLoader.displayImage(img_src,iv_img_src,displayImageOptions);
                            //-----------------------------------------------------------------------------------------

                            String pubtime = Objectdata.getString("pubtime");//时间
                            tv_pubtime.setText(pubtime);
                            //=================================================================================================
                            //内容
                            content = Objectdata.getString("content");
                            contrnt2 = content.replaceAll("data-src","src");
                            Log.e("TAG", "content: "+content);
                            //使用Html进行标签的转换
                            //TODO　如果内部包含　img src 图片，那么默认会给图片的位置添加一个占位符！但是不会显示图片的内容
                            //TODO 要根绝String  source 图片的路径 获取网络上的图片， 并且返回
                            //1，此处是主线程 无法直接进行网络请求
                            //2，如果开启新的线程 异步操作 返回值不能等
                            spanned = Html.fromHtml(contrnt2, new Html.ImageGetter() {
                                @Override
                                public Drawable getDrawable(String source) {
                                    Log.e("TAG", "getDrawable: "+source + " 所在的线程："+Thread.currentThread().getId());
                                    //TODO 要根绝String  source 图片的路径 获取网络上的图片， 并且返回
                                    //1，此处是主线程 无法直接进行网络请求
                                    //2，如果开启新的线程 异步操作 返回值不能等
                                    // Log.e("TAG", "getDrawable: "+source );
//                                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//                                    drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                                    new MyTask().execute(source);
                                    return  null;
                                }
                            }, null);
                            tv_content.setText(spanned);
                            //=================================================================================================
                            //解析user里面的内容
                            JSONObject ObjectUser = Objectdata.getJSONObject("user");
                            String nickname = ObjectUser.getString("nickname");//公众号的名字
                            tv_nickname.setText(nickname);
                            tv_nickname.setText(nickname);
                            String head_img = ObjectUser.getString("head_img");//头像
                            //-----------设置图片
                            imageLoader = ImageLoader.getInstance();
                            DisplayImageOptions displayImageOptions2 = new DisplayImageOptions.Builder()
                                    .cacheInMemory(true)                //内存缓存
                                    .cacheOnDisk(true)                  //磁盘缓存
                                    .bitmapConfig(Bitmap.Config.RGB_565)//解码方式
                                    .resetViewBeforeLoading(true)       //加载前是否重置,为了放置图片错位
                                    //.showImageOnLoading(R.mipmap.ic_launcher) //默认图片
                                    // .showImageOnFail(R.mipmap.ic_launcher)    //失败图片
                                    .showImageForEmptyUri(R.mipmap.ic_launcher)  //url地址为空的时候显示的图片
                                    .imageScaleType(ImageScaleType.NONE) //缩放类型
                                    .displayer(new CircleBitmapDisplayer())//显示前的处理【CircleBitmapDisplayer,FadeInBitmapDisplayer,RoundedBitmapDisplayer】
                                    .build();
                            imageLoader.displayImage(head_img,iv_head_img,displayImageOptions2);
                            //---------------------------------
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse: "+error );
            }
        });
        stringRequest.setTag("request");
        requestQueue.add(stringRequest);
    }

    class  MyTask extends AsyncTask<String,Void,Drawable>
    {
        private String path;
        @Override
        protected Drawable doInBackground(String... params) {
            path = params[0];
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                httpURLConnection.setConnectTimeout(5*1000);
                if (httpURLConnection.getResponseCode() ==200)
                {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Drawable drawable = Drawable.createFromStream(inputStream,null);
                    //用不了
//                    int intrinsicWidth = drawable.getIntrinsicWidth();
//                    int intrinsicHeight = drawable.getIntrinsicHeight();
//
//                    float  scale = intrinsicWidth/(float)intrinsicHeight;
//                    int newHeight = (int) (widthPixels/scale);

                    drawable.setBounds(0,0,drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

                    return  drawable;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute( Drawable drawable) {
            super.onPostExecute(drawable);

            if (drawable != null) {
                map.put(path,drawable);
                Log.e("tag", "onPostExecute: "+map.size());
            }

            Spanned spanned = Html.fromHtml(contrnt2, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {

                    Drawable drawable1 = map.get(source);

                    return drawable1;
                }
            }, null);

            tv_content.setText(spanned);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll("request");
    }
}
