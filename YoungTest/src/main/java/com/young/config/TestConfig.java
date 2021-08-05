package com.young.config;

import lombok.Data;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


@Data
public class TestConfig {

    //登陆接口uri
    public static String loginUrl;
    public static String validatePhoneUrl;
    public static String dailyMissionBarUrl;
    public static HttpGet httpGet = new HttpGet();
    public static HttpPost httpPost = new HttpPost();
    public static void getHeader(){
        httpGet.setHeader("content-type","application/json");
    }
    public static void postHeader(){
        httpPost.setHeader("content-type","application/json");
    }

    //用来存储cookies信息的变量
    public static CookieStore store;
    //获取token
    public static String token;
    //声明http客户端
    public static DefaultHttpClient defaultHttpClient;

}
