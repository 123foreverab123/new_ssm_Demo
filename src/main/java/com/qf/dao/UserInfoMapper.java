package com.qf.dao;

import com.qf.pojo.UserInfo;
import com.qf.vo.RoleInfo;

import java.util.List;

public interface UserInfoMapper {
    //登录验证
    public UserInfo checkUser(UserInfo userInfo);
    //注册用户
    public int registerUser(UserInfo userInfo);
    //查询所有得用户信息
     public List<UserInfo> getAllUser();
     //删除用户信息
    public int deleteUser(UserInfo id);
    //修改用户信息
    public int updateUser(UserInfo userInfo);

}
