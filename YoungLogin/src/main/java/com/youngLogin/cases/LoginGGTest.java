package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.LoginGGCase;
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

public class LoginGGTest {
    @Test(dependsOnGroups = "loginTrue",description = "逛逛接口")
    public void loginGG1() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginGGCase loginGGCase = session.selectOne("loginGGCase",1);
        System.out.println(loginGGCase.toString());
        System.out.println(TestConfig.loginGGUrl);
        //发送请求，返回结果
        String  result = getResult(loginGGCase);
        System.out.println("测试预期结果：" + loginGGCase.getExpected());
        System.out.println("预期的状态码为：" + loginGGCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,loginGGCase.getStatus());
    }
    private String  getResult(LoginGGCase loginGGCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginGGUrl);
        JSONObject param = new JSONObject();
        param.put("gender",loginGGCase.getGender());
        param.put("age",loginGGCase.getAge());
        param.put("parentType",loginGGCase.getParentType());
        param.put("labels",loginGGCase.getLabels());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        post.setHeader("token",TestConfig.token);
        post.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");
        post.setHeader("guestid","lalalalalalalalalalala");

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
