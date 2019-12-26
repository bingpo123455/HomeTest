package com.youngLogin.model;

import lombok.Data;

@Data
public class ValidateCaptchaForClientCase {
    private int id;
    private String phone;
    private String picCode;
    private String isEncrypt;
    private String status;
    private String expected;
}
