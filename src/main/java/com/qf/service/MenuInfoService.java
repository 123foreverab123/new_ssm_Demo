package com.qf.service;


import com.qf.dto.RoleMenuDto;
import com.qf.pojo.MenuInfo;
import com.qf.vo.MenuInfoVO;

import java.util.List;

public interface MenuInfoService {
    //根据roleid获取到该父角色对应的子角色
    public List<MenuInfoVO> ListMenuByRoleid(int roleid);
    //在前台assignPermission.html传送来的角色的id信息
      public boolean RoleMenu(RoleMenuDto roleMenuDto);
    //查找出所有的菜单，然后在permission.xml页面上进行显示
    public List<MenuInfo> selectAllMenu();
}
