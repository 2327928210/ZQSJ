package com.example.haier.sheji.homepager.host.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.example.haier.sheji.R;
import com.example.haier.sheji.homepager.host.bean.HotFragmentBean;

import java.util.List;

/**
 * Created by GHZ_PC on 2016/12/22.
 */

public class HotFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ONE_ITEM = 1;
    private static final int TWO_ITEM = 2;
    private static final int THREE_ITEM = 3;
    private static final int FOYR_ITEM = 4;
    private static final int FIVE_ITEM = 5;
    private Context context;
    private List<HotFragmentBean> data;
    private ImageLoader imageLoader;
    private View.OnClickListener mClickListener;
 //   private View.OnLongClickListener mLongClickListener;//长点击监听
    private OneViewHolder holder1;
    private TwoViewHolder holder2;
    private TwoViewHolder holder3;
    private TwoViewHolder holder4;
    private TwoViewHolder holder5;


    public HotFragmentAdapter(Context context, List<HotFragmentBean> data/*View.OnClickListener listener*/) {
        this.context = context;
        this.data = data;
        //mClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case ONE_ITEM:
                View view = LayoutInflater.from(context).inflate(R.layout.home_fragment_recyclerview_one_item,parent,false);
                holder = new OneViewHolder(view);
            break;
            case  TWO_ITEM:
                View view1 = LayoutInflater.from(context).inflate(R.layout.home_fragment_recyclerview_two_item,parent,false);
                holder = new TwoViewHolder(view1);
                break;
            case  THREE_ITEM:
                View view2 = LayoutInflater.from(context).inflate(R.layout.home_fragment_recyclerview_two_item,parent,false);
                holder = new TwoViewHolder(view2);
                break;
            case  FOYR_ITEM:
                View view3 = LayoutInflater.from(context).inflate(R.layout.home_fragment_recyclerview_two_item,parent,false);
                holder = new TwoViewHolder(view3);
                break;
            case  FIVE_ITEM:
                View view4 = LayoutInflater.from(context).inflate(R.layout.home_fragment_recyclerview_two_item,parent,false);
                holder = new TwoViewHolder(view4);
                break;
        }
        return holder;
    }

    public  void  setClickListener(View.OnClickListener listener)
    {

        mClickListener = listener;
}
   /* //设置长按点击事件监听
    public  void setLongClickLinstener(View.OnLongClickListener listener ){
        mLongClickListener = listener;
    }*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       int type = getItemViewType(position);
        HotFragmentBean hotFragmentBean = data.get(position);
        switch (type){
            case ONE_ITEM:
                holder1 = (OneViewHolder) holder;
                   holder1.Title.setText(hotFragmentBean.getTitle());
                   holder1.Cate_Title.setText(hotFragmentBean.getCate_title());
                   holder1.Visit_Num.setText(hotFragmentBean.getValue());

                Glide.with(context).load(hotFragmentBean.getImg_src()).placeholder(R.mipmap.loading).into(holder1.Image_Src);
                //填数据
                //item点击事件----------------------
               // holder1.Image_Src.setTag("holder1");
//                holder1.Image_Src.setTag(position);
//                holder1.Title.setTag(position);
                if (mClickListener!=null) {
                    holder1.Image_Src.setOnClickListener(mClickListener);
                    holder1.Title.setOnClickListener(mClickListener);
                }
                //长点击监听
                /*
                if (mLongClickListener!=null){
                    holder1.Image_Src.setOnLongClickListener(mLongClickListener);
                }*/
                /*//第一种通过匿名内部类，成功了
                holder1.Image_Src.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("TAG", "onClick: 点击了"+position);
                    }
                });
                */
                //item点击事件----------------------
                break;
            case TWO_ITEM:
                TwoViewHolder holder2 = (TwoViewHolder) holder;
                //填第二个的数据

                holder2.Title2.setText(hotFragmentBean.getTitle());
                holder2.Cate_Title2.setText(hotFragmentBean.getCate_title());
                holder2.Visit_Num2.setText(hotFragmentBean.getValue());

                Glide.with(context).load(hotFragmentBean.getImg_src()).placeholder(R.mipmap.loading).into(holder2.Image_Src2);
                break;
            //++++++++++++++++++=+++++++第三item复用第二个++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=

            case THREE_ITEM:
                TwoViewHolder holder3 = (TwoViewHolder) holder;
                //填第二个的数据

                holder3.Title2.setText(hotFragmentBean.getTitle());
                holder3.Cate_Title2.setText(hotFragmentBean.getCate_title());
                holder3.Visit_Num2.setText(hotFragmentBean.getValue());

                Glide.with(context).load(hotFragmentBean.getImg_src()).placeholder(R.mipmap.loading).into(holder3.Image_Src2);
                break;
            //++++++++++++++++++=+++++++第三item复用第二个++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=

            //%%%%%%%%%%%%%%%%%%%%%%%%%%第四个item复用第二个%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

            case FOYR_ITEM:
                TwoViewHolder holder4 = (TwoViewHolder) holder;
                //填第二个的数据

                holder4.Title2.setText(hotFragmentBean.getTitle());
                holder4.Cate_Title2.setText(hotFragmentBean.getCate_title());
                holder4.Visit_Num2.setText(hotFragmentBean.getValue());

                Glide.with(context).load(hotFragmentBean.getImg_src()).placeholder(R.mipmap.loading).into(holder4.Image_Src2);
                break;

            //%%%%%%%%%%%%%%%%%%%%%%%%%%第四个item复用第二个%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

            //^^^^^^^^^^^^^^^^^^^^^^^^^^^第五个item复用第二个………………………………………………………………………………

            case FIVE_ITEM:
                TwoViewHolder holder5 = (TwoViewHolder) holder;
                //填第二个的数据

                holder5.Title2.setText(hotFragmentBean.getTitle());
                holder5.Cate_Title2.setText(hotFragmentBean.getCate_title());
                holder5.Visit_Num2.setText(hotFragmentBean.getValue());

                Glide.with(context).load(hotFragmentBean.getImg_src()).placeholder(R.mipmap.loading).into(holder5.Image_Src2);
                break;
            //^^^^^^^^^^^^^^^^^^^^^^^^^^^第五个item复用第二个………………………………………………………………………………
        }
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = position%5+1;
        return  type;
    }
