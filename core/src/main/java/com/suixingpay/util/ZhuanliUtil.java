package com.suixingpay.util;


import com.alibaba.fastjson.JSONObject;

public class ZhuanliUtil {


    public  static String  getJSONString(int code,String msg){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg",msg);
        return jsonObject.toJSONString();
    }
    public  static  String getJSONString(String msg){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg",msg);
        return jsonObject.toJSONString();
    }
}
