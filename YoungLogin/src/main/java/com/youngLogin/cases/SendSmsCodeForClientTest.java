package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.LoginCase;
import com.youngLogin.model.SendSmsCodeForClientCase;
import com.youngLogin.model.ValidatePhoneCase;
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

//发送短信验证码接口(用于获取手机短信验证码)
public class SendSmsCodeForClientTest {

    @Test(enabled = false,groups = "sendCodesuccess",description = "操作成功(发送短信验证码成功)")
    public void sendSmsCodeForClient1() throws IOException, InterruptedException {
       SqlSession session = DatabaseUtil.getSqlSession();
       SendSmsCodeForClientCase sendSmsCodeForClientCase =session.selectOne("sendSmsCodeForClientCase",1);
        System.out.println(sendSmsCodeForClientCase.toString());
        System.out.println(TestConfig.sendSmsCodeForClientUrl);
        String result = getResult(sendSmsCodeForClientCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + sendSmsCodeForClientCase.getExpected());
        System.out.println("预期的状态码为：" + sendSmsCodeForClientCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        if(result.equals("0")){
                ValidateSmsCodeCase validateSmsCodeCase =session.selectOne("validateSmsCodeCase",2);
                validateSmsCodeCase.setPhone(sendSmsCodeForClientCase.getPhone());
                int num = session.update("updateValidateSmsCodeCase",validateSmsCodeCase);
            Thread.sleep(500);
            System.out.println("newPhone: "+validateSmsCodeCase.getPhone());
                 if(num >= 0){
                    System.out.println("更新手机号条数："+ num);
                    }
            session.commit();
        }


        Assert.assertEquals(result,sendSmsCodeForClientCase.getStatus());

    }
    private String getResult(SendSmsCodeForClientCase sendSmsCodeForClientCase) throws IOException {

        HttpGet httpGet = new HttpGet(TestConfig.sendSmsCodeForClientUrl+
                "?phone=" +sendSmsCodeForClientCase.getPhone()+
                "&isEncrypt="+sendSmsCodeForClientCase.getIsEncrypt());
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
