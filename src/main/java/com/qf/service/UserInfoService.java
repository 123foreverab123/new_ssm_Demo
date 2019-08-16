package com.qf.service;

import com.qf.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    //登录验证
    public UserInfo checkUser(UserInfo userInfo);

    //注册用户
     public boolean registerUser(UserInfo userInfo);
     //获取所有用户
     public List<UserInfo> getAllUser();
     //删除某一用户
    public int deleteUser(UserInfo id);
    //修改某一用户的信息
    public int updateUser(UserInfo userInfo);
}
