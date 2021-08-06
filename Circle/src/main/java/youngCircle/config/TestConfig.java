package com.youngCircle.config;


import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


public class TestConfig {

    //登陆接口地址
    public static String loginUrl;
    //1.获取广场顶部列表接口地址
    public static String squareListUrl;
    //2.添加关注接口
    public static String  addFollowUrl;
    //5.取消关注接口
    public static String delFollowUrl;



    public static HttpGet httpGet = new HttpGet();
    public static HttpPost httpPost = new HttpPost();
    public static void getHeader(){ httpGet.setHeader("content-type","application/json");
    }
    public static void postHeader(){ httpPost.setHeader("content-type","application/json");
    }

    //用来存储cookies信息的变量
    public static CookieStore store;
    //用来存储token信息的变量
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
