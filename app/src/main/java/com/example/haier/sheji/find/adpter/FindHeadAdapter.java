package com.example.haier.sheji.find.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.haier.sheji.R;
import com.example.haier.sheji.find.bean.Find_headView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 */

public class FindHeadAdapter extends PagerAdapter {

    private Context context;
    private List<Find_headView> data;

    public FindHeadAdapter(Context context, List<Find_headView> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {

        View ret=null;
        ImageView imageView=new ImageView(context);

        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1,-1));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(data.get(position).getImg_src()).placeholder(R.mipmap.loading).into(imageView);
        ret=imageView;
        container.addView(ret);


        return ret;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if(object instanceof  View){
           View view= (View) object;
            container.removeView(view);
        }

    }
}
