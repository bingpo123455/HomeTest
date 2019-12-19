package com.course.cases;


import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AddUserTest {


    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口接口")
    public void addUser() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);
        //发送请求，返回结果
        String result = getResult(addUserCase);
        //验证返回结果
        Thread.sleep(5000);
        User user = session.selectOne("addUser",addUserCase);
        System.out.println("用户信息为:"+ user.toString());
        Assert.assertEquals(addUserCase.getExpected(),result);

    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
        //设置头信息
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response =TestConfig.defaultHttpClient.execute(post);
        String result;
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;
    }
}
