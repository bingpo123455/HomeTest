package com.youngLogin.model;

import lombok.Data;

@Data
public class VerifyCPasswordCase {
    private int id;
    private String cpassword;
    private String isEncrypt;
    private String status;
    private String expected;
}
