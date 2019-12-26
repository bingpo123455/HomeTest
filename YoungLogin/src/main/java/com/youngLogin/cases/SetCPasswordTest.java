package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.LoginCase;
import com.youngLogin.model.RegisterCase;
import com.youngLogin.model.SetCPasswordCase;
import com.youngLogin.model.VerifyCPasswordCase;
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

//设置陪伴密码接口(用于设置陪伴密码（家长密码，需要登录状态下)
public class SetCPasswordTest {
    @Test( enabled = false,dependsOnGroups = "loginTrue",description = "输入的新密码与原密码一致，请重新输入")
    public void setCPassword1() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        SetCPasswordCase setCPasswordCase = session.selectOne("setCPasswordCase",1);
        System.out.println(setCPasswordCase.toString());
        System.out.println(TestConfig.setCPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(setCPasswordCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + setCPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + setCPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
            if(result.equals("1323")){

               String newCpassword = String.valueOf(Integer.parseInt(setCPasswordCase.getCpassword())+1);
               setCPasswordCase.setCpassword(newCpassword);
               int num = session.update("updatePassword",setCPasswordCase);
               if(num >=1){
                   System.out.println("更新密码条数为：" + num);
               }
               session.commit();
            }
        //返回结果和实际结果做验证
        Assert.assertEquals(result,setCPasswordCase.getStatus());
        //包含登录成功判断
        //Assert.assertTrue(result.contains("登录成功"));
    }
    @Test(enabled = false,dependsOnGroups = "loginTrue",description = "操作成功（修改完成）")
    public void setCPassword2() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        SetCPasswordCase setCPasswordCase = session.selectOne("setCPasswordCase",2);
        System.out.println(setCPasswordCase.toString());
        System.out.println(TestConfig.setCPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(setCPasswordCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + setCPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + setCPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        if(result.equals("0")){
            VerifyCPasswordCase verifyCPasswordCase = session.selectOne("verifyCPasswordCase",1);
            verifyCPasswordCase.setCpassword(setCPasswordCase.getCpassword());
            int i =session.update("updateVerifyCPasswordCase",verifyCPasswordCase);
            if(i >=1){
                System.out.println("验证库里更新条数：：" + i);
            }
            session.commit();
            String newCpassword = String.valueOf(Integer.parseInt(setCPasswordCase.getCpassword())+1);
            setCPasswordCase.setCpassword(newCpassword);
            int num = session.update("updatePassword",setCPasswordCase);
            if(num >=1){
                System.out.println("更新密码条数为：" + num);
            }
            session.commit();
        }
        //返回结果和实际结果做验证
        Assert.assertEquals(result,setCPasswordCase.getStatus());
        //包含登录成功判断
        //Assert.assertTrue(result.contains("登录成功"));
    }
    @Test(dependsOnGroups = "loginTrue",description = "操作成功（修改完成）")
    public void setCPassword3() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        SetCPasswordCase setCPasswordCase = session.selectOne("setCPasswordCase",3);
        System.out.println(setCPasswordCase.toString());
        System.out.println(TestConfig.setCPasswordUrl);
        //发送请求，返回结果
        String  result = getResult(setCPasswordCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + setCPasswordCase.getExpected());
        System.out.println("预期的状态码为：" + setCPasswordCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,setCPasswordCase.getStatus());
        //包含登录成功判断
        //Assert.assertTrue(result.contains("登录成功"));
    }
    private String  getResult(SetCPasswordCase setCPasswordCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.setCPasswordUrl);
        JSONObject param = new JSONObject();
        param.put("cpassword",setCPasswordCase.getCpassword());
        param.put("isEncrypt",setCPasswordCase.getIsEncrypt());

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
