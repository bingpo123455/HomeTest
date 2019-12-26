package com.youngLogin.model;

import lombok.Data;

@Data
public class ResetPasswordCase {

    private int id;
    private String phone;
    private String password;
    private String smsCode;
    private String isEncrypt;
    private String status;
    private String expected;

}
