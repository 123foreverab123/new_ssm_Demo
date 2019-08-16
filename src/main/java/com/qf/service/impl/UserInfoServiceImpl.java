package com.qf.service.impl;

import com.qf.dao.UserInfoMapper;
import com.qf.pojo.UserInfo;
import com.qf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    public UserInfo checkUser(UserInfo userInfo) {
        UserInfo userInfo1 = userInfoMapper.checkUser(userInfo);
        return userInfo1;
    }

    public boolean registerUser(UserInfo userInfo) {
        int i = userInfoMapper.registerUser(userInfo);
        return i>0;
    }

    public List<UserInfo> getAllUser() {
        List<UserInfo> allUser = userInfoMapper.getAllUser();
        return allUser;
    }

    public int deleteUser(UserInfo id) {
        int i = userInfoMapper.deleteUser(id);
        return i;
    }

    public int updateUser(UserInfo userInfo) {
        int i = userInfoMapper.updateUser(userInfo);
        return i;
    }

}
