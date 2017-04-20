package com.example.haier.sheji.url;

/**
 * Created by GHZ_PC on 2016/12/21.
 * 顾怀章
 * 接口类
 */

public  class Constants {
    //首页-->热门
    /*
    http://app.lerays.com/api/stream/rec/list?pubtime=0&cate_sign=null
    http://app.lerays.com/api/stream/rec/list?pubtime=1482393600&cate_sign=65b24283c31d0144dc64ba669d8f9595

   http://app.lerays.com/api/stream/rec/list?pubtime=1482321600&cate_sign=2437cf4b118c730fc42da84fadca4e1a

   http://app.lerays.com/api/stream/rec/list?pubtime=1482260400&cate_sign=ff2a8022307f902e0c1ec04b93d04609

  http://app.lerays.com/api/stream/rec/list?pubtime=1482199210&cate_sign=e1482d198cde90b1573ee45cbb8a6ab0

  http://app.lerays.com/api/stream/rec/list?pubtime=1482123600&cate_sign=1e2d33a915b610968c63183e87af41ef
     */
    public  static  final String Home_Hot_Fast = "http://app.lerays.com/api/stream/rec/list?pubtime=0&cate_sign=null";

   public  static  final  String Home_Hot_Fast1 = "http://app.lerays.com/api/stream/rec/list?pubtime=";
   public  static  final  String Home_Hot_Fast2 = "&cate_sign=";
    //热门二级接口
    public  static  final String Home_Hot_Saecond="http://app.lerays.com/api/stream/view?";
    //二级接口webView
    public  static  final String Home_Hot_Saecond2="http://www.lerays.com/stream/view?";
   //http://app.lerays.com/api/stream/view?/api/stream/list?cate_list=31&amp;cate_type=cate&amp;pubtime={nexttime}&amp;cate_sign={nextsign}
    /**
     * 这个是网页的 http://www.lerays.com/stream/view?acm=none-52-156081-JnShlcpp&stream_id=156081&_ack=JnShlcpp
     * 这个是app的  http://www.lerays.com/stream/view?acm=none-52-156081-JnShlcpp&amp;stream_id=156081&amp;_ack=JnShlcpp
     * 打印        http://www.lerays.com/stream/view?acm=none-52-156081-JnShlcpp&stream_id=156081&_ack=JnShlcpp
     */


    //http://app.lerays.com/api/stream/view?acm=none-52-156081-JnShlcpp&amp;stream_id=156081&amp;_ack=JnShlcpp
   //http://www.lerays.com/stream/view?acm=none-52-156081-JnShlcpp&stream_id=156081&_ack=JnShlcpp
    //
    //首页-->互动
  public  static  final String Home_Interaction_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=33&cate_type=cate";
    public  static  final String Home_Interaction_Fast2 = "&cate_list=33&cate_sign=";
    //二级页面接口http://app.lerays.com/api/stream/view?acm=none-51-155769-Wq8ga1Ps&stream_id=155769&_ack=Wq8ga1Ps&_ack=Wq8ga1Ps&stream_id=155769
                      //acm=none-51-155769-Wq8ga1Ps&amp;stream_id=155769&amp;_ack=Wq8ga1Ps
    //app.lerays.com/api/stream/view?acm=none-51-155769-Wq8ga1Ps&amp;stream_id=155769&amp;_ack=Wq8ga1Ps



    //首页-->奇趣
  public  static  final String Home_Interesting_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=31&cate_type=cate";
    public  static  final String Home_Interesting_Fast1 = "http://app.lerays.com/api/stream/list?pubtime=";
    public  static  final String Home_Interesting_Fast2 = "&cate_list=31&cate_sign=";
    public  static  final String Home_Interesting_Fast3 = "&cate_type=cate";
//拼接奇趣的下一页接口//http://app.lerays.com/api/stream/list?pubtime=0&cate_list=31&cate_type=cate
//拼接奇趣的下一页接口//http://app.lerays.com/api/stream/list?pubtime=1482372010&cate_list=31&cate_sign=333c2d0caf49afdc880106ff5ea1fcd9&cate_type=cate
//拼接奇趣的下一页接口//http://app.lerays.com/api/stream/list?pubtime=1482058810&cate_list=31&cate_sign=50abf18befae0a7f8f63a0316a1097c0&cate_type=cate
    //http://app.lerays.com/api/stream/list?pubtime=
   //1482372010     nexttime
    //&cate_list=31&cate_sign=   nextsign
    //333c2d0caf49afdc880106ff5ea1fcd9
//http://app.lerays.com/api/stream/list?pubtime=1482372010&&cate_list=31&cate_sign=333c2d0caf49afdc880106ff5ea1fcd9&cate_type=cate
//

    //首页-->娱乐
  public  static  final String Home_Entertainmen_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=4&cate_type=cate";
    public  static  final String Home_Entertainmen_Fast1 = "http://app.lerays.com/api/stream/list?pubtime=";
    public  static  final String Home_Entertainmen_Fast2 = "&cate_list=4&cate_sign=";
    public  static  final String Home_Entertainmen_Fast3 = "&cate_type=cate";
//原来第三页http://app.lerays.com/api/stream/list?pubtime=1482505200&cate_list=4&cate_sign=78a7347aa70d67c641f6a1157fa98004&cate_type=cate
//原来第四页http://app.lerays.com/api/stream/list?pubtime=1482303651&cate_list=4&cate_sign=5344475eafa435dde97828ce5ff8a210&cate_type=cate

    //首页-->萌宠
  public  static  final String Home_Pets_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=34&cate_type=cate";
    public  static  final String Home_Pets_Fast2 = "&cate_list=34&cate_sign=";
    //首页-->爆笑
  public  static  final String Home_Rofl_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=3&cate_type=cate";
    public  static  final String Home_Rofl_Fast2 = "&cate_list=3&cate_sign=";
    //首页-->生活
  public  static  final String Home_Life_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=35&cate_type=cate";
    public  static  final String Home_Life_Fast2 = "&cate_list=35&cate_sign=";

      //视频
  public  static  final String Video_Fast = "http://app.lerays.com/api/nvideo/list?sign=null&pubtime=0";

    //发现
  public  static  final String Discover_Fast = "http://app.lerays.com/api/user/subscription/streams?pubtime=0";


}
