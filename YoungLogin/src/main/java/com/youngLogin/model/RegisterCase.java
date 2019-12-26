package com.youngLogin.model;

import lombok.Data;

@Data
public class RegisterCase {
    private int id;
    private String phone;
    private String password;
    private String picCode;
    private String isEncrypt;
    private String deviceid;
    private String status;
    private String  expected;
}
