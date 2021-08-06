package com.youngCircle.utils;





import com.youngCircle.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

   private static ResourceBundle bundle= ResourceBundle.getBundle("application", Locale.CHINA);;

    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;
        //登录接口的URI
        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }
        //获取广场顶部列表接口URI
        if(name == InterfaceName.SQUARELIST){
            uri = bundle.getString("squareList.uri");
        }
        //添加关注接口URI
        if(name == InterfaceName.ADDFOLLOW){
            uri = bundle.getString("addFollow.uri");
        }
        //取消关注接口URI
        if(name == InterfaceName.DELFOLLOW){
            uri = bundle.getString("delFollow.uri");
        }


        //完整接口的URL地址
        testUrl = address + uri;
        return testUrl;
    }
}
