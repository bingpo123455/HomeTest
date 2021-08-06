package com.youngCircle.cases;

import com.youngCircle.config.TestConfig;
import com.youngCircle.model.AddFollowCase;
import com.youngCircle.model.basicmodel.UserFollow;
import com.youngCircle.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

//添加关注接口(用于添加关注或者达人信息)
public class AddFollowTest {
    @Test(enabled = true,dependsOnGroups = "loginTrue", description = "获取到返回数据")
    public void addFollow1() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        AddFollowCase addFollowCase = session.selectOne("addFollowCase", 1);
        System.out.println(addFollowCase.toString());
        System.out.println(TestConfig.addFollowUrl);
        addFollowCase.setR(1);
        //发送请求请求之前  去数据库查询关注和粉丝 的总数
        List<UserFollow> userFollowList  = session.selectList(addFollowCase.getExpected(), addFollowCase);
        JSONArray jsonArrayList = new JSONArray(userFollowList);
        System.out.println("请求前关注数为："+jsonArrayList.length());
        int guanzhu = jsonArrayList.length();
        List<UserFollow> userFollowList1  = session.selectList(addFollowCase.getExpected()+1, addFollowCase);
        JSONArray jsonArrayList1 = new JSONArray(userFollowList1);
        System.out.println("请求前粉丝数为："+jsonArrayList1.length());
        int fensi = jsonArrayList1.length();

        //发送关注请求
        System.out.println(getResult(addFollowCase));

        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        AddFollowCase addFollowCase1 = sqlSession.selectOne("addFollowCase", 1);
        //发送请求请求之后  去数据库查询关注和粉丝 的总数
        List<UserFollow> userFollowList2  = sqlSession.selectList(addFollowCase1.getExpected(), addFollowCase1);
        JSONArray jsonArrayList2 = new JSONArray(userFollowList2);
        System.out.println("请求后关注数为："+jsonArrayList2.length());
        List<UserFollow> userFollowList3  = sqlSession.selectList(addFollowCase1.getExpected()+1, addFollowCase1);
        JSONArray jsonArrayList3 = new JSONArray(userFollowList3);
        System.out.println("请求后粉丝数为："+jsonArrayList3.length());
        //返回结果和实际结果做验证
        Assert.assertEquals(guanzhu+1,jsonArrayList2.length());
        Assert.assertEquals(fensi+1,jsonArrayList3.length());

    }
    @Test(enabled = false,dependsOnGroups = "loginTrue", description = "不能关注自己")
    public void addFollow2() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        AddFollowCase addFollowCase = session.selectOne("addFollowCase", 2);
        System.out.println(addFollowCase.toString());
        System.out.println(TestConfig.addFollowUrl);
        //发送请求，返回结果
        String result = getResult(addFollowCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + addFollowCase.getExpected());
        System.out.println("预期的状态码为：" + addFollowCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result, addFollowCase.getStatus());
    }

    private String  getResult(AddFollowCase addFollowCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.addFollowUrl);
        JSONObject param = new JSONObject();
        param.put("followuid",addFollowCase.getFollowuid());
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

        return result;
    }
}