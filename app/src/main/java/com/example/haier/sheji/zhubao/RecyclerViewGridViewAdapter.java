package com.example.haier.sheji.zhubao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haier.sheji.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Window 7 on 2017/4/1.
 */

public class RecyclerViewGridViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ShiTiLei.ContextBean> data;

    //设置底部布局
    private static final int TYPE_FOOTER = 0;
    //设置默认布局
    private static final int TYPE_DEFAULT = 1;

    //判断是不是最后一个item，默认是true
    private boolean mShowFooter = true;

    public RecyclerViewGridViewAdapter(Context context, List<ShiTiLei.ContextBean> data) {
        this.context = context;
        this.data = data;
    }

    //多布局
    @Override
    public int getItemViewType(int position) {


        if (!mShowFooter) {
            return TYPE_DEFAULT;
        }
        // 判断当前位置+1是不是等于数据总数（因为数组从0开始计数），是的就加载底部布局刷新，不是就加载默认布局
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_DEFAULT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == TYPE_DEFAULT) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_gridview_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            return new RecyclerViewAdaptr.ViewHolder_Foot(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            ShiTiLei.ContextBean bean = data.get(position);
            if (data == null) {
                return;
            }
            ViewHolder viewHolder = (ViewHolder) holder;
            Glide.with(context).load("http://192.168.16.44:88/photo-album/image/" + bean.getImg()).placeholder(R.mipmap.default_img).into(viewHolder.itemImage);
            viewHolder.text1.setText(bean.getNumber());
            viewHolder.text2.setText(bean.getName());

        }

    }

    @Override
    public int getItemCount() {
        // 判断是不是显示底部，是就返回1，不是返回0
        int begin = mShowFooter ? 1 : 0;
        // 没有数据的时候，直接返回begin
        if (data == null) {
            return begin;
        }
        // 因为底部布局要占一个位置，所以总数目要+1
        return data.size() + begin;
    }

    // 设置是否显示底部加载提示（将值传递给全局变量）
    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    // 判断是否显示底部，数据来自全局变量
    public boolean isShowFooter() {
        return this.mShowFooter;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_image)
        ImageView itemImage;
        @InjectView(R.id.text1)
        TextView text1;
        @InjectView(R.id.text2)
        TextView text2;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            AutoUtils.autoSize(itemView);
        }
    }

    static class ViewHolder_Foot extends RecyclerView.ViewHolder {
        @InjectView(R.id.progressBar)
        ProgressBar progressBar;
        @InjectView(R.id.last_layout)
        RelativeLayout lastLayout;

        public ViewHolder_Foot(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            AutoUtils.autoSize(itemView);
        }
    }
}
