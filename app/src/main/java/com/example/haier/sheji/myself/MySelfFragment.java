package com.example.haier.sheji.myself;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.haier.sheji.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MySelfFragment extends Fragment {

    @InjectView(R.id.my_fragment_image1)
    ImageView myFragmentImage1;
    @InjectView(R.id.zhuye)
    RelativeLayout zhuye;
    @InjectView(R.id.xiaoxi)
    RelativeLayout xiaoxi;
    @InjectView(R.id.shangcheng)
    RelativeLayout shangcheng;
    @InjectView(R.id.qianbao)
    RelativeLayout qianbao;
    @InjectView(R.id.renwu)
    RelativeLayout renwu;
    @InjectView(R.id.shezhi)
    RelativeLayout shezhi;
    @InjectView(R.id.view)
    View view;

    public MySelfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_self, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.zhuye, R.id.xiaoxi, R.id.shangcheng, R.id.qianbao, R.id.renwu, R.id.shezhi, R.id.view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuye:
                break;
            case R.id.xiaoxi:
                break;
            case R.id.shangcheng:
                break;
            case R.id.qianbao:
                break;
            case R.id.renwu:
                break;
            case R.id.shezhi:
                break;
            case R.id.view:
                break;
        }
    }
}