package com.youngLogin.model;

import lombok.Data;

@Data
public class InvalidUserCase {
    private int id;
    private int uid;
    private String status;
    private String expected;
}
