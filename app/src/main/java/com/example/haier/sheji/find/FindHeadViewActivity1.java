package com.example.haier.sheji.find;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haier.sheji.Constants;
import com.example.haier.sheji.R;
import com.example.haier.sheji.find.adpter.FindHeadView2jiAdapter;
import com.example.haier.sheji.find.bean.FindHeadView2ji1;
import java.util.ArrayList;
import java.util.List;

public class FindHeadViewActivity1 extends Activity {

    private ListView listView;

    private FindHeadView2jiAdapter adapter;

    private List<FindHeadView2ji1> datas;

    private String url= Constants.Discover_find_head_2ji1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_head_view1);

        listView= (ListView) findViewById(R.id.find_headView_2ji1_listView);

        datas=new ArrayList<>();

        //设置空视图
        RelativeLayout empty= (RelativeLayout) findViewById(R.id.find_headview2ji_empty_layout);
        listView.setEmptyView(empty);

        adapter=new FindHeadView2jiAdapter(this,datas);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                datas.addAll(new FindHeadView2ji1().jsonParser(response));
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.setTag("request1");
        Volley.newRequestQueue(this).add(request);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Volley.newRequestQueue(this).cancelAll("request1");
    }
}