package com.youngLogin.model;

import lombok.Data;

@Data
public class SmsLoginCase {
    private int id;
    private String phone;
    private String smsCode;
    private String deviceid;
    private String isEncrypt;
    private String status;
    private String expected;
}
