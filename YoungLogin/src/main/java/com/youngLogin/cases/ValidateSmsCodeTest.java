package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.SendSmsCodeForForgetCase;
import com.youngLogin.model.ValidateSmsCodeCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
//验证短信验证码接口(用于验证短信验证码)
public class ValidateSmsCodeTest {
    @Test(enabled = false,dependsOnGroups = "sendCodesuccess",description = "验证码错误")
    public void validateSmsCode1() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidateSmsCodeCase validateSmsCodeCase =session.selectOne("validateSmsCodeCase",1);
        System.out.println(validateSmsCodeCase.toString());
        System.out.println(TestConfig.validateSmsCodeUrl);
        String result = getResult(validateSmsCodeCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validateSmsCodeCase.getExpected());
        System.out.println("预期的状态码为：" + validateSmsCodeCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validateSmsCodeCase.getStatus());

    }
    @Test(enabled = false,dependsOnGroups = "sendCodesuccess",description = "操作成功（验证通过）")
    public void validateSmsCode2() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        Thread.sleep(30000);
        ValidateSmsCodeCase validateSmsCodeCase =session.selectOne("validateSmsCodeCase",2);
        System.out.println(validateSmsCodeCase.toString());
        System.out.println(TestConfig.validateSmsCodeUrl);
        String result = getResult(validateSmsCodeCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validateSmsCodeCase.getExpected());
        System.out.println("预期的状态码为：" + validateSmsCodeCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validateSmsCodeCase.getStatus());

    }
    @Test(enabled = false,description = "验证码失效(没有发过短信的验证码),错误过多会锁死  注意次数")
    public void validateSmsCode3() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidateSmsCodeCase validateSmsCodeCase =session.selectOne("validateSmsCodeCase",3);
        System.out.println(validateSmsCodeCase.toString());
        System.out.println(TestConfig.validateSmsCodeUrl);
        String result = getResult(validateSmsCodeCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validateSmsCodeCase.getExpected());
        System.out.println("预期的状态码为：" + validateSmsCodeCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validateSmsCodeCase.getStatus());

    }
    private String getResult(ValidateSmsCodeCase validateSmsCodeCase) throws IOException {

        HttpGet httpGet = new HttpGet(TestConfig.validateSmsCodeUrl+
                "?phone=" +validateSmsCodeCase.getPhone()+
                "&smsCode="+validateSmsCodeCase.getSmsCode()+
                "&isEncrypt="+validateSmsCodeCase.getIsEncrypt());
        // httpGet.setHeader("content-type","application/json");
        TestConfig.getHeader();
        String result;
        HttpResponse response =TestConfig.defaultHttpClient.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject jsonObject = new JSONObject(result);
        String status = jsonObject.get("status").toString();
        return status;
    }
}
