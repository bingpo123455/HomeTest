package com.young.modle;

import lombok.Data;

@Data
public class LoginCase {
    private int id;
    private String phone;
    private String password;
    private String picCode;
    private String isEncrypt;
    private String deviceid;
    private String status;
    private String  expected;
}
