package com.youngLogin.model;

import lombok.Data;

@Data
public class SetPasswordCase {
    private int id;
    private String password;
    private String isEncrypt;
    private String status;
    private String expected;
}
