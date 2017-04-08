package com.example.haier.sheji.find.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class Find_2ji1 {

    private String title;
    private String img_src;
    private String visit_num;
    private int type;

    private String stream_id;
    private String ack_code;

    public Find_2ji1(String title, String img_src, String visit_num, int type) {
        this.title = title;
        this.img_src = img_src;
        this.visit_num = visit_num;
        this.type = type;
    }

    public Find_2ji1(String title, String img_src, String visit_num, int type, String stream_id, String ack_code) {
        this.title = title;
        this.img_src = img_src;
        this.visit_num = visit_num;
        this.type = type;
        this.stream_id = stream_id;
        this.ack_code = ack_code;
    }

    public Find_2ji1(){}

    public String getTitle() {
        return title;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getVisit_num() {
        return visit_num;
    }

    public int getType() {
        return type;
    }

    public String getStream_id() {
        return stream_id;
    }

    public String getAck_code() {
        return ack_code;
    }

    public List<Find_2ji1> jsonParser(String json){

        List<Find_2ji1> data=null;

        if(json!=null){

            data=new ArrayList<>();

            try {
                JSONObject object=new JSONObject(json);

                JSONObject object1=object.getJSONObject("data");

                    JSONObject object2 = object1.getJSONObject("tag");

                    title = object2.getString("title");
                    img_src = object2.getString("img_src");
                    visit_num = object2.getInt("subscribers") + "";
                    data.add(new Find_2ji1(title, img_src, visit_num, 2));

                JSONArray array=object1.getJSONArray("list");
                for(int i=0;i<array.length();i++){

                    JSONObject object3=array.getJSONObject(i);

                    title=object3.getString("title");
                    img_src=object3.getString("img_src");
                    visit_num=object3.getString("visit_num");
                    stream_id=object3.getString("stream_id");
                    ack_code=object3.getString("ack_code");

                    if(i%4==3){
                       type=1;
                    }else {
                        type=0;
                    }

                    data.add(new Find_2ji1(title,img_src,visit_num,type,stream_id,ack_code));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return  data;

    }

}
