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
import com.example.haier.sheji.find.bean.FindHeadView2ji1;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class FindHeadView2jiAdapter extends BaseAdapter {

    private Context context;
    private List<FindHeadView2ji1> data;

    public FindHeadView2jiAdapter(Context context, List<FindHeadView2ji1> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderFindHeadView2ji viewHolderFindHeadView2ji=null;

        if(convertView==null){

            LayoutInflater inflater=LayoutInflater.from(context);

            convertView=inflater.inflate(R.layout.find_headview_2ji1_item_listview,parent,false);

            viewHolderFindHeadView2ji=new ViewHolderFindHeadView2ji(convertView);

            convertView.setTag(viewHolderFindHeadView2ji);

        }else {

            viewHolderFindHeadView2ji= (ViewHolderFindHeadView2ji) convertView.getTag();

        }

        FindHeadView2ji1 findHeadView2ji1=data.get(position);

        Glide.with(context).load(findHeadView2ji1.getImg_src()).placeholder(R.mipmap.loading).into(viewHolderFindHeadView2ji.imageView);
               viewHolderFindHeadView2ji.textView1.setText(findHeadView2ji1.getTag()+"");

               viewHolderFindHeadView2ji.textView2.setText(findHeadView2ji1.getTitle());

               viewHolderFindHeadView2ji.textView3.setText(findHeadView2ji1.getCate_title());

        if(Integer.parseInt(findHeadView2ji1.getValue())>=10000){

            viewHolderFindHeadView2ji.textView4.setText(Integer.parseInt(findHeadView2ji1.getValue())/10000+"万+");

        }else {
            viewHolderFindHeadView2ji.textView4.setText(findHeadView2ji1.getValue());
        }


        return convertView;
    }

    class ViewHolderFindHeadView2ji{

        ImageView imageView;
        TextView textView1,textView2,textView3,textView4;

        public ViewHolderFindHeadView2ji(View convertView){

            imageView= (ImageView) convertView.findViewById(R.id.find_headView_2ji1_imageview1);
            textView1= (TextView) convertView.findViewById(R.id.find_headView_2ji1_text2);
            textView2= (TextView) convertView.findViewById(R.id.find_headView_2ji1_text3);
            textView3= (TextView) convertView.findViewById(R.id.find_headView_2ji1_text4);
            textView4= (TextView) convertView.findViewById(R.id.find_headView_2ji1_text5);

        }


    }

}
