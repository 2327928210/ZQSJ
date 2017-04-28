package com.example.haier.sheji.hudong;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haier.sheji.Constants;
import com.example.haier.sheji.R;
import com.example.haier.sheji.homepager.host.Hot_Fragment_Second.Second_WebActivity;
import com.example.haier.sheji.homepager.host.bean.HotFragmentBean;
import com.example.haier.sheji.homepager.hudong.Adapter.InteractionFragmentAdapter;
import com.example.haier.sheji.url.MyUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HuDongFragment extends Fragment implements View.OnClickListener{


    private RecyclerView ifrecyclerView;
    private SwipeRefreshLayout ifswipeRefreshLayout;

    private List<HotFragmentBean> data;
    private View view;
    private LinearLayoutManager manager;
    private String url1;
    //------------6666-------------------------------------------------------------
    private boolean isLoading = false;  //false代表当前没有在加载
    //------------6666-------------------------------------------------------------
    private String nextsign;
    private String nexttime;
    private HotFragmentBean hotFragmentBean;
    private InteractionFragmentAdapter interactionFragmentAdapter;
    private int mCurrentPage = 1;
    private View headView;//头视图
    private View circularProgressBar;

    public HuDongFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();
        VollsyRequest(1);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hot, container, false);
        ifswipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_pull);
        ifrecyclerView = (RecyclerView) view.findViewById(R.id.HotFragment_RecyclerView);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ifrecyclerView.setLayoutManager(manager);
        interactionFragmentAdapter = new InteractionFragmentAdapter(data,getContext());
        interactionFragmentAdapter.setClickListener(this);
        ifrecyclerView.setAdapter(interactionFragmentAdapter);
        // setHeadView(ifrecyclerView);//添加头视图
        setListener();
        //******************************progressbar****************
        /*注意，我尝试路好几种方法，onCreate里面只创建一次progressbar已经无解，突然想起判断数据的方法，
        *frangment滑到第三页回到第一页会重走 onCreateView方法，这样会导致progressbary又被创建并显示，
        * 而适配器不会再刷新，progressbar会一直显示,为了解决这个问题，就需要判断当前fragment的集合里有没有数据
        * 有数据就把progressbar隐藏
        */
        circularProgressBar = view.findViewById(R.id.progressbar);
        if (circularProgressBar != null) {
            circularProgressBar.setVisibility(View.VISIBLE);
        }
        if (data.size()!=0){
            if (circularProgressBar != null) {
                circularProgressBar.setVisibility(View.GONE);
            }
        }
        //******************************progressbar****************
        return view;
    }
