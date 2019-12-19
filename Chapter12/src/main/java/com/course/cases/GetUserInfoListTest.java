package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {

    @Test(dependsOnGroups="loginTrue",description = "获取性别为男的用户信息")
    public void getUserListInfo() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);
        //发送请求  返回结果
        JSONArray resultArray = getJsonResult(getUserListCase);

        //验证结果
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User users : userList){
            System.out.println("获取的用户列表为：" + users.toString());
        }
        JSONArray userListArray = new JSONArray(userList);
        Assert.assertEquals(userListArray.length(),resultArray.length());
        for(int i =0;i<resultArray.length();i++){
           JSONObject expect = (JSONObject) resultArray.get(i);
            JSONObject actual = (JSONObject) userListArray.get(i);
           //报错伏笔，需要加入toString
           Assert.assertEquals(expect.toString(),actual.toString());
        }
    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());

        //设置header信息
        post.setHeader("content-type","application/json");
        StringEntity entity =new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //设置cookie信息
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray resultArray = new JSONArray(result);
        System.out.println("调用接口 List Result ：" + result);
        return  resultArray;

    }
}
