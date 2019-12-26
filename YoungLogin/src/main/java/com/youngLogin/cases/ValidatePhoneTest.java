package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.InterfaceName;
import com.youngLogin.model.ValidatePhoneCase;
import com.youngLogin.utils.ConfigFile;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

//验证手机号接口
public class ValidatePhoneTest {

    @Test(description = "手机号被占用")
    public void validatePhone1() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase", 1);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());
    }
    @Test(description = "该手机号未注册")
    public void validatePhone2() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",2);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }
    @Test(description = "手机号格式错误")
    public void validatePhone3() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",3);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }
    @Test(description = "手机号格式错误")
    public void validatePhone4() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",4);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }
    @Test(description = "手机号格式错误（手机号为空）")
    public void validatePhone5() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",5);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }
    private String getResult(ValidatePhoneCase validatePhoneCase) throws IOException {

        HttpGet httpGet = new HttpGet(TestConfig.validatePhoneUrl+
                "?phone=" +validatePhoneCase.getPhone()+
                "&isEncrypt="+validatePhoneCase.getIsEncrypt());
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
