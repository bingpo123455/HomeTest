package com.youngCircle.cases;

import com.youngCircle.config.TestConfig;
import com.youngCircle.model.LoginCase;
import com.youngCircle.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @Test(enabled = true,groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrue() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求，返回结果
        Thread.sleep(1000);
        String  result = getResult(loginCase);
        Thread.sleep(1000);
        System.out.println("测试预期结果：" + loginCase.getExpected());
        System.out.println("预期的状态码为：" + loginCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        //返回结果和实际结果做验证
        Assert.assertEquals(result,loginCase.getStatus());
        //包含登录成功判断
        //Assert.assertTrue(result.contains("登录成功"));
    }
    @Test(description = "用户登陆失败接口")
    public void loginFalse2() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //发送请求，返回结果
        String  result = getResult(loginCase);
        System.out.println("测试预期结果：" + loginCase.getExpected());
        System.out.println("预期的状态码为：" + loginCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        //返回结果和实际结果做验证
        Assert.assertEquals(result,loginCase.getStatus());
    }

    private String  getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("phone",loginCase.getPhone());
        param.put("password",loginCase.getPassword());
        param.put("picCode",loginCase.getPicCode());
        param.put("isEncrypt",loginCase.getIsEncrypt());
        param.put("deviceid",loginCase.getDeviceid());

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
