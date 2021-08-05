package com.young.cases;

import com.young.config.TestConfig;
import com.young.modle.InterfaceName;
import com.young.modle.ValidatePhoneCase;
import com.young.utils.ConfigFile;
import com.young.utils.DatabaseUtil;
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
//1.验证手机号接口
public class ValidatePhoneTest {

    @Test(dependsOnGroups = "loginTrue",description = "校验正确（手机号被占用）")
    public void validatePhone1() throws IOException {
       SqlSession session = DatabaseUtil.getSqlSession();
      ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",1);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }
    @Test(dependsOnGroups = "loginTrue",description = "校验正确（手机号可用）")
    public void validatePhone2() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",2);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }
    @Test(dependsOnGroups = "loginTrue",description = "校验正确（手机号被占用）")
    public void validatePhone3() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",3);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }
    @Test(dependsOnGroups = "loginTrue",description = "校验正确（手机号被占用）")
    public void validatePhone4() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        ValidatePhoneCase validatePhoneCase = session.selectOne("validatePhoneCase",4);
        System.out.println(validatePhoneCase.toString());
        System.out.println(TestConfig.validatePhoneUrl);

        String result = getResult(validatePhoneCase);
        System.out.println("测试预期结果：" + validatePhoneCase.getExpected());
        System.out.println("预期的状态码为：" + validatePhoneCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        Assert.assertEquals(result,validatePhoneCase.getStatus());

    }

    private String getResult(ValidatePhoneCase validatePhoneCase) throws IOException {

       HttpGet httpGet = new HttpGet(TestConfig.validatePhoneUrl+
                                                "?phone=" +validatePhoneCase.getPhone()+
                                                                "&isEncrypt"+validatePhoneCase.getIsEncrypt());
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
