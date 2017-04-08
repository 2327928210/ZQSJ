package com.example.haier.sheji.find;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haier.sheji.Constants;
import com.example.haier.sheji.R;
import com.example.haier.sheji.find.adpter.FindHeadAdapter;
import com.example.haier.sheji.find.adpter.FindListViewAdapter;
import com.example.haier.sheji.find.bean.Find1;
import com.example.haier.sheji.find.bean.Find_headView;
import com.example.haier.sheji.view.CircleIndicator;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class FindFragment extends Fragment {

    private PullToRefreshListView listView;
    private List<Find1> datas;

    private FindListViewAdapter adapter;
    private String http;

    private String nexttime;
    private String nextsign;

    private View headView;
    private String headUrl = Constants.Discover_find_head;
    private ViewPager viewPager;
    int curNum = 0;//ViewPager页码
    private List<Find_headView> list;

    private float xDown;

    public FindFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datas = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        listView = (PullToRefreshListView) view.findViewById(R.id.find_fragment_listView);

        RelativeLayout emptyLayout = (RelativeLayout) view.findViewById(R.id.find_fragment_empty_layout);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setEmptyView(emptyLayout);
        Log.i("haha", "------------------------------------p000000000000");

        adapter = new FindListViewAdapter(datas, getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        http = Constants.Discover_Fast;
        requestDownLoad(http);

        //添加头视图
        headView = LayoutInflater.from(getContext()).inflate(R.layout.find_listview_headview, null);
        listView.getRefreshableView().addHeaderView(headView);

        //todo 点击更多订阅弹出对话框
        TextView moreText = (TextView) headView.findViewById(R.id.find_headview_text3);
        moreText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_zidingyi_layout, null);
                builder.setView(view1);
                //builder.setIcon(R.mipmap.ic_launcher);
                // builder.setTitle("登陆后才可以使用奥");
                //  builder.setNegativeButton("再逛逛",null);
                //  builder.setPositiveButton("去登陆",null);
                TextView textCall = (TextView) view1.findViewById(R.id.dialog_call);
                TextView textLogin = (TextView) view1.findViewById(R.id.dialog_login);
                final Dialog dialog = builder.show();

                textCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                textLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        });
        if (headUrl != null) {
            addHeadView(headUrl);
        }


        //listView点击事件进入二级页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Find1 find1 = datas.get(position - 2);

                if (find1.getType() == 0) {
                    String tag = find1.getTag_id();
                    Intent intent = new Intent(getContext(), Find_Second_Activity1.class);
                    intent.putExtra("id", tag);
                    startActivity(intent);

                } else if (find1.getType() == 1) {
                    String tag = find1.getTag_id();
                    String ack_code = find1.getAck_code();
                    Intent intent = new Intent(getContext(), Find_Second_Activity2.class);
                    intent.putExtra("stream_id", tag);
                    intent.putExtra("code", ack_code);
                    startActivity(intent);

                }

            }
        });


        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {

                datas.clear();
                http = Constants.Discover_Fast;
                requestDownLoad(http);

            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase refreshView) {

                Log.i("haha", "------------------------------------");
                http = "http://app.lerays.com/api/user/subscription/streams?pubtime=" + nexttime + "&nextsign=" + nextsign;
                requestDownLoad(http);

            }
        });


        return view;
    }


    //添加头视图
    private void addHeadView(String headUrl) {

        list = new ArrayList<>();

        viewPager = (ViewPager) headView.findViewById(R.id.find_headView_viewPager);

        //ViewPager点击事件，点击不同的图片进入不同的二级页面
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        float xUp = event.getX();
                        if (Math.abs(xUp - xDown) < 10) {
                            int currentItem = viewPager.getCurrentItem();
                            //  Toast.makeText(getContext(), "CURRENT:"+currentItem, Toast.LENGTH_SHORT).show();

                            Log.i("haha", "---------------------------");
                            if (currentItem % 3 == 0) {
                                Intent intent = new Intent(getContext(), FindHeadViewActivity1.class);
                                startActivity(intent);

                            } else if (currentItem % 3 == 1) {
                                Intent intent = new Intent(getContext(), FindHeadViewActivity2.class);
                                startActivity(intent);

                            } else if (currentItem % 3 == 2) {
                                Intent intent = new Intent(getContext(), FindHeadViewActivity3.class);
                                startActivity(intent);

                            }
                        }

                        break;

                }

                return false;
            }
        });

        //ViewPager实现左右无限滑动
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curNum = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (curNum == list.size() - 1 && state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.setCurrentItem(2, false);

                } else if (curNum == 0 && state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.setCurrentItem(3, false);
                }

            }
        });

        //ViewPage实现自动滑动
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (viewPager.getCurrentItem() == list.size() - 1) {
                                viewPager.setCurrentItem(3);
                            } else {
                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            }
                        }
                    });
                }


            }

        }, 2000, 4000);

        StringRequest request = new StringRequest(headUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);

                            JSONArray array = object.getJSONArray("data");

                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < array.length(); j++) {

                                    JSONObject object2 = array.getJSONObject(j);

                                    String img_src = object2.getString("img_src");

                                    Find_headView find_headView = new Find_headView();
                                    find_headView.setImg_src(img_src);
                                    list.add(find_headView);

                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        FindHeadAdapter findHeadAdapter = new FindHeadAdapter(getContext(), list);

                        viewPager.setAdapter(findHeadAdapter);

                        //圆形指示器显示
                        CircleIndicator indicator = (CircleIndicator) headView.findViewById(R.id.find_headView_indicator);
                        indicator.setIndicatorMode(CircleIndicator.Mode.OUTSIDE);  //设置圆形指示器模式
                        indicator.setIndicatorRadius(5);  //设置指示器圆的大小
                        indicator.setIndicatorMargin(25);  //设置圆之间的间隔
                        indicator.setViewPager(viewPager, list.size() / 2);   //圆形指示器和ViewPager相结合

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.setTag("headView");
        Volley.newRequestQueue(getContext()).add(request);
        //MyApp.getApp().getRequestQueue().add(request);


    }

    private void requestDownLoad(String url) {

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        datas.addAll(new Find1().jsonParser(response));
                        adapter.notifyDataSetChanged();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                            nexttime = jsonObject1.getString("nexttime");
                            nextsign = jsonObject1.getString("nextsign");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listView.onRefreshComplete();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listView.onRefreshComplete();
                    }
               });

        stringRequest.setTag("request");
        Volley.newRequestQueue(getContext()).add(stringRequest);
       // MyApp.getApp().getRequestQueue().add(stringRequest);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Volley.newRequestQueue(getContext()).cancelAll("request");
        Volley.newRequestQueue(getContext()).cancelAll("headView");
    }

}
