package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dto.RoleMenuDto;
import com.qf.pojo.MenuInfo;
import com.qf.service.MenuInfoService;
import com.qf.vo.MenuInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuInfoController {
    @Autowired
    MenuInfoService menuInfoService;
    @RequestMapping("ListMenuByRoleid")
    public Object ListMenuByRoleid(@RequestParam int roleid){
        System.out.println(roleid);
        List<MenuInfoVO> menuInfoVOS = menuInfoService.ListMenuByRoleid(roleid);
        return menuInfoVOS;
    }

    @RequestMapping("RoleMenu")
    public Object RoleMenu(@RequestBody RoleMenuDto roleMenuDto){
        System.out.println(roleMenuDto);
        boolean b = menuInfoService.RoleMenu(roleMenuDto);
        return b;
    }
    //查找出所有的菜单，然后在permission.xml页面上进行显示
    @RequestMapping("listAllMenu")
    public Object selectAllMenu(@RequestParam(required = true,defaultValue ="1",value = "pageNum")Integer pageNum) {
        //先定义一页有多少条
        int defaultPageSize=4;
        //初始化pageHelper对象
        PageHelper.startPage(pageNum,defaultPageSize);
        List<MenuInfo> menuInfos = menuInfoService.selectAllMenu();
        PageInfo<MenuInfo> menuInfoPageInfo=new PageInfo<MenuInfo>(menuInfos);
        return menuInfoPageInfo;
    }

}
