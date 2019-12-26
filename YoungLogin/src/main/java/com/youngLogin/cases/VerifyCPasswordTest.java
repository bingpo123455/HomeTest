package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.VerifyCPasswordCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


//验证陪伴密码(用于校验登录后的陪伴密码)
public class VerifyCPasswordTest {
    @Test(enabled = false,dependsOnGroups = "loginTrue",description = "验证通过")
    public void verifyCPassword1() throws IOException, InterruptedException {
       SqlSession session = DatabaseUtil.getSqlSession();
       VerifyCPasswordCase verifyCPasswordCase = session.selectOne("verifyCPasswordCase",1);
        System.out.println(verifyCPasswordCase.toString());
        System.out.println(TestConfig.verifyCPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(verifyCPasswordCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + verifyCPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + verifyCPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,verifyCPasswordCase.getStatus());

    }
    @Test(dependsOnGroups = "loginTrue",description = "验证未通过")
    public void verifyCPassword2() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        VerifyCPasswordCase verifyCPasswordCase = session.selectOne("verifyCPasswordCase",2);
        System.out.println(verifyCPasswordCase.toString());
        System.out.println(TestConfig.verifyCPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(verifyCPasswordCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + verifyCPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + verifyCPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,verifyCPasswordCase.getStatus());

    }

    private String getResult(VerifyCPasswordCase verifyCPasswordCase) throws IOException {
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
    }
}
