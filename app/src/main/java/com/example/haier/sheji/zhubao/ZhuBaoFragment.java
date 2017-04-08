package com.example.haier.sheji.zhubao;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haier.sheji.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhuBaoFragment extends Fragment {


    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    RequestQueue requestQueue;
    private List<ShiTiLei.ContextBean> data;
    private RecyclerViewAdaptr recyclerViewAdaptr;
    private RecyclerViewGridViewAdapter recyclerViewGridViewAdapter;
    //加载的页数
    private int page = 0;

    //加载每页的条目数
    private int itemPage=10;

    //判断要加载哪套布局  默认记载列表样式
    private boolean flag=true;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    public ZhuBaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zhu_bao, container, false);
        ButterKnife.inject(this, view);

        data = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());

        init();

        swipeRefreshLayout.setColorSchemeColors(Color.CYAN, Color.DKGRAY, Color.GRAY);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                page = 0;
                httpUrl();
                shouQuan();
            }

        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == recyclerViewAdaptr.getItemCount()
                        && recyclerViewAdaptr.isShowFooter()) {

                    page++;
                    httpUrl();
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(flag){
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }else{
                    lastVisibleItem=gridLayoutManager.findLastVisibleItemPosition();
                }

            }
        });

        return view;
    }



    private void init() {

        if(flag){

            linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            data.clear();
            page=0;
            httpUrl();
            recyclerViewAdaptr = new RecyclerViewAdaptr(getContext(), data);
            recyclerView.setAdapter(recyclerViewAdaptr);

        }else {

            gridLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(gridLayoutManager);
            data.clear();
            page=0;
            httpUrl();
            recyclerViewGridViewAdapter=new RecyclerViewGridViewAdapter(getContext(),data);
            recyclerView.setAdapter(recyclerViewGridViewAdapter);
        }
    }

    private void httpUrl() {

        StringRequest request = new StringRequest("http://192.168.16.44:88/photo-album/search/recommend/?page=" + page + "&itemperpage=" + itemPage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                List<ShiTiLei> list = gson.fromJson(response, new TypeToken<List<ShiTiLei>>() {
                }.getType());
                List<ShiTiLei.ContextBean> context = list.get(0).getContext();
                Log.d("TTT", "response:\n" + response.toString() + "\nsize:" + String.valueOf(context.size()));

                if (context.size() < itemPage) {

                    if (flag) {
                        recyclerViewAdaptr.isShowFooter(false);
                    } else {
                        recyclerViewGridViewAdapter.isShowFooter(false);
                    }

                }

                if (context.size() == 0) {

                    Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                }

                for (ShiTiLei.ContextBean contextBean : context) {
                    data.add(contextBean);
                }
                Log.e("TAG", data.toString());

                if (flag) {
                    recyclerViewAdaptr.notifyDataSetChanged();
                } else {
                    recyclerViewGridViewAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "网络请求失败,请检查网络状况", Toast.LENGTH_SHORT).show();
                Log.e("TAG", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

        //设置请求取消标记
        request.setTag("rquqest");

        //将请求添加到请求队列
        requestQueue.add(request);

    }



    //点击切换布局按钮
    @OnClick(R.id.button)
    public void onViewClicked() {

        flag=!flag;
        init();

    }


    //收起swipeRefreshLayout刷新
    public void shouQuan() {

        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 关闭加载进度条
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }, 1000);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
