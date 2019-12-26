package com.youngLogin.model;

import lombok.Data;

@Data
public class ValidatePhoneCase {
    private int id;
    private String phone;
    private String isEncrypt;
    private String status;
    private String expected;
}
