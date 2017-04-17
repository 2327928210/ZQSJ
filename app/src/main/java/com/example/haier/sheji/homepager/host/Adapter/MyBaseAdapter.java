package com.example.haier.sheji.homepager.host.Adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GHZ_PC on 2016/12/25.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> data;
    private Context context;

    public MyBaseAdapter( Context context) {
       data = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null?0:data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
public  void  addAll( List<T> dt){
    data.addAll(dt);
    notifyDataSetChanged();
}
    public  void  clear (){
    data.clear();
        notifyDataSetChanged();
}

}
