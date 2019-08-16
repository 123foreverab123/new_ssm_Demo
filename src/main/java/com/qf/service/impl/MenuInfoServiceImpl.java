package com.qf.service.impl;

import com.qf.dao.MenuInfoMapper;
import com.qf.dto.RoleAndUserDto;
import com.qf.dto.RoleMenuDto;
import com.qf.pojo.MenuInfo;
import com.qf.service.MenuInfoService;
import com.qf.vo.MenuInfoVO;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {
@Autowired
MenuInfoMapper menuInfoMapper;
    public List<MenuInfoVO> ListMenuByRoleid(int roleid) {
        List<MenuInfoVO> menuInfoVOS = menuInfoMapper.ListMenuByRoleid(roleid);
        return menuInfoVOS;
    }

    public boolean RoleMenu(RoleMenuDto roleMenuDto) {
        ArrayList<Integer> newmenuIds = roleMenuDto.getNewmenuIds();
        ArrayList<Integer> oldmenuIds = roleMenuDto.getOldmenuIds();
        //先删除oldmenuIds里面没有但是newmenuIds里面有的角色信息
        int i1 = menuInfoMapper.deleteRoleMenu(roleMenuDto);
        //定义一个新的ArrayList，然后将然后将oldmenuIds与newmenuIds的差集装在里面
        ArrayList<Integer> list=new ArrayList<Integer>();
        for (int a=0;a<newmenuIds.size();a++){
            for (int b=0;b<oldmenuIds.size();b++){
                if(newmenuIds.get(a)==oldmenuIds.get(b)){
                    newmenuIds.remove(a);
                    a++;
                }
            }
        }
        roleMenuDto.setNewmenuIds(newmenuIds);
        //进行插入操作，插入去掉差集过后的newmenuIds里面对应的角色
        int i = menuInfoMapper.addRoleMenu(roleMenuDto);
        //如果删除和插入都成功，就返回一个true，否则就返回一个false
        if(i1>0&&i>0){
            return true;
        }
        return false;
    }

    public List<MenuInfo> selectAllMenu() {
        List<MenuInfo> menuInfos = menuInfoMapper.selectAllMenu();
        return menuInfos;
    }
}
