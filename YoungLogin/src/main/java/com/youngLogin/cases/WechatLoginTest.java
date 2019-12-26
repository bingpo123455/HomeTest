package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.LoginCase;
import com.youngLogin.model.WechatLoginCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class WechatLoginTest {
    @Test(description = "微信第三方登陆接口")
    public void wechatLogin1() throws IOException, InterruptedException {
       SqlSession session =  DatabaseUtil.getSqlSession();
       WechatLoginCase wechatLoginCase = session.selectOne("wechatLoginCase",1);
        System.out.println(wechatLoginCase.toString());
        System.out.println(TestConfig.wechatLoginUrl);
        //发送请求，返回结果
        Thread.sleep(1000);
        String  result = getResult(wechatLoginCase);
        Thread.sleep(1000);
        System.out.println("测试预期结果：" + wechatLoginCase.getExpected());
        System.out.println("预期的状态码为：" + wechatLoginCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        //返回结果和实际结果做验证
        Assert.assertEquals(result,wechatLoginCase.getStatus());
    }
    private String  getResult(WechatLoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.wechatLoginUrl);
        JSONObject param = new JSONObject();
        param.put("uid",loginCase.getUid());
        param.put("name",loginCase.getName());
        param.put("logintype",loginCase.getLogintype());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        post.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");
        post.setHeader("Accept-Charset","UTF-8");
        post.setHeader("guestId","lalalalalalalalalalala");
        //TestConfig.postHeader();
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
        if(status.equals("0")) {
            TestConfig.token = json.getJSONObject("data").getString("token");

            System.out.println(TestConfig.token);
        }

        System.out.println(status);

        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return status;
    }
}
