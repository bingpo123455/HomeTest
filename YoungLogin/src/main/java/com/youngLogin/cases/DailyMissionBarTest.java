package com.youngLogin.cases;

import com.youngLogin.config.TestConfig;
import com.youngLogin.model.DailyMissionBarCase;
import com.youngLogin.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DailyMissionBarTest {
    @Test(dependsOnGroups = "loginTrue",description = "日常任务进度条接口")
    public void dailyMissionBar1() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        DailyMissionBarCase dailyMissionBarCase =sqlSession.selectOne("dailyMissionBarCase",1);
        System.out.println(dailyMissionBarCase.toString());
        System.out.println(TestConfig.dailyMissionBarUrl);

        String result = getResult(dailyMissionBarCase);
        System.out.println("测试预期结果：" + dailyMissionBarCase.getExpected());
        System.out.println("预期的状态码为：" + dailyMissionBarCase.getStatus());
        System.out.println("返回实际状态码为：" + result);

       Assert.assertEquals(result,dailyMissionBarCase.getStatus());
    }

    private String getResult(DailyMissionBarCase dailyMissionBarCase) throws IOException {
        HttpGet httpGet = new HttpGet(TestConfig.dailyMissionBarUrl);
        //TestConfig.getHeader();

        httpGet.setHeader("content-type","application/json");
        httpGet.setHeader("token",TestConfig.token);
        httpGet.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");

        HttpResponse response =  TestConfig.defaultHttpClient.execute(httpGet);
        String result;
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONObject jsonObject = new JSONObject(result);
        System.out.println(result);
        String status = jsonObject.get("status").toString();
        return status;
    }
}
