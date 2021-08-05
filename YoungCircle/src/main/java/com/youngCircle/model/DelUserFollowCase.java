package com.youngCircle.model;

import lombok.Data;

@Data
public class DelUserFollow {
    private int id;
    private String followuid;
    private String status;
    private String expected;
}
