package com.youngCircle.model;

import lombok.Data;

@Data
public class DelUserFollowCase {
    private int id;
    private String followuid;
    private String status;
    private String expected;
}
