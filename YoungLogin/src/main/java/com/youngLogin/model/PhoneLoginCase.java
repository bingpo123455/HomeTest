package com.youngLogin.model;

import lombok.Data;

@Data
public class PhoneLoginCase {
    private int id;
    private String gyuid;
    private String gytoken;
    private String deviceid;
    private String status;
    private String expected;
}
