package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.InterfaceName;
import com.youngLogin.utils.ConfigFile;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.Test;

public class ReadyTest {
    @Test(groups = "loginTrue",description = "测试准备工作,获取HttpClient对象")
    public void beforeTest(){
        //创建DefaultHTTPClient对象
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        //获取登录接口的完整URL地址
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        //获取验证手机号接口的URL地址
        TestConfig.validatePhoneUrl = ConfigFile.getUrl(InterfaceName.VALIDATEPHONE);
        //获取用户中心任务进度条 接口URL地址
        TestConfig.dailyMissionBarUrl = ConfigFile.getUrl(InterfaceName.DAILYMISSIONBAR);
        //获取注册接口的URL地址
        TestConfig.registerUrl=ConfigFile.getUrl(InterfaceName.REGISTER);
        //获取发送手机验证码接口的URL地址
        TestConfig.sendSmsCodeForClientUrl = ConfigFile.getUrl(InterfaceName.SENDSMSCODEFORCLIENT);
        //获取发送手机验证码（登录之后）接口URL地址
        TestConfig.sendSmsCodeForForgetUrl = ConfigFile.getUrl(InterfaceName.SENDSMSCODEFORFORGET);
        //获取验证手机验证码接口的URL地址
        TestConfig.validateSmsCodeUrl = ConfigFile.getUrl(InterfaceName.VALIDATESMSCODE);
        //获取设置陪伴密码接口的URL地址
        TestConfig.setCPasswordUrl = ConfigFile.getUrl(InterfaceName.SETCPASSWORD);
        //获取验证陪伴密码接口的URL地址
        TestConfig.verifyCPasswordUrl = ConfigFile.getUrl(InterfaceName.VERIFYCPASSWORD);
        //获取修改密码（登录后）接口的URL地址
        TestConfig.setPasswordUrl = ConfigFile.getUrl(InterfaceName.SETPASSWORD);
        //修改密码接口（需要短信验证）接口的URL地址
        TestConfig.resetPasswordUrl = ConfigFile.getUrl(InterfaceName.RESETPASSWORD);
        //获取图形验证码接口的URL地址
        TestConfig.captchaForClientUrl = ConfigFile.getUrl(InterfaceName.CAPTCHAFORCLIENT);
        //验证图形验证码接口的URL地址
        TestConfig.validateCaptchaForClientUrl = ConfigFile.getUrl(InterfaceName.VALIDATECAPTCHAFORCLIENT);
        //刷新token接口的URL地址
        TestConfig.freshTokenUrl = ConfigFile.getUrl(InterfaceName.FRESHTOKEN);
        //用户登出接口URL地址
        TestConfig.logoutUrl = ConfigFile.getUrl(InterfaceName.LOGOUT);
        //用户失效接口URL地址
        TestConfig.invalidUserUrl = ConfigFile.getUrl(InterfaceName.INVALIDUSER);
        //一键登录接口URL地址
        TestConfig.phoneLoginUrl = ConfigFile.getUrl(InterfaceName.PHONELOGIN);
        //短信验证码登陆接口URL地址
        TestConfig.smsLoginUrl = ConfigFile.getUrl(InterfaceName.SMSLOGIN);
        //短信验证码登陆接口URL地址(摄影大赛用)
        TestConfig.smsLoginForPicUrl=ConfigFile.getUrl(InterfaceName.SMSLOGINFORPIC);
        //短信验证注册接口URL地址
        TestConfig.startRegisterUrl = ConfigFile.getUrl(InterfaceName.STARTREGISTER);
        //完成注册接口URL地址
        TestConfig.finishRegisterUrl = ConfigFile.getUrl(InterfaceName.FINISHREGISTER);
        //生成图形验证码接口URL地址
        TestConfig.captchaUrl = ConfigFile.getUrl(InterfaceName.CAPTCHA);
        //逛逛接口URL地址
        TestConfig.loginGGUrl = ConfigFile.getUrl(InterfaceName.LOGINGG);
        //微信第三方登陆接口URL地址
        TestConfig.wechatLoginUrl = ConfigFile.getUrl(InterfaceName.WECHATLOGIN);
    }
}
