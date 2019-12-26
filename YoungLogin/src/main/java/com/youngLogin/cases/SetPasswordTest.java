package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.LoginCase;
import com.youngLogin.model.SetCPasswordCase;
import com.youngLogin.model.SetPasswordCase;
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


//修改密码接口(用于修改密码(用于登录之后))
public class SetPasswordTest {
    @Test(description = "修改登录之后的密码成功")
    public void setPassword1() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        SetPasswordCase setPasswordCase = session.selectOne("setPasswordCase",1);
        System.out.println(setPasswordCase.toString());
        System.out.println(TestConfig.setPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(setPasswordCase);
        Thread.sleep(700);
        System.out.println("测试预期结果：" + setPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + setPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        if(result.equals("0")){
            LoginCase loginCase = session.selectOne("loginCase",1);
            loginCase.setPassword(setPasswordCase.getPassword());
            int num = session.update("updateLoginCase",loginCase);
            if(num >= 0){
                System.out.println("更新密码条数："+ num);
            }
        }

        //返回结果和实际结果做验证
        Assert.assertEquals(result,setPasswordCase.getStatus());

    }



    private String  getResult(SetPasswordCase setPasswordCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.setPasswordUrl);
        JSONObject param = new JSONObject();
        param.put("password",setPasswordCase.getPassword());
        param.put("isEncrypt",setPasswordCase.getIsEncrypt());

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
