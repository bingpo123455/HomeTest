package com.youngLogin.config;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;


public class TestConfig {

    //登陆接口地址
    public static String loginUrl;
    //验证手机号接口地址
    public static String validatePhoneUrl;
    //用户中心任务进度条地址
    public static String dailyMissionBarUrl;
    //手机号 密码  注册用户接口地址
    public static String registerUrl;
    //发送短信验证码（未注册）接口地址
    public static String sendSmsCodeForClientUrl;
    //发送短信验证码（已登录）接口地址
    public static String sendSmsCodeForForgetUrl;
    //验证手机验证码接口地址
    public static String validateSmsCodeUrl;
    //设置陪伴密码接口地址
    public static String setCPasswordUrl;
    //验证陪伴密码接口地址
    public static String verifyCPasswordUrl;
    //修改登录密码（登录后的）接口地址
    public static String setPasswordUrl;
    //发送短信修改密码接口地址
    public static String resetPasswordUrl;
    //获取图形验证码接口地址
    public static String captchaForClientUrl;
    //.验证图形验证码接口地址
    public static String validateCaptchaForClientUrl;
    //刷新token接口地址
    public static String freshTokenUrl;
    //用户登出接口
    public static String logoutUrl;
    //用户失效接口地址
    public static String invalidUserUrl;
    //一键登录接口地址
    public static String phoneLoginUrl;
    //短信验证码登陆接口地址
    public static String smsLoginUrl;
    //短信验证码登陆接口地址（摄影大赛登录）
    public static String smsLoginForPicUrl;
    //短信验证注册接口地址
    public static String startRegisterUrl;
    //完成注册接口地址
    public static String finishRegisterUrl;
    //生成图形验证码接口地址
    public static String captchaUrl;
    //逛逛接口地址
    public static String loginGGUrl;
    //微信第三方登陆接口地址
    public static  String wechatLoginUrl;




    public static HttpGet httpGet = new HttpGet();
    public static HttpPost httpPost = new HttpPost();
    public static void getHeader(){ httpGet.setHeader("content-type","application/json");
    }
    public static void postHeader(){ httpPost.setHeader("content-type","application/json");
    }

    //用来存储cookies信息的变量
    public static CookieStore store;
    //获取token
    public static String token;
    //声明http客户端
    public static DefaultHttpClient defaultHttpClient;

//  发送请求，返回结果。进行校验的代码

/*      //发送请求，返回结果
        String  result = getResult(loginCase);
        System.out.println("测试预期结果：" + loginCase.getExpected());
        System.out.println("预期的状态码为：" + loginCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

    //返回结果和实际结果做验证
        Assert.assertEquals(result,loginCase.getStatus());*/

//  发送get请求的代码
/*   private String getResult(VerifyCPasswordCase verifyCPasswordCase) throws IOException {
        HttpGet httpGet = new HttpGet(TestConfig.verifyCPasswordUrl+
                "?cpassword=" +verifyCPasswordCase.getCpassword()+
                "&isEncrypt="+verifyCPasswordCase.getIsEncrypt());
        //设置请求头信息 设置header
        httpGet.setHeader("content-type","application/json");
        httpGet.setHeader("token",TestConfig.token);
        httpGet.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");
        String result;
        HttpResponse response =TestConfig.defaultHttpClient.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject jsonObject = new JSONObject(result);
        String status = jsonObject.get("status").toString();
        return status;
    }*/
//  发送post请求的代码
/*   private String  getResult(SetCPasswordCase setCPasswordCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.setCPasswordUrl);
        JSONObject param = new JSONObject();
        param.put("cpassword",setCPasswordCase.getCpassword());
        param.put("isEncrypt",setCPasswordCase.getIsEncrypt());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        post.setHeader("token",TestConfig.token);
        post.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");

        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");

        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        JSONObject json = new JSONObject(result);
        String status = json.get("status").toString();
        System.out.println(status);

        return status;
    }*/
}
