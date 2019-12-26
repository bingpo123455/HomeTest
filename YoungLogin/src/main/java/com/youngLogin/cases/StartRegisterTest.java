package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.SmsLoginForPicCase;
import com.youngLogin.model.StartRegisterCase;
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

public class StartRegisterTest {
    @Test(dependsOnGroups = "sendCodesuccess",description = "短信验证码注册接口")
    public void startRegister1() throws IOException {
        SqlSession session =DatabaseUtil.getSqlSession();
        StartRegisterCase startRegisterCase = session.selectOne("startRegisterCase",1);
        System.out.println(startRegisterCase.toString());
        System.out.println(TestConfig.startRegisterUrl);
        //发送请求，返回结果
        String  result = getResult(startRegisterCase);
        System.out.println("测试预期结果：" + startRegisterCase.getExpected());
        System.out.println("预期的状态码为：" + startRegisterCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,startRegisterCase.getStatus());
    }
    private String  getResult(StartRegisterCase startRegisterCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.startRegisterUrl);
        JSONObject param = new JSONObject();
        param.put("phone",startRegisterCase.getPhone());
        param.put("smsCode",startRegisterCase.getSmsCode());
        param.put("deviceid",startRegisterCase.getDeviceid());
        param.put("isEncrypt",startRegisterCase.getIsEncrypt());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //post.setHeader("token",TestConfig.token);
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
    }
}
