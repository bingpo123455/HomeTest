package com.youngCircle.model;

import lombok.Data;

@Data
public class AddFollowCase {
    private int id;
    private int uid;
    private int fuid;
    private String followuid;
    private String status;
    private String expected;
    private int r;
}
