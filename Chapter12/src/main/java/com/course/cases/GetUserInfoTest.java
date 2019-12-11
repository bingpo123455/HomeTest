package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;
import java.io.IOException;

public class GetUserInfoTest {


    @Test(dependsOnGroups="loginTrue",description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfoCase",1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);
    }
}
