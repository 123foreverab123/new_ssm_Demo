package com.qf.toolkit;


import org.apache.commons.codec.digest.DigestUtils;

public class Md5 {
    //写一个给密码进行加密的方法
    public static String ancodePassword(String pwd){
        String s= DigestUtils.md5Hex(pwd);
        for (int i=0;i<10;i++){
            s=DigestUtils.md5Hex(s.substring(15));
        }
        return s;
    }
}
