package com.qf.service;

import com.qf.dto.RoleAndUserDto;
import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.vo.MenuInfoVO;
import com.qf.vo.RoleInfo;

import java.util.List;

public interface RoleInfoService {
    //查询角色
    public List<RoleInfo> selectUserById(int userid);
    //角色增加的方法
    public boolean  addMenuInfo(RoleAndUserDto menuInfoDto);
    //角色删除的方法
    public boolean deleteMenuInfo(RoleAndUserDto menuInfoDto);
    //展现所有父角色得信息
    public List<com.qf.pojo.RoleInfo> showRoleInfo();
    //用户登录成功后，，在user.html页面的左侧展现出用户的角色
    public List<MenuInfo> showUserRoleMenu(UserInfo userInfo);
}
