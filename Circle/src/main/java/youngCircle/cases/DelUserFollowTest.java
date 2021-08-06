package com.youngCircle.cases;

import com.youngCircle.config.TestConfig;
import com.youngCircle.model.AddFollowCase;
import com.youngCircle.model.DelUserFollowCase;
import com.youngCircle.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

//5.取消关注接口
public class DelUserFollowTest {
    @Test(enabled = true,dependsOnGroups = "loginTrue",description = "取消关注成功")
    public void delUserFollow1() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        DelUserFollowCase delUserFollowCase = session.selectOne("delUserFollowCase",1);
        System.out.println(delUserFollowCase.toString());
        System.out.println(TestConfig.delFollowUrl);
        //发送请求，返回结果
        String result = getResult(delUserFollowCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + delUserFollowCase.getExpected());
        System.out.println("预期的状态码为：" + delUserFollowCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result, delUserFollowCase.getStatus());
    }
    private String  getResult(DelUserFollowCase delUserFollowCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.delFollowUrl);
        JSONObject param = new JSONObject();
        param.put("followuid",delUserFollowCase.getFollowuid());
        // param.put("isEncrypt",setCPasswordCase.getIsEncrypt());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        post.setHeader("token",TestConfig.token);
        post.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");
        post.setHeader("Accept-Charset","UTF-8");
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
