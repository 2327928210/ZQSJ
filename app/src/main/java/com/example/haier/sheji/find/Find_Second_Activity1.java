package com.example.haier.sheji.find;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haier.sheji.R;
import com.example.haier.sheji.find.adpter.Find_2ji1Adapter;
import com.example.haier.sheji.find.bean.Find_2ji1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Find_Second_Activity1 extends Activity {

    private ListView listView;
    private Find_2ji1Adapter adapter;
    private List<Find_2ji1> datas;

    private String http="http://app.lerays.com/api/stream/tag/slist?pubtime=null&nextsign=null&tag_id=";

    private String nexttime;
    private String nextsign;
    private String id;
    private boolean isLast=false;
    private String title;
    private String img_src;
    private String visit_num;
    private int type=0;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__seond_1);

        datas=new ArrayList<>();

        final Intent intent=getIntent();
        id=intent.getStringExtra("id");
        http=http+id;
        initHttp(http);
        listView= (ListView) findViewById(R.id.find_2ji1_listView);

        //Todo listView 点击事件，点击后进入三级页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Find_2ji1 find_2ji1=datas.get(position);

                if(find_2ji1.getType()==0){
                    //listView.setSelection(position);
//                      TextView text= (TextView) parent.getChildAt(position).findViewById(R.id.find_2ji1_item1_text1);
//
//                      int color=getResources().getColor(R.color.textcolor);
//                      text.setTextColor(color);
                    adapter.addPosition(position);
                    adapter.notifyDataSetChanged();

                    String stream_id=find_2ji1.getStream_id();
                    String ack_code=find_2ji1.getAck_code();
                    Intent intent1=new Intent(Find_Second_Activity1.this,FindThridActivity1.class);
                    intent1.putExtra("stream",stream_id);
                    intent1.putExtra("ack",ack_code);
                    startActivity(intent1);

                }else if(find_2ji1.getType()==1){
                    String stream_id=find_2ji1.getStream_id();
                    String ack_code=find_2ji1.getAck_code();
                    Intent intent2=new Intent(Find_Second_Activity1.this,FindThridActivity2.class);
                    intent2.putExtra("stream2",stream_id);
                    intent2.putExtra("ack2",ack_code);

                    startActivity(intent2);

                }
            }
        });

        //设置空视图
        RelativeLayout empty= (RelativeLayout) findViewById(R.id.find_2ji1_empty_layout);
        listView.setEmptyView(empty);

        adapter=new Find_2ji1Adapter(this,datas);
        listView.setAdapter(adapter);
        // adapter.notifyDataSetChanged();


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if(flag && isLast && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    flag = false;
                    http="http://app.lerays.com/api/stream/tag/slist?pubtime="+nexttime+"&nextsign="+nextsign+"&tag_id="+id;
                    secondHttp(http);
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                isLast = (firstVisibleItem+ visibleItemCount)==totalItemCount;

            }
        });


    }


    public void initHttp( String url){

        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                datas.addAll(new Find_2ji1().jsonParser(response));
                adapter.notifyDataSetChanged();

                try {
                    JSONObject object=new JSONObject(response);

                    JSONObject object1=object.getJSONObject("data");

                    nexttime=object1.getString("nexttime");
                    nextsign=object1.getString("nextsign");
                    flag=true;


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                flag=true;
            }
        });

        request.setTag("requestFind2ji1");
        Volley.newRequestQueue(this).add(request);

    }


    public void secondHttp(String url){

        StringRequest headRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                flag=true;
                try {
                    JSONObject object=new JSONObject(response);

                    JSONObject object1=object.getJSONObject("data");

                    nexttime=object1.getString("nexttime");
                    nextsign=object1.getString("nextsign");

                    JSONArray array=object1.getJSONArray("list");
                    for(int i=0;i<array.length();i++){

                        JSONObject object3=array.getJSONObject(i);

                        title=object3.getString("title");
                        img_src=object3.getString("img_src");
                        visit_num=object3.getString("visit_num");

                        if(i%4==3){
                            type=1;
                        }else {
                            type=0;
                        }

                        datas.add(new Find_2ji1(title,img_src,visit_num,type));

                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        headRequest.setTag("secondRequest");
        Volley.newRequestQueue(this).add(headRequest);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       Volley.newRequestQueue(this).cancelAll("requestFind2ji1");
        Volley.newRequestQueue(this).cancelAll("secondRequest");
    }
}
