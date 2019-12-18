package com.dudes.dexin.bayae.user;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    //登陆请求
    public static void loginWithOkHttp(String account,String password,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("loginAccount",account)
                .add("loginPassword",password)
                .build();
        Request request = new Request.Builder()
                .url( "http://111.111.11.111:8081/AndroidLogin/LoginServlet")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    //注册请求
    public static void registerWithOkHttp(String account,String password,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("registerAccount",account)
                .add("registerPassword",password)
                .build();
        Request request = new Request.Builder()
                .url("http://111.111.11.111:8081/AndroidLogin/RegisterServlet")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}

