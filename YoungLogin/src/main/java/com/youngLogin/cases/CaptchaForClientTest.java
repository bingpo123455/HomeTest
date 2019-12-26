package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;

import com.youngLogin.model.CaptchaForClientCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.io.IOException;

public class CaptchaForClientTest {
    @Test(enabled = false,description = "获取图形验证码接口")
    public void captchaForClient1() throws IOException {
       SqlSession session =  DatabaseUtil.getSqlSession();
       CaptchaForClientCase captchaForClientCase = session.selectOne("captchaForClientCase",1);
        System.out.println(captchaForClientCase.toString());
        System.out.println(TestConfig.captchaForClientUrl);
        //发送请求，返回结果
        String  result = getResult(captchaForClientCase);
        System.out.println("测试预期结果：" + captchaForClientCase.getExpected());
        System.out.println("预期的状态码为：" + captchaForClientCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,captchaForClientCase.getStatus());
    }
    private String getResult(CaptchaForClientCase captchaForClientCase) throws IOException {
        HttpGet httpGet = new HttpGet(TestConfig.captchaForClientUrl+
                "?phone=" + captchaForClientCase.getPhone()+
                "&isEncrypt=" + captchaForClientCase.getIsEncrypt());
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
