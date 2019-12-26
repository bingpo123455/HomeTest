package com.youngLogin.model;

import lombok.Data;

@Data
public class CaptchaCase {
    private int id;
    private String status;
    private String expected;
}
