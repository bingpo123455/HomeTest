package com.youngLogin.cases;

import com.sun.deploy.util.ArrayUtil;
import com.youngLogin.config.TestConfig;
import com.youngLogin.model.InterfaceName;
import com.youngLogin.model.LoginCase;
import com.youngLogin.model.RegisterCase;
import com.youngLogin.utils.ConfigFile;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
//注册接口(用户通过手机号密码注册)
public class RegisterTest {

    @Test(description = "手机号已被占用")
    public void register1() throws IOException, InterruptedException {
       SqlSession session =  DatabaseUtil.getSqlSession();
       RegisterCase registerCase = session.selectOne("registerCase",1);
        System.out.println(registerCase.toString());
        System.out.println(TestConfig.registerUrl);
        //发送请求，返回结果
        String  result = getResult(registerCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + registerCase.getExpected());
        System.out.println("预期的状态码为：" + registerCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,registerCase.getStatus());
    }

    @Test(enabled = false,description = "注册接口")
    public void register2() throws IOException, InterruptedException {
        SqlSession session =  DatabaseUtil.getSqlSession();
        RegisterCase registerCase = session.selectOne("registerCase",2);
       String phone = registerCase.getPhone();
//        int i = Integer.parseInt(phone.substring(phone.length()-3,phone.length()));
//        System.out.println(i);
        System.out.println(registerCase.toString());
        System.out.println(TestConfig.registerUrl);
        //发送请求，返回结果
        String  result = getResult(registerCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + registerCase.getExpected());
        System.out.println("预期的状态码为：" + registerCase.getStatus());
        System.out.println("返回实际状态码为：" + result);
        //返回结果和实际结果做验证
        Assert.assertEquals(result,registerCase.getStatus());
//        phone=phone.substring(0,8)+i;
//        i=i++;



        registerCase.setPhone(String.valueOf(Long.parseLong(phone) +1));
        System.out.println(registerCase.toString());
        Thread.sleep(2000);
        int getNumber  =session.update("updateRegisterCase",registerCase);
        if(getNumber >= 1){
            System.out.println("更新条数：" + getNumber);
        }
       // session.commit();
    }
    @Test(description = "密码错误(密码为空)")
    public void register3() throws IOException, InterruptedException {
        SqlSession session =  DatabaseUtil.getSqlSession();
        RegisterCase registerCase = session.selectOne("registerCase",3);
        System.out.println(registerCase.toString());
        System.out.println(TestConfig.registerUrl);
        //发送请求，返回结果
        String  result = getResult(registerCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + registerCase.getExpected());
        System.out.println("预期的状态码为：" + registerCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,registerCase.getStatus());
    }
    @Test(description = "请输入6~12位数字字母组合(密码为9位数字组成的)")
    public void register4() throws IOException, InterruptedException {
        SqlSession session =  DatabaseUtil.getSqlSession();
        RegisterCase registerCase = session.selectOne("registerCase",4);
        System.out.println(registerCase.toString());
        System.out.println(TestConfig.registerUrl);
        //发送请求，返回结果
        String  result = getResult(registerCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + registerCase.getExpected());
        System.out.println("预期的状态码为：" + registerCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,registerCase.getStatus());
    }
    @Test(description = "请输入6~12位数字字母组合（密码为全是字母）")
    public void register5() throws IOException, InterruptedException {
        SqlSession session =  DatabaseUtil.getSqlSession();
        RegisterCase registerCase = session.selectOne("registerCase",5);
        System.out.println(registerCase.toString());
        System.out.println(TestConfig.registerUrl);
        //发送请求，返回结果
        String  result = getResult(registerCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + registerCase.getExpected());
        System.out.println("预期的状态码为：" + registerCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,registerCase.getStatus());
    }
    @Test(description = "请输入6~12位数字字母组合（密码为5位字母数字组合）")
    public void register6() throws IOException, InterruptedException {
        SqlSession session =  DatabaseUtil.getSqlSession();
        RegisterCase registerCase = session.selectOne("registerCase",6);
        System.out.println(registerCase.toString());
        System.out.println(TestConfig.registerUrl);
        //发送请求，返回结果
        String  result = getResult(registerCase);
        Thread.sleep(500);
        System.out.println("测试预期结果：" + registerCase.getExpected());
        System.out.println("预期的状态码为：" + registerCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

        //返回结果和实际结果做验证
        Assert.assertEquals(result,registerCase.getStatus());
    }

    private String  getResult(RegisterCase registerCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.registerUrl);
        JSONObject param = new JSONObject();
        param.put("phone",registerCase.getPhone());
        param.put("password",registerCase.getPassword());
        param.put("picCode",registerCase.getPicCode());
        param.put("isEncrypt",registerCase.getIsEncrypt());
        param.put("deviceid",registerCase.getDeviceid());

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
       // post.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");
       // post.setHeader("Accept-Charset","UTF-8");
       // post.setHeader("guestId","lalalalalalalalalalala");
        //TestConfig.postHeader();
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

       // TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return status;
    }
}
