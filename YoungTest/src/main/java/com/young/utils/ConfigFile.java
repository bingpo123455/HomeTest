package com.young.utils;


import com.young.modle.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

   private static ResourceBundle bundle= ResourceBundle.getBundle("application", Locale.CHINA);;

    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;

        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }
        if(name == InterfaceName.VALIDATEPHONE){
            uri = bundle.getString("validatePhone.uri");
        }
        if(name == InterfaceName.DAILYMISSIONBAR){
            uri = bundle.getString("dailyMissionBar.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }
}
