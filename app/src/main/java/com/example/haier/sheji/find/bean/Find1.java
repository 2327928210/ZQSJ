package com.example.haier.sheji.find.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class Find1{

    private String title;
    private String img_src;
    private String text;
    private int type;
    private String tag_id;
    private String ack_code;

    public Find1(){}

    public Find1(String title, String img_src, String text, int type, String tag_id, String ack_code) {
        this.title = title;
        this.img_src = img_src;
        this.text = text;
        this.type = type;
        this.tag_id = tag_id;
        this.ack_code = ack_code;
    }

    public String getTag_id() {
        return tag_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }

    public String getAck_code() {
        return ack_code;
    }

    public List<Find1> jsonParser(String json){

        List<Find1> data=null;

        if(json!=null){
            data=new ArrayList<>();
            try {
                JSONObject object=new JSONObject(json);

                JSONObject object1=object.getJSONObject("data");

                JSONArray array=object1.getJSONArray("list");

                for(int i=0;i<array.length();i++){

                    JSONObject object2=array.getJSONObject(i);

                    JSONObject object3=object2.getJSONObject("tag");
                    title=object3.getString("title");
                    img_src=object3.getString("img_src");
                    text=object3.getString("subscribers");
                    tag_id=object3.getString("Id");

                    ack_code=object2.getString("ack_code");
                    data.add(new Find1(title,img_src,text,0,tag_id,ack_code));

                    title=object2.getString("title");
                    img_src=object2.getString("img_src");
                    text=object2.getString("visit_num");
                    tag_id=object2.getString("stream_id");

                    data.add(new Find1(title,img_src,text,1,tag_id,ack_code));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return data;

    }

}
