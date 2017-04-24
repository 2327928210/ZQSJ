package com.example.haier.sheji;

/**
 * Created by GHZ_PC on 2016/12/21.
 * 顾怀章
 * 接口类
 */

public  class Constants  {


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


    //首页-->互动
    public  static  final String Home_Interaction_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=33&cate_type=cate";

    //首页-->奇趣
   public  static  final String Home_Interesting_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=31&cate_type=cate";

    //首页-->娱乐
   public  static  final String Home_Entertainmen_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=4&cate_type=cate";

    //首页-->萌宠
   public  static  final String Home_Pets_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=34&cate_type=cate";

    //首页-->爆笑
   public  static  final String Home_Rofl_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=3&cate_type=cate";

    //首页-->生活
   public  static  final String Home_Life_Fast = "http://app.lerays.com/api/stream/list?pubtime=0&cate_list=35&cate_type=cate";

      //视频
   public  static  final String Video_Fast = "http://app.lerays.com/api/nvideo/list?sign=null&pubtime=0";

    //发现
     public  static  final String Discover_Fast = "http://app.lerays.com/api/user/subscription/streams?pubtime=0";
//     public static final String  Discover_Fast2="http://app.lerays.com/api/user/subscription/streams?pubtime=1482318000&nextsign=22903b76f037e7da0273759b7b98aebc";
//     public static final String  Discover_Fast3="http://app.lerays.com/api/user/subscription/streams?pubtime=1482192010&nextsign=1e8237954c220fe5e28b733d30a9db35";
//     public  static  final String Discover_Fast4 ="http://app.lerays.com/api/user/subscription/streams?pubtime=1482192010&nextsign=1e8237954c220fe5e28b733d30a9db35";
//     public  static  final String Discover_Fast5="http://app.lerays.com/api/user/subscription/streams?pubtime=1481976000&nextsign=47553e469e01a4de2af66f4898b0eeea";
//     public  static  final String Discover_Fast6 ="http://app.lerays.com/api/user/subscription/streams?pubtime=1481799059&nextsign=d89b8ba9897205598d7fde38e4f56953";
//     public  static  final String Discover_Fast7 ="http://app.lerays.com/api/user/subscription/streams?pubtime=1481627986&nextsign=7375a1abeec9070fb9af6a3ac69f439e";
//     public  static  final String Discover_Fast8 ="http://app.lerays.com/api/user/subscription/streams?pubtime=1481241610&nextsign=4ba194d2914fb442a9917d1801836591";
//     public  static  final String Discover_Fast9 ="http://app.lerays.com/api/user/subscription/streams?pubtime=1481023066&nextsign=25023d414401b23dbe6b26fb1ba5f1b0";

    //发现  头视图图片
    public static final String Discover_find_head="http://app.lerays.com/api/discovery/navi";

    //发现 透视图二级页面1
    public static final String Discover_find_head_2ji1="http://app.lerays.com/api/stream/rank/day";

    //发现 透视图二级页面2
    public static final String Discover_find_head_2ji2="http://app.lerays.com/api/stream/rank/week";

    //发现 透视图二级页面3
    public static final String Discover_find_head_2ji3="http://app.lerays.com/api/stream/rank/month";

    //发现 listview 第一个item 二级界面第一页
    public static final String Discover_find_2ji1="http://app.lerays.com/api/stream/tag/slist?pubtime=null&nextsign=null&tag_id=7";
   // public static final String Discover_find_2ji1="http://app.lerays.com/api/stream/tag/slist?pubtime=null&nextsign=null&tag_id=11";
    //发现 listview 第一个item二级界面第2页
    public static final String Discover_find_2ji11="http://app.lerays.com/api/stream/tag/slist?pubtime=null&nextsign=null&tag_id=1";
   // public static final String Discover_find_2ji11="http://app.lerays.com/api/stream/tag/slist?pubtime=1481976000&nextsign=47553e469e01a4de2af66f4898b0eeea&tag_id=11";
    //发现 listview 第一个item（萌萌哒）二级界面第一页
    public static final String Discover_find_2ji111="http://app.lerays.com/api/stream/tag/slist?pubtime=1480226400&nextsign=fb226a393cae2b78e91fffe6d798f19a&tag_id=11";

    //发现 listview 第二个item（萌萌哒）二级界面第一页
   // public static final String Discover_find_2ji2="http://app.lerays.com/api/stream/view?stream_id=156051&_ack=pPnOYnp7&_ack=pPnOYnp7&stream_id=156051";
    //public static final String Discover_find_2ji2="http://app.lerays.com/api/stream/view?stream_id=156022&_ack=yQ0FzbWE&_ack=yQ0FzbWE&stream_id=156022";
    public static final String Discover_find_2ji2="http://app.lerays.com/api/user/subscription/streams?pubtime=0";

    // 发现 listview 第一个item（萌萌哒）三级界面第一页
    public static final String Discover_find_3ji="http://app.lerays.com/api/stream/view?_ack=rpsvgneu&stream_id=155875";

    //listview 第一个item（萌萌哒）三级界面第二个页
    public static final String Discover_find_3ji2="http://app.lerays.com/api/stream/view?_ack=Rlgc8fX0&stream_id=155868";
}
