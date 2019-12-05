package com.suixingpay.util;


import com.alibaba.fastjson.JSONObject;

public class ZhuanliUtil {


    public  static String  getJSONString(int code,Object msg){
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
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}
