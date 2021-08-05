package com.youngLogin.utils;



import com.youngLogin.model.InterfaceName;

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
        //验证手机号接口URI
        if(name == InterfaceName.VALIDATEPHONE){
            uri = bundle.getString("validatePhone.uri");
        }
        //用户中心任务进度条接口URI
        if(name == InterfaceName.DAILYMISSIONBAR){
            uri = bundle.getString("dailyMissionBar.uri");
        }
        //手机号  密码 注册接口URI
        if(name == InterfaceName.REGISTER){
            uri=bundle.getString("register.uri");
        }
        //发送手机号验证码接口URI
        if(name == InterfaceName.SENDSMSCODEFORCLIENT){
            uri = bundle.getString("sendSmsCodeForClient.uri");
        }
        //发送手机号验证码（已登录）接口URI
        if(name == InterfaceName.SENDSMSCODEFORFORGET){
            uri = bundle.getString("sendSmsCodeForForget.uri");
        }
        //验证短信验证码接口URI
        if(name == InterfaceName.VALIDATESMSCODE){
            uri = bundle.getString("validateSmsCode.uri");
        }
        //设置陪伴密码接口URI
        if(name == InterfaceName.SETCPASSWORD){
            uri = bundle.getString("setCPassword.uri");
        }
        //验证陪伴密码URI
        if(name == InterfaceName.VERIFYCPASSWORD){
            uri = bundle.getString("verifyCPassword.uri");
        }

        //修改登录之后密码接口URI
        if(name == InterfaceName.SETPASSWORD){
            uri = bundle.getString("setPassword.uri");
        }
        //修改密码接口（需要短信验证）接口URI
        if(name == InterfaceName.RESETPASSWORD){
            uri = bundle.getString("resetPassword.uri");
        }
        //获取图形验证码接口URI
        if(name == InterfaceName.CAPTCHAFORCLIENT){
            uri = bundle.getString("captchaForClient.uri");
        }
        //验证图形验证码接口URI
        if(name == InterfaceName.VALIDATECAPTCHAFORCLIENT){
            uri = bundle.getString("validateCaptchaForClient.uri");
        }
        //刷新token接口URI
        if(name == InterfaceName.FRESHTOKEN){
            uri = bundle.getString("freshToken.uri");
        }
        //用户登出接口URI
        if(name == InterfaceName.LOGOUT){
            uri = bundle.getString("logout.uri");
        }
        //用户失效接口URI
        if(name == InterfaceName.INVALIDUSER){
            uri = bundle.getString("invalidUser.uri");
        }
        //一键登录接口URI
        if(name == InterfaceName.PHONELOGIN){
            uri = bundle.getString("phoneLogin.uri");
        }
        //短信验证码登陆接口URI
        if(name == InterfaceName.SMSLOGIN){
            uri = bundle.getString("smsLogin.uri");
        }
        //短信验证码登陆接口(摄影大赛登录用)URI
        if(name == InterfaceName.SMSLOGINFORPIC){
            uri = bundle.getString("smsLoginForPic.uri");
        }
        //短信验证注册接口URI
        if(name == InterfaceName.STARTREGISTER){
            uri = bundle.getString("startRegister.uri");
        }
        //完成注册接口URI
        if(name == InterfaceName.FINISHREGISTER){
            uri = bundle.getString("finishRegister.uri");
        }
        //生成图形验证码接口URI
        if(name == InterfaceName.CAPTCHA){
            uri = bundle.getString("captcha.uri");
        }
        //逛逛接口URI
        if(name == InterfaceName.LOGINGG){
            uri = bundle.getString("loginGG.uri");
        }
        //微信第三方登陆接口URI
        if(name == InterfaceName.WECHATLOGIN){
            uri = bundle.getString("wechatLogin.uri");
        }




        //完整接口的URL地址
        testUrl = address + uri;
        return testUrl;
    }
}
