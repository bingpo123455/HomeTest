package com.youngCircle.cases;

import com.youngCircle.config.TestConfig;
import com.youngCircle.model.SquareListCase;
import com.youngCircle.model.basicmodel.SquareList;
import com.youngCircle.utils.DatabaseUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

//获取广场顶部列表接口
public class SquareListTest {
    @Test(enabled = false,dependsOnGroups = "loginTrue",description = "根据版本号  和   手机类型  获取 数据")
    public void squareList1() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
       SquareListCase squareListCase = session.selectOne("getSquareListCase",1);
        System.out.println(squareListCase.toString());
        System.out.println(TestConfig.squareListUrl);

        String resultArray = getJsonResult(squareListCase);
        JSONObject jsonObject = new JSONObject(resultArray);

       JSONArray jsonArray = jsonObject.getJSONObject("data"). getJSONArray("list");

        List<SquareList> squareLists = session.selectList(squareListCase.getExpected(),squareListCase);
    JSONArray jsonArrayList = new JSONArray(squareLists);
        System.out.println("数据库："+jsonArrayList.length());

        Assert.assertEquals(jsonArrayList.length(),jsonArray.length());
        for(int i = 0;i<jsonArrayList.length();i++){
            JSONObject expect = (JSONObject)jsonArray.get(i);
            JSONObject actual = (JSONObject)jsonArrayList.get(i);
            System.out.println(expect.toString());
            System.out.println(actual.toString());
            System.out.println("==========");
            Assert.assertEquals(actual.getString("name"),expect.getString("name"));
            Assert.assertEquals(actual.get("slid"),expect.get("slid"));
            Assert.assertEquals(actual.getString("description"),expect.getString("description"));
        }
        //安卓端  展示返回的全部

        //苹果端 需要过略
        // http,chinasoyoung://youngapp.com/applet,
        //  chinasoyoung://youngapp.com/coin_detail,
        //  chinasoyoung://youngapp.com/daily_task
        for(int i = 0;i<jsonArrayList.length();i++){
            JSONObject expect = (JSONObject)jsonArray.get(i);
            if(!expect.getString("url").equals("chinasoyoung://youngapp.com/applet")
                   && !expect.getString("url").equals("chinasoyoung://youngapp.com/coin_detail")
                    && !expect.getString("url").equals("chinasoyoung://youngapp.com/daily_task")){
                System.out.println("苹果端展示：" + i + expect.toString());
            }
        }
       // Assert.assertEquals(actual.get("imgurl"),expect.get("imgurl"));
       // Assert.assertEquals(actual.get("smallimgurl"),expect.get("smallimgurl"));



      /* for(int i=0;i<jsonArray.length();i++){
           JSONObject expect = (JSONObject)jsonArray.get(i);

           System.out.println(expect);
       }*/
       /* System.out.println(jsonArray.length());

        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()){
           JSONObject jo = (JSONObject)iterator.next();
           int sid = jo.getInt(0);
        }*/


    }

    private String getJsonResult(SquareListCase squareListCase) throws IOException {
        HttpGet httpGet = new HttpGet(TestConfig.squareListUrl);

        //设置header信息
      Header[] headers = httpGet.getHeaders("");
        System.out.println(headers.length);
        httpGet.setHeader("content-type","application/json");
        httpGet.setHeader("token",TestConfig.token);
        httpGet.setHeader("User-Agent","Young/3.1.2(com.chinaso.so; build:1910141500; Android:27; phone)");

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println("调用接口 List Result ：" + result);
        return  result;

    }
}
