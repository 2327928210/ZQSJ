package com.example.haier.sheji.myself;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @InjectView(R.id.lianxi)
    RelativeLayout lianxi;
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

    @OnClick({R.id.zhuye, R.id.xiaoxi, R.id.shangcheng, R.id.lianxi, R.id.renwu, R.id.shezhi, R.id.view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuye:
                break;
            case R.id.xiaoxi:
                break;
            case R.id.shangcheng:
                break;
            case R.id.lianxi:

                //从底部拉出视频
                showDialog();

                break;
            case R.id.renwu:
                break;
            case R.id.shezhi:
                break;
            case R.id.view:
                break;
        }
    }

//    /**
//     * 设置添加屏幕的背景透明度
//     *
//     * @param bgAlpha
//     */
//    public void backgroundAlpha(float bgAlpha)
//    {
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        getWindow().setAttributes(lp);
//    }

    private Dialog dialog;
    private View view1;


    public void showDialog(){

        dialog=new Dialog(getContext(), R.style.ActionSheetDialogStyle);

        view1= LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout,null);

        //初始化控件
        TextView btn_web = (TextView) view1.findViewById(R.id.main_pop_web);
        TextView btn_tel = (TextView) view1.findViewById(R.id.main_pop_tel);
        TextView btn_address = (TextView) view1.findViewById(R.id.main_pop_address);
        TextView btn_close = (TextView) view1.findViewById(R.id.main_pop_cancel);


        //点击取消按钮对话框消失
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //对话框消失
                dialog.cancel();
            }
        });

        //将布局设置给Dialog
        dialog.setContentView(view1);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 设置底部宽度
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框

    }
}