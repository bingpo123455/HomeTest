package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.LoginCase;
import com.youngLogin.model.ResetPasswordCase;
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


//修改密码接口（需要短信验证）
public class ResetPasswordTest {
    @Test(enabled = false,dependsOnGroups = "sendSuccess",description = "验证失败")
    public void resetPassword1() throws IOException, InterruptedException {
      SqlSession session = DatabaseUtil.getSqlSession();
      ResetPasswordCase resetPasswordCase = session.selectOne("resetPasswordCase",1);
        System.out.println(resetPasswordCase.toString());
        System.out.println(TestConfig.resetPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(resetPasswordCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + resetPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + resetPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,resetPasswordCase.getStatus());
    }
    @Test(enabled = false,dependsOnGroups = "sendSuccess",description = "验证码正确")
    public void resetPassword2() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        Thread.sleep(30000);
        ResetPasswordCase resetPasswordCase = session.selectOne("resetPasswordCase",2);
        System.out.println(resetPasswordCase.toString());
        System.out.println(TestConfig.resetPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(resetPasswordCase);
        System.out.println("测试预期结果：" + resetPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + resetPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        if(result.equals("0")){
            LoginCase loginCase = session.selectOne("loginCase",1);
            loginCase.setPassword(resetPasswordCase.getPassword());
            int num = session.update("updateLoginCase",loginCase);
            if(num >= 0){
                System.out.println("更新密码条数："+ num);
            }
            session.commit();
        }

        //返回结果和实际结果做验证
        Assert.assertEquals(result,resetPasswordCase.getStatus());
    }
    private String  getResult(ResetPasswordCase resetPasswordCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.resetPasswordUrl);
        JSONObject param = new JSONObject();
        param.put("phone",resetPasswordCase.getPhone());
        param.put("password",resetPasswordCase.getPassword());
        param.put("smsCode",resetPasswordCase.getSmsCode());
        param.put("isEncrypt",resetPasswordCase.getIsEncrypt());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        post.setHeader("token",TestConfig.token);
        post.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");

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
