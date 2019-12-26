package com.dudes.dexin.bayae.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    public static final MediaType TYPE_JSON
        = MediaType.get("application/json; charset=utf-8");


//    登陆请求
    public static void loginWithOkHttp(String credential,String password,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Map map = new HashMap();
        map.put("credential",credential);
        map.put("password",password);
        String mapToJson = JSON.toJSONString(map);

        RequestBody body = RequestBody.create(TYPE_JSON, mapToJson);
        Request request = new Request.Builder()
                .url( "http://67.209.183.5/bayae/user/login")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    //注册请求
    public static void registerWithOkHttp(String email,String password,String username,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Map map = new HashMap();
        map.put("email",email);
        map.put("password",password);
        map.put("username",username);
        String mapToJson = JSON.toJSONString(map);
        RequestBody body = RequestBody.create(TYPE_JSON, mapToJson);
        Request request = new Request.Builder()
                .url("http://67.209.183.5/bayae/user/register")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}

