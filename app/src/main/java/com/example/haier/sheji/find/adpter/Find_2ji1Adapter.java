package com.example.haier.sheji.find.adpter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haier.sheji.R;
import com.example.haier.sheji.find.bean.Find_2ji1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class Find_2ji1Adapter extends BaseAdapter {
    private Context context;
    private List<Find_2ji1> data;
    private List<Integer> positions; //要改变字体颜色的位置

    public Find_2ji1Adapter(Context context, List<Find_2ji1> data) {
        this.context = context;
        this.data = data;
        this.positions = new ArrayList<>();
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
    public int getItemViewType(int position) {

        Find_2ji1 find_2ji1=data.get(position);

        if(find_2ji1.getType()==2){
            return  2;
        }else if(find_2ji1.getType()==1){
            return  1;
        }
            return 0;

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderFind2ji1 viewHolderFind2ji1=null;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);


            if(getItemViewType(position)==2) {
                viewHolderFind2ji1=new ViewHolderFind2ji1();
                convertView = inflater.inflate(R.layout.activity_find_2ji1_item3,parent,false);
                viewHolderFind2ji1.imageView= (ImageView) convertView.findViewById(R.id.find_2ji1_item3_image);

                viewHolderFind2ji1.textView1= (TextView) convertView.findViewById(R.id.find_2ji1_item3_text1);
                viewHolderFind2ji1.textView2= (TextView) convertView.findViewById(R.id.find_2ji1_item3_text2);
                Button button= (Button) convertView.findViewById(R.id.find_2ji1_button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                                View view1=LayoutInflater.from(context).inflate(R.layout.dialog_zidingyi_layout,null);
                                builder.setView(view1);
                                //builder.setIcon(R.mipmap.ic_launcher);
                                // builder.setTitle("登陆后才可以使用奥");
                                //  builder.setNegativeButton("再逛逛",null);
                                //  builder.setPositiveButton("去登陆",null);
                                TextView textCall= (TextView) view1.findViewById(R.id.dialog_call);
                                TextView textLogin= (TextView) view1.findViewById(R.id.dialog_login);
                                final Dialog dialog=builder.show();

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



            }else if(getItemViewType(position)==0){
                viewHolderFind2ji1=new ViewHolderFind2ji1();
                convertView = inflater.inflate(R.layout.activity_find_2ji1_item1,parent,false);
                viewHolderFind2ji1.imageView= (ImageView) convertView.findViewById(R.id.find_2ji1_item1_image);
                viewHolderFind2ji1.textView1= (TextView) convertView.findViewById(R.id.find_2ji1_item1_text1);
                viewHolderFind2ji1.textView2= (TextView) convertView.findViewById(R.id.find_2ji1_item1_text2);


            }else {
                viewHolderFind2ji1=new ViewHolderFind2ji1();
                convertView = inflater.inflate(R.layout.activity_find_2ji1_item2,parent,false);

                viewHolderFind2ji1.imageView= (ImageView) convertView.findViewById(R.id.find_2ji1_item2_image);
                viewHolderFind2ji1.textView1= (TextView) convertView.findViewById(R.id.find_2ji1_item2_text1);
                viewHolderFind2ji1.textView2= (TextView) convertView.findViewById(R.id.find_2ji1_item2_text2);
            }

            convertView.setTag(viewHolderFind2ji1);

        }else{
            viewHolderFind2ji1= (ViewHolderFind2ji1) convertView.getTag();

        }


        Find_2ji1 find_2ji1=data.get(position);


        Glide.with(context).load(find_2ji1.getImg_src()).placeholder(R.mipmap.loading).into(viewHolderFind2ji1.imageView);
        viewHolderFind2ji1.textView1.setText(find_2ji1.getTitle());

        boolean has = false;
        for (Integer integer : positions) {
            if (integer == position){
                has = true;
                break;
            }
        }

        int color=context.getResources().getColor(R.color.textcolor);
        int nocolor=context.getResources().getColor(R.color.notextcolor);

        if(find_2ji1.getType()==0) {
            if (has) {
                viewHolderFind2ji1.textView1.setTextColor(color);
            } else {
                viewHolderFind2ji1.textView1.setTextColor(nocolor);
            }
        }

        viewHolderFind2ji1.textView2.setText(find_2ji1.getVisit_num());

        return convertView;
    }

    class ViewHolderFind2ji1{

        ImageView imageView;
        TextView textView1,textView2;

    }
 
    public void addPosition(int index){
        int position = positions.indexOf(index);

        if (position>=0)
        {
           positions.remove(position);
        }else {
            positions.add(index);
        }
        notifyDataSetChanged();
    }
    
}

