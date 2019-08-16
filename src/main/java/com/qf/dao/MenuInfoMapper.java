package com.qf.dao;


import com.qf.dto.RoleMenuDto;
import com.qf.pojo.MenuInfo;
import com.qf.vo.MenuInfoVO;

import java.util.List;

public interface MenuInfoMapper {
    public List<MenuInfoVO> ListMenuByRoleid(int roleid);

    //删除父角色里角色
    public int deleteRoleMenu(RoleMenuDto roleMenuDto);
    //添加父校色里面的子角色
    public int addRoleMenu(RoleMenuDto roleMenuDto);
    //查找出所有的菜单，然后在permission.xml页面上进行显示
    public List<MenuInfo> selectAllMenu();
}
