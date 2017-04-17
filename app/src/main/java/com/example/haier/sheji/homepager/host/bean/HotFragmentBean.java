package com.example.haier.sheji.homepager.host.bean;

/**
 * Created by GHZ_PC on 2016/12/22.
 */

public class HotFragmentBean {
    private  String Id;
    private  String title;
    private  String img_src;
    private  String cate_title;
    private  String query_string;
    private  String value;


   public HotFragmentBean(){

   }
    public HotFragmentBean(String id, String title, String img_src, String cate_title, String query_string, String value) {
        Id = id;
        this.title = title;
        this.img_src = img_src;
        this.cate_title = cate_title;
        this.query_string = query_string;
        this.value = value;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getCate_title() {
        return cate_title;
    }

    public void setCate_title(String cate_title) {
        this.cate_title = cate_title;
    }

    public String getQuery_string() {
        return query_string;
    }

    public void setQuery_string(String query_string) {
        this.query_string = query_string;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
