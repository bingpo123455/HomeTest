package com.youngLogin.model;

import lombok.Data;

@Data
public class WechatLoginCase {
    private int id;
    private String uid;
    private String name;
    private int logintype;
    private String devId;
    private int gender;
    private String iconurl;
    private String city;
    private String prvinice;
    private String country;
    private String birthday;
    private String year;
    private String month;
    private String day;
    private int coin;
    private String status;
    private String expected;
}
