package com.qf.service.impl;

import com.qf.dao.MenuInfoMapper;
import com.qf.dao.RoleInfoMapper;
import com.qf.dao.UserInfoMapper;
import com.qf.dto.RoleAndUserDto;
import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.service.RoleInfoService;
import com.qf.vo.MenuInfoVO;
import com.qf.vo.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Autowired
    RoleInfoMapper roleInfoMapper;
    public List<RoleInfo> selectUserById(int userid) {
        List<RoleInfo> roleInfos = roleInfoMapper.selectUserById(userid);
        return roleInfos;
    }

    public boolean addMenuInfo(RoleAndUserDto menuInfoDto) {
        int i = roleInfoMapper.addMenuInfo(menuInfoDto);
        return i>0;
    }

    public boolean deleteMenuInfo(RoleAndUserDto menuInfoDto) {
        int i = roleInfoMapper.deleteMenuInfo(menuInfoDto);
        return i>0;
    }

    public List<com.qf.pojo.RoleInfo> showRoleInfo() {
        List<com.qf.pojo.RoleInfo> roleInfos = roleInfoMapper.showRoleInfo();
        return roleInfos;
    }

    public List<MenuInfo> showUserRoleMenu(UserInfo userInfo) {
        List<MenuInfo> menuInfoVOS = roleInfoMapper.showUserRoleMenu(userInfo);
        return menuInfoVOS;
    }
}
