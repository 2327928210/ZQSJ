package com.example.haier.sheji.homepager.host;


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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haier.sheji.Constants;
import com.example.haier.sheji.R;
import com.example.haier.sheji.homepager.host.Adapter.HotFragmentAdapter;
import com.example.haier.sheji.homepager.host.bean.HotFragmentBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HostFragment extends Fragment {


    private RequestQueue requestQueue;//网络加载
    private RecyclerView recyclerView;
    private List<HotFragmentBean> data;//存放适配器的数据

    private HotFragmentBean hotFragmentBean;

    private HotFragmentAdapter hotFragmentAdapter;
    private LinearLayoutManager manager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  int mCurrentPage = 1;
    private  int Page;
    private String url1;
    //------------6666-------------------------------------------------------------
    private boolean isLoading = false;  //false代表当前没有在加载
    //------------6666-------------------------------------------------------------
    private  String nextsign;
    private String nexttime;

    public HostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();

        // VollsyRequest(mCurrentPage);
        VollsyRequest(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.HotFragment_RecyclerView);
        //下拉加载更多
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout_pull);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager);
        hotFragmentAdapter = new HotFragmentAdapter(getContext(),data);
        recyclerView.setAdapter(hotFragmentAdapter);
        setListener();//下拉刷新监听
        return view;
    }

    private void setListener() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                VollsyRequest(1);

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //------------6666-------------------------------------------------------------
                if (newState == recyclerView.SCROLL_STATE_IDLE&&manager.findLastVisibleItemPosition()==data.size()-1&&!isLoading){
                    Log.e("tag", "onScrollStateChanged: "+mCurrentPage);
                    VollsyRequest(mCurrentPage);
                    mCurrentPage++;
                    //------------6666-------------------------------------------------------------
                }
            }
        });
    }
    //public void  LoadData(){
//}
    public void VollsyRequest( int page) {
        if (page == 1) {
            data.clear();
            StringRequest stringRequest = new StringRequest(StringRequest.Method.GET,
                    Constants.Home_Hot_Fast+page,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                        HotFragmentBean hotFragmentBean = new HotFragmentBean();
//                       Gson gson = new Gson();
//                        HotFragmentBean hotFragmentBean = gson.fromJson(response, HotFragmentBean.class);
//                          hotFragmentBean.;

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsondata = jsonObject.getJSONObject("data");
                                //-----下一页数据-----
                                nexttime = jsondata.getString("nexttime");
                                nextsign = jsondata.getString("nextsign");
//                                nextPageBean = new NextPageBean();
//                                nextPageBean.setNexttime(nexttime);
//                                nextPageBean.setNextsign(nextsign);
//                                nextpage.add(nextPageBean);
                                url1 = Constants.Home_Hot_Fast1+nexttime+Constants.Home_Hot_Fast2+nextsign;
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

                                hotFragmentAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Log.e("TAG", "onResponse: " + data);
                            swipeRefreshLayout.setRefreshing(false);
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
        }else{



//---------------------又重新加载一遍网络请求了，不过要改网址----------------------------------------------
            StringRequest stringRequest = new StringRequest(StringRequest.Method.GET,
                    url1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                        HotFragmentBean hotFragmentBean = new HotFragmentBean();
//                       Gson gson = new Gson();
//                        HotFragmentBean hotFragmentBean = gson.fromJson(response, HotFragmentBean.class);
//                          hotFragmentBean.;

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject jsondata = jsonObject.getJSONObject("data");

//                                nextPageBean = new NextPageBean();
//                                nextPageBean.setNexttime(nexttime);
//                                nextPageBean.setNextsign(nextsign);
//                                nextpage.add(nextPageBean);
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
                                hotFragmentAdapter.notifyDataSetChanged();

                                //-----下一页数据-----
                                nexttime = jsondata.getString("nexttime");
                                nextsign = jsondata.getString("nextsign");
                                url1 = Constants.Home_Hot_Fast1+nexttime+Constants.Home_Hot_Fast2+nextsign;
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

            //  hotFragmentAdapter.notifyDataSetChanged();

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



