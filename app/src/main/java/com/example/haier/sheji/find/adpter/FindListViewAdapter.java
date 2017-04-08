package com.example.haier.sheji.find.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haier.sheji.R;
import com.example.haier.sheji.find.bean.Find1;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class FindListViewAdapter extends BaseAdapter {
    private List<Find1> data;
    private Context context;

    public FindListViewAdapter(List<Find1> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Find1 getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Find1 find=data.get(position);
        if(find.getType()==0){

            return  0;

        }

            return 1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderFind viewHolderFind=null;

        if (convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);

             if(getItemViewType(position)==0) {
                 convertView = inflater.inflate(R.layout.find_listview_item0_layout, parent, false);

                 viewHolderFind = new ViewHolderFind();
                 convertView.setTag(viewHolderFind);

                 viewHolderFind.imageView1= (ImageView) convertView.findViewById(R.id.find_listview_item_imageview1);
                 viewHolderFind.textView1= (TextView) convertView.findViewById(R.id.find_listview_item_text1);
                 viewHolderFind.textView11= (TextView) convertView.findViewById(R.id.find_listview_item_text2);

             }else{

                 convertView = inflater.inflate(R.layout.find_listview_item_layout, parent, false);

                 viewHolderFind = new ViewHolderFind();
                 convertView.setTag(viewHolderFind);

                 viewHolderFind.imageView1= (ImageView) convertView.findViewById(R.id.find_listview_item_imageview3);
                 viewHolderFind.textView1= (TextView) convertView.findViewById(R.id.find_listview_item_text3);
                 viewHolderFind.textView11= (TextView) convertView.findViewById(R.id.find_listview_item_text4);

            }

        }else{

          viewHolderFind= (ViewHolderFind) convertView.getTag();

        }

        Find1 find1=data.get(position);

        if(find1.getType()==0) {
            Glide.with(context).load(find1.getImg_src()).placeholder(R.mipmap.loading).into(viewHolderFind.imageView1);
        }else{

            Glide.with(context).load(find1.getImg_src()).placeholder(R.mipmap.loading).into(viewHolderFind.imageView1);

        }

        //Picasso.with(context).load(find1.getImg_src()).into(viewHolderFind.imageView1);
        viewHolderFind.textView1.setText(find1.getTitle());
        viewHolderFind.textView11.setText(find1.getText());

        return convertView;
    }


    class ViewHolderFind{
        private ImageView imageView1;
        private TextView textView1;
        private TextView textView11;

    }


}
