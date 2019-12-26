package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.SendSmsCodeForClientCase;
import com.youngLogin.model.SendSmsCodeForForgetCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

//发送短信验证码接口(登录后忘记密码使用)
public class SendSmsCodeForForgetTest {

    @Test(enabled = false,dependsOnGroups = "loginTrue",groups = "sendSuccess",description = "操作成功(发送成功)")
    public void sendSmsCodeForForget1() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        SendSmsCodeForForgetCase sendSmsCodeForForgetCase =session.selectOne("sendSmsCodeForForgetCase",1);
        System.out.println(sendSmsCodeForForgetCase.toString());
        System.out.println(TestConfig.sendSmsCodeForForgetUrl);
        String result = getResult(sendSmsCodeForForgetCase);
        System.out.println("测试预期结果：" + sendSmsCodeForForgetCase.getExpected());
        System.out.println("预期的状态码为：" + sendSmsCodeForForgetCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,sendSmsCodeForForgetCase.getStatus());

    }
    private String getResult(SendSmsCodeForForgetCase sendSmsCodeForForgetCase) throws IOException {

        HttpGet httpGet = new HttpGet(TestConfig.sendSmsCodeForClientUrl+
                "?phone=" +sendSmsCodeForForgetCase.getPhone()+
                "&isEncrypt="+sendSmsCodeForForgetCase.getIsEncrypt());
        httpGet.setHeader("content-type","application/json");
        String result;
        HttpResponse response =TestConfig.defaultHttpClient.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject jsonObject = new JSONObject(result);
        String status = jsonObject.get("status").toString();
        return status;
    }
}