//    @Override
//    public void onClick(View v) {
//        if (v != null) {
//            String tag = (String) v.getTag();
//            switch (tag) {
//                case "holder1":
//                    Toast.makeText(context, "LALALAL", Toast.LENGTH_SHORT).show();
//                    Log.e("TAG", "onClick:点击了 " );
//                    break;
//            }
//        }
//    }



    //------------------多布局之第一个布局——————————————————————
    public static class OneViewHolder extends RecyclerView.ViewHolder {
        private TextView Title;//标题
        private TextView Cate_Title;//出自哪，比如来自独立鱼电影
        private TextView Visit_Num;//阅读人数
        private ImageView Image_Src;//图片

        public OneViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.Title_Home_Fragment_Recyclerview_one_item);
            Cate_Title = (TextView) itemView.findViewById(R.id.Cate_Title_Home_Fragment_Recyclerview_one_item);
            Visit_Num = (TextView) itemView.findViewById(R.id.Visit_Num_Home_Fragment_Recyclerview_one_item);
            Image_Src = (ImageView) itemView.findViewById(R.id.Image_Src_Home_Fragment_Recyclerview_one_item);
        }
    }
    //------------------多布局之第一个布局——————————————————————

    //========================多布局之地二个布局 ===========================================
    public static class TwoViewHolder extends RecyclerView.ViewHolder {
        private TextView Title2;//标题
        private TextView Cate_Title2;//出自哪，比如来自独立鱼电影
        private TextView Visit_Num2;//阅读人数
        private ImageView Image_Src2;//图片

        public TwoViewHolder(View itemView) {
            super(itemView);
            Title2 = (TextView) itemView.findViewById(R.id.Title2_Home_Fragment_Recyclerview_one_item);
            Cate_Title2 = (TextView) itemView.findViewById(R.id.Cate_Title2_Home_Fragment_Recyclerview_one_item);
            Visit_Num2 = (TextView) itemView.findViewById(R.id.Visit_Num2_Home_Fragment_Recyclerview_one_item);
            Image_Src2 = (ImageView) itemView.findViewById(R.id.Image_Src2_Home_Fragment_Recyclerview_one_item);
        }
        //========================多布局之地二个布局 ===========================================
    }
}

