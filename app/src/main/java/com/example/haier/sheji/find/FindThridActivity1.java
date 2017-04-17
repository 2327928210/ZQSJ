package com.example.haier.sheji.find;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.haier.sheji.R;
import com.example.haier.sheji.view.GlideCirclerTransform;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FindThridActivity1 extends AppCompatActivity {

    private TextView textView;
    private String stream;
    private String code;
    private String path;

    private String content2;
    private Map<String,Drawable> map = new HashMap<>();//存放图片的集合

    private ImageView imageView1,imageView2;
    private TextView textView1,textView2,textView3,textView4;
    private String img_src;
    private String title;
    private String pubtime;
    private String nickname;
    private String head_img;
    private String fcate_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_thrid1);

        textView= (TextView) findViewById(R.id.find_3ji_text);
        imageView1= (ImageView) findViewById(R.id.find_3ji1_image1);
        imageView2= (ImageView) findViewById(R.id.find_3ji1_image2);
        textView1= (TextView) findViewById(R.id.find_3ji1_text1);
        textView2= (TextView) findViewById(R.id.find_3ji1_text2);
        textView3= (TextView) findViewById(R.id.find_3ji1_text3);
        textView4= (TextView) findViewById(R.id.find_3ji1_text4);

        Intent intent=getIntent();
        stream = intent.getStringExtra("stream");
        code = intent.getStringExtra("ack");
        path="http://app.lerays.com/api/stream/view?_ack="+code+"&stream_id="+stream;

        initHttp(path);
    }

    private void initHttp(String path) {

        StringRequest request= new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object=new JSONObject(response);

                    JSONObject object1=object.getJSONObject("data");

                    title=object1.getString("title");
                    img_src=object1.getString("img_src");
                    pubtime=object1.getString("pubtime");

                    JSONObject object2=object1.getJSONObject("user");
                    JSONObject object3=object1.getJSONObject("fcate");

                    nickname=object2.getString("nickname");
                    head_img=object2.getString("head_img");
                    fcate_title=object3.getString("title");

                    textView1.setText(fcate_title);
                    textView2.setText(title);
                    textView3.setText(nickname);
                    textView4.setText(pubtime);

                    Glide.with(FindThridActivity1.this).load(img_src).placeholder(R.mipmap.loading).into(imageView1);
                    Glide.with(FindThridActivity1.this).load(head_img).placeholder(R.mipmap.loading).transform(new GlideCirclerTransform(FindThridActivity1.this)).into(imageView2);


                    String content=object1.getString("content");
                    content2=content.replaceAll("data-src","src");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Spanned spanned= Html.fromHtml(content2, new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {

                        new  MyTask().execute(source);

                        return null;
                    }
                },null);

                textView.setText(spanned);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setTag("find_3ji1");
        Volley.newRequestQueue(this).add(request);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

       Volley.newRequestQueue(this).cancelAll("Find_3ji1");
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

//                    BitmapFactory.Options options=new BitmapFactory.Options();
//                    options.inJustDecodeBounds=true;
//                    //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    int hieght1= drawable.getMinimumHeight();
//                    int width1=drawable.getMinimumWidth();
//                    Display defaultDisplay = getWindowManager().getDefaultDisplay();
//                    int width2 = defaultDisplay.getWidth();
//                    int height2 = defaultDisplay.getHeight();
//                    int  w= width1 / width2;
//                    int  h= hieght1 / height2;
//                    int max= Math.max(w,h);
//
//                    options.inJustDecodeBounds=false;
//                    options.inSampleSize=max;
//
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
//                    drawable=new BitmapDrawable(bitmap);



//                    int widthPixels = getResources().getDisplayMetrics().widthPixels;
//                    int width=drawable.getIntrinsicWidth();
//                    int height=drawable.getIntrinsicHeight();
//
//                    float scale=width/height;
//                    int newHeight= (int) (widthPixels/scale);
//
//                    drawable.setBounds(0,0,widthPixels,newHeight);


                    drawable.setBounds(5,5,690,500);


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

            Spanned spanned = Html.fromHtml(content2, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {


                    Drawable drawable1 = map.get(source);

                    return drawable1;
                }
            }, null);

            textView.setText(spanned);

        }
    }


}
