package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.CaptchaCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CaptchaTest {
    @Test(description = "生成图形验证码接口")
    public void captcha1() throws IOException {
       SqlSession session = DatabaseUtil.getSqlSession();
      CaptchaCase captchaCase = session.selectOne("captchaCase",1);
        System.out.println(captchaCase.toString());
        System.out.println(TestConfig.captchaUrl);
        //发送请求，返回结果
        String  result = getResult(captchaCase);
        System.out.println("测试预期结果：" + captchaCase.getExpected());
        System.out.println("预期的状态码为：" + captchaCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
       // Assert.assertEquals(result,captchaCase.getStatus());
    }
    private String getResult(CaptchaCase captchaCase) throws IOException {
        HttpGet httpGet = new HttpGet(TestConfig.captchaUrl);
        //设置请求头信息 设置header
       // httpGet.setHeader("content-type","application/json");
       // httpGet.setHeader("token",TestConfig.token);
       // httpGet.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");
        String result;
        HttpResponse response =TestConfig.defaultHttpClient.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
       // JSONObject jsonObject = new JSONObject(result);
       // String status = jsonObject.get("status").toString();
        return result;
    }
}
