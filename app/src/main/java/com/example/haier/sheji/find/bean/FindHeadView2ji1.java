package com.example.haier.sheji.find.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class FindHeadView2ji1 {

    private String img_src;
    private String title;
    private String cate_title;
    private String value;
    private int tag;

    public  FindHeadView2ji1(){

    }

    public FindHeadView2ji1(String img_src, String title, String cate_title, String value, int tag) {
        this.img_src = img_src;
        this.title = title;
        this.cate_title = cate_title;
        this.value = value;
        this.tag = tag;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getTitle() {
        return title;
    }

    public String getCate_title() {
        return cate_title;
    }

    public String getValue() {
        return value;
    }

    public int getTag() {
        return tag;
    }

    public List<FindHeadView2ji1> jsonParser(String  json){

        List<FindHeadView2ji1> data=null;
        if(json!=null){
            data=new ArrayList<>();

            try {
                JSONObject object=new JSONObject(json);

                JSONObject jsonObject=object.getJSONObject("data");

                JSONArray array=jsonObject.getJSONArray("list");

                for(int i=0;i<array.length();i++){

                    JSONObject object1=array.getJSONObject(i);

                    img_src=object1.getString("img_src");
                    title=object1.getString("title");
                    cate_title=object1.getString("cate_title");
                    value=object1.getString("visit_num");

                    data.add(new FindHeadView2ji1(img_src,title,cate_title,value,i+1));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return data;
    }

}
