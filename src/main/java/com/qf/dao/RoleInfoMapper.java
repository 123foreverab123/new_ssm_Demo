package com.qf.dao;

import com.qf.dto.RoleAndUserDto;
import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.vo.MenuInfoVO;
import com.qf.vo.RoleInfo;

import java.util.List;

public interface RoleInfoMapper {
    //根据用户的id来查找出用户对应的角色信息
    public List<RoleInfo> selectUserById(int userid);
    //角色增加的方法
    public int addMenuInfo(RoleAndUserDto menuInfoDto);
    //角色删除的方法
    public int deleteMenuInfo(RoleAndUserDto menuInfoDto);
    //展现所有角色得信息
    public List<com.qf.pojo.RoleInfo> showRoleInfo();
    //用户登录成功后，，在user.html页面的左侧展现出用户的角色
    public List<MenuInfo> showUserRoleMenu(UserInfo userInfo);
}
