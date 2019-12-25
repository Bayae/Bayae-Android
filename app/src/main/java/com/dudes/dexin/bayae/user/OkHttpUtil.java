package com.dudes.dexin.bayae.user;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    //登陆请求
    public static void loginWithOkHttp(String credential,String password,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("credential",credential)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url( "http://67.209.183.5/bayae/user/login")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    //注册请求
    public static void registerWithOkHttp(String email,String password,String username,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("email",email)
                .add("password",password)
                .add("username",username )
                .build();
        Request request = new Request.Builder()
                .url("http://67.209.183.5/bayae/user/register")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}

