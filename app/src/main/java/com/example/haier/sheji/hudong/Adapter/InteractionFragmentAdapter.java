package com.example.haier.sheji.hudong.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haier.sheji.R;
import com.example.haier.sheji.homepager.host.bean.HotFragmentBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

/**
 * Created by GHZ_PC on 2016/12/25.
 * 互动的适配器
 * 这个得设置一个标签用来添加头视图，
 *
 */

public class InteractionFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HotFragmentBean> data;
    private Context context;
    private ImageLoader imageLoader;
    //重新尝试
    private static final int IS_HEADER = 0;//头部视图标签
    private static final int IS_NORMAL = 1;//正常视图标签
    private View.OnClickListener mClickListener;//监听

//    public static final int TYPE_HEADER = 0;//头部视图标签
//    public static final int TYPE_NORMAL = 1;//正常视图标签
//    private  int mHeaderCount = 1;//头部view个数
    //  private View mHeaderView;    //头部视图

    //给头部视图添加一个get,set方法，主函数用+++++++++++
//    public void setmHeaderView(View mHeaderView) {
//        this.mHeaderView = mHeaderView;
//    //notifyItemInserted(int position):在 position 位置插入数据的时候更新
//        notifyItemInserted(0);
//    }
//    public View getmHeaderView() {
//        return mHeaderView;
//    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=

    public InteractionFragmentAdapter(List<HotFragmentBean> data, Context context) {
        this.data = data;
        this.context = context;
    }
//重新尝试添加标签-----

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? IS_HEADER : IS_NORMAL;
    }


//    //给item 添加标签，
//    @Override
//    public int getItemViewType(int position) {
//        if (mHeaderView == null)return  TYPE_NORMAL;//没有头视图，返回正常标签
//        if (position == 0)return  TYPE_HEADER;//位置为0，返回头视图标签
//        return TYPE_NORMAL;
//    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
//      if (mHeaderView != null && viewType == TYPE_HEADER) return  new MyViewHolder(mHeaderView);//++++++返回头部视图
        //多布局了---------------
        RecyclerView.ViewHolder  holder = null;
        switch (viewType) {
            case IS_NORMAL:
                View view = LayoutInflater.from(context).inflate(R.layout.interaction_fragment_recyclerview_item, parent, false);
                holder = new MyViewHolder(view);
                break;
            case IS_HEADER:  //载入头部视图标签
                //View view2 = View.inflate(context,R.layout.interaction_fragment_recyclerview_head_item,null);
                View view2 = LayoutInflater.from(context).inflate(R.layout.interaction_fragment_recyclerview_head_item, parent, false);
                holder = new MyViewHolder2(view2);
                break;
        }
        return holder;
    }
    //设置点击事件
    public  void  setClickListener(View.OnClickListener listener) {

        mClickListener = listener;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder  holder, int position) {
        // if (getItemViewType(position)==TYPE_HEADER) return;//+++不是很懂呀
        // int pos = getRealPosition(holder);//不知道干什么的
        // HotFragmentBean hotFragmentBean = data.get(position);
        int type = getItemViewType(position);
        HotFragmentBean hotFragmentBean = data.get(position);
        switch (type){
            case IS_NORMAL://普通视图
                MyViewHolder holder1 = (MyViewHolder) holder;
                holder1.title.setText(hotFragmentBean.getTitle());
                holder1.value.setText(hotFragmentBean.getValue());
                imageLoader = ImageLoader.getInstance();
                DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)                //内存缓存
                        .cacheOnDisk(true)                  //磁盘缓存
                        .bitmapConfig(Bitmap.Config.ARGB_4444)//解码方式
                        .resetViewBeforeLoading(true)       //加载前是否重置,为了放置图片错位
                        //.showImageOnLoading(R.mipmap.ic_launcher) //默认图片
                        // .showImageOnFail(R.mipmap.ic_launcher)    //失败图片
                        .showImageForEmptyUri(R.mipmap.ic_launcher)  //url地址为空的时候显示的图片
                        .imageScaleType(ImageScaleType.NONE) //缩放类型
                        //.displayer(new FadeInBitmapDisplayer(3*1000))//显示前的处理【CircleBitmapDisplayer,FadeInBitmapDisplayer,RoundedBitmapDisplayer】
                        .build();
                imageLoader.displayImage(hotFragmentBean.getImg_src(), holder1.img_src, displayImageOptions);
                //———————————————————item点击事件
                holder1.img_src.setTag(position);
                if (mClickListener != null){
                    holder1.img_src.setOnClickListener(mClickListener);
                }
                break;
            case  IS_HEADER:
                MyViewHolder2 holder2 = (MyViewHolder2) holder;
                holder2.title2.setText(hotFragmentBean.getTitle());
                holder2.value2.setText(hotFragmentBean.getValue());
                imageLoader = ImageLoader.getInstance();
                DisplayImageOptions displayImageOptions2 = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)                //内存缓存
                        .cacheOnDisk(true)                  //磁盘缓存
                        .bitmapConfig(Bitmap.Config.ARGB_4444)//解码方式
                        .resetViewBeforeLoading(true)       //加载前是否重置,为了放置图片错位
                        //.showImageOnLoading(R.mipmap.ic_launcher) //默认图片
                        // .showImageOnFail(R.mipmap.ic_launcher)    //失败图片
                        .showImageForEmptyUri(R.mipmap.ic_launcher)  //url地址为空的时候显示的图片
                        .imageScaleType(ImageScaleType.NONE) //缩放类型
                        //.displayer(new FadeInBitmapDisplayer(3*1000))//显示前的处理【CircleBitmapDisplayer,FadeInBitmapDisplayer,RoundedBitmapDisplayer】
                        .build();
                imageLoader.displayImage(hotFragmentBean.getImg_src(), holder2.img_src2, displayImageOptions2);
                holder2.img_src2.setTag(position);
                if (mClickListener != null ){
                    holder2.img_src2.setOnClickListener(mClickListener);
                }
                break;
        }

    }

//     //这个方法也不是很懂
//     public int getRealPosition(RecyclerView.ViewHolder holder) {
//         int position = holder.getLayoutPosition();
//         return mHeaderView == null ? position : position - 1;
//     }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
        //return  mHeaderView == null ? data.size() : data.size()+1;//这个也不知道呀
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;//标题
        public ImageView img_src;//图片
        public TextView value;//阅读人数

        public MyViewHolder(View itemView) {
            super(itemView);
            //if(itemView == mHeaderView) return;//++返回头部视图
            title = (TextView) itemView.findViewById(R.id.interaction_title);
            img_src = (ImageView) itemView.findViewById(R.id.interaction_img_src);
            value = (TextView) itemView.findViewById(R.id.interaction_value);
        }

    }
    public   class  MyViewHolder2 extends  RecyclerView.ViewHolder{
        public TextView title2;//标题
        public ImageView img_src2;//图片
        public   TextView value2;//阅读人数
        public MyViewHolder2(View itemView) {
            super(itemView);
            //if(itemView == mHeaderView) return;//++返回头部视图
            title2 = (TextView) itemView.findViewById(R.id.interaction_title2);
            img_src2 = (ImageView) itemView.findViewById(R.id.interaction_img_src2);
            value2 = (TextView) itemView.findViewById(R.id.interaction_value2);
        }
    }

}