//    //添加头视图
//    private  void  setHeadView(RecyclerView view){
//        View header = LayoutInflater.from(getContext()).inflate(R.layout.interaction_fragment_recyclerview_head_item,view,false);
//
//        interactionFragmentAdapter.setmHeaderView(header);
//    }

    //监听事件
    @Override
    public void onClick(View v) {
        Integer tag = (Integer) v.getTag();
        if (tag!=null){
            Intent intent = new Intent(getActivity(), Second_WebActivity.class);
            intent.putExtra("query_string",data.get(tag).getQuery_string());
            startActivity(intent);
        }
    }
    private void setListener() {
        ifswipeRefreshLayout.setColorSchemeResources(R.color.blue,R.color.green,R.color.red,R.color.yellow);
        ifswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                VollsyRequest(1);
            }
        });
        ifrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == recyclerView.SCROLL_STATE_IDLE && manager.findLastVisibleItemPosition() == data.size() - 1 && !isLoading)
                    VollsyRequest(mCurrentPage);
                mCurrentPage++;
            }
        });
    }
    public void VollsyRequest(int page) {
        if (page == 1) {
            data.clear();
            StringRequest stringRequest = new StringRequest(StringRequest.Method.GET,
                    Constants.Home_Interaction_Fast + page,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsondata = jsonObject.getJSONObject("data");
                                //-----下一页数据-----
                                nexttime = jsondata.getString("nexttime");
                                nextsign = jsondata.getString("nextsign");
//Home_Interaction_Fast2
                                url1 = MyUrl.Home_Entertainmen_Fast1 + nexttime +MyUrl.Home_Entertainmen_Fast2+ nextsign + MyUrl.Home_Entertainmen_Fast3;
                                //-----下一页数据-----
                                JSONArray list = jsondata.getJSONArray("list");

                                for (int i = 0; i < list.length(); i++) {
                                    hotFragmentBean = new HotFragmentBean();
                                    JSONObject listItem = list.getJSONObject(i);
                                    String Id = listItem.getString("Id");
                                    String title = listItem.getString("title");
                                    String img_src = listItem.getString("img_src");
                                    String cate_title = listItem.getString("cate_title");
                                    String query_string = listItem.getString("query_string");
                                    hotFragmentBean.setId(Id);
                                    hotFragmentBean.setTitle(title);
                                    hotFragmentBean.setImg_src(img_src);
                                    hotFragmentBean.setCate_title(cate_title);
                                    hotFragmentBean.setQuery_string(query_string);
                                    JSONObject display0bject = listItem.getJSONObject("display");
                                    String value = display0bject.getString("value");
                                    hotFragmentBean.setValue(value);
                                    data.add(hotFragmentBean);
                                    Log.e("TAG", "互动数据: "+data );
                                }

                                interactionFragmentAdapter.notifyDataSetChanged();
                                if (circularProgressBar != null) {
                                    circularProgressBar.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Log.e("TAG", "onResponse: " + data);
                            ifswipeRefreshLayout.setRefreshing(false);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            mCurrentPage = page;
            mCurrentPage++;
            stringRequest.setTag("rquest");
            Volley.newRequestQueue(getContext()).add(stringRequest);
        } else {


//---------------------又重新加载一遍网络请求了，不过要改网址----------------------------------------------
            StringRequest stringRequest = new StringRequest(StringRequest.Method.GET,
                    url1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsondata = jsonObject.getJSONObject("data");

                                //-----下一页数据-----
                                JSONArray list = jsondata.getJSONArray("list");

                                for (int i = 0; i < list.length(); i++) {
                                    hotFragmentBean = new HotFragmentBean();
                                    JSONObject listItem = list.getJSONObject(i);
                                    String Id = listItem.getString("Id");
                                    String title = listItem.getString("title");
                                    String img_src = listItem.getString("img_src");
                                    String cate_title = listItem.getString("cate_title");
                                    String query_string = listItem.getString("query_string");
                                    hotFragmentBean.setId(Id);
                                    hotFragmentBean.setTitle(title);
                                    hotFragmentBean.setImg_src(img_src);
                                    hotFragmentBean.setCate_title(cate_title);
                                    hotFragmentBean.setQuery_string(query_string);
                                    JSONObject display0bject = listItem.getJSONObject("display");
                                    String value = display0bject.getString("value");
                                    hotFragmentBean.setValue(value);
                                    data.add(hotFragmentBean);
                                }
                                interactionFragmentAdapter.notifyDataSetChanged();

                                //-----下一页数据-----
                                nexttime = jsondata.getString("nexttime");
                                nextsign = jsondata.getString("nextsign");
                                url1 = MyUrl.Home_Entertainmen_Fast1 + nexttime + MyUrl.Home_Entertainmen_Fast2+ nextsign + MyUrl.Home_Entertainmen_Fast3;
                                //------------6666-------------------------------------------------------------
                                isLoading = false;
                                //------------6666-------------------------------------------------------------
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Log.e("TAG", "onResponse: " + data);
//                            swipeRefreshLayout.setRefreshing(false);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            //------------

            interactionFragmentAdapter.notifyDataSetChanged();
            if (circularProgressBar != null) {
                circularProgressBar.setVisibility(View.GONE);
            }

            //--------------
            stringRequest.setTag("rquest");
            Volley.newRequestQueue(getContext()).add(stringRequest);

//---------------------又重新加载一遍网络请求了，不过要改网址----------------------------------------------
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Volley.newRequestQueue(getContext()).cancelAll("rquest");
    }

}