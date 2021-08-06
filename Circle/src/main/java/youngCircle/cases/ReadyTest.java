package com.youngCircle.cases;


import com.youngCircle.config.TestConfig;
import com.youngCircle.model.InterfaceName;
import com.youngCircle.utils.ConfigFile;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.Test;

public class ReadyTest {
    @Test(description = "测试准备工作,获取HttpClient对象,获取请求的地址")
    public void beforeTest(){
        //创建DefaultHTTPClient对象
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        //获取登录接口的完整URL地址
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        //1.获取广场顶部列表接口的URL地址
        TestConfig.squareListUrl = ConfigFile.getUrl(InterfaceName.SQUARELIST);
        //2.添加关注接口的URL地址
        TestConfig.addFollowUrl = ConfigFile.getUrl(InterfaceName.ADDFOLLOW);
        //5.取消关注接口URL地址
        TestConfig.delFollowUrl = ConfigFile.getUrl(InterfaceName.DELFOLLOW);
    }
}
