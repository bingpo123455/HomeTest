package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.FreshTokenCase;
import com.youngLogin.model.LogoutCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.io.IOException;

public class LogoutTest {
    @Test(dependsOnGroups = "loginTrue",description = "用户登出接口")
    public void logout1() throws IOException {
      SqlSession session = DatabaseUtil.getSqlSession();
      LogoutCase logoutCase = session.selectOne("logoutCase",1);
        System.out.println(logoutCase.toString());
        System.out.println(TestConfig.logoutUrl);
        //发送请求，返回结果
        String  result = getResult(logoutCase);
        System.out.println("测试预期结果：" + logoutCase.getExpected());
        System.out.println("预期的状态码为：" + logoutCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,logoutCase.getStatus());
    }
    private String getResult(LogoutCase logoutCase) throws IOException {
        HttpGet httpGet = new HttpGet(TestConfig.logoutUrl);
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
    }
}
