package com.qf.pojo;

import lombok.Data;

@Data
public class TestPojo {
    private String username;
    private String password;

    public void test(){
        TestPojo testPojo = new TestPojo();
    }
}
