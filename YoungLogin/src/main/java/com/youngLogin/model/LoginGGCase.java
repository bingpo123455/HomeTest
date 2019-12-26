package com.youngLogin.model;

import lombok.Data;

@Data
public class LoginGGCase {
    private int id;
    private int gender;
    private int age;
    private int parentType;
    private String labels;
    private String status;
    private String expected;
}
