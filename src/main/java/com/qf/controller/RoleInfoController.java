package com.qf.controller;

import com.qf.dto.RoleAndUserDto;
import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.service.RoleInfoService;
import com.qf.vo.MenuInfoVO;
import com.qf.vo.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RoleInfoController {
    @Autowired
    RoleInfoService roleInfoService;

    //根据用户的id来查找出用户对应的角色信息
    @RequestMapping("selectUserById")
    public Object selectUserById(@RequestParam int userid){
        List<RoleInfo> roleInfos = roleInfoService.selectUserById(userid);
        return roleInfos;
    }

    //根据用户的id在页面进行添加角色
    @RequestMapping("addUserAndRole")
    public boolean addUserAndRole(@RequestBody RoleAndUserDto roleAndUserDto){
        boolean b = roleInfoService.addMenuInfo(roleAndUserDto);
        return b;
    }
    //根据用户的id在页面进行删除角色
    @RequestMapping("deeletUserAndRole")
    public boolean deleteUserAndRole(@RequestBody RoleAndUserDto roleAndUserDto){
        boolean b = roleInfoService.deleteMenuInfo(roleAndUserDto);
        return b;
    }

    //展现出所有父角色得信息
    @RequestMapping("showRoleInfo")
    public Object showRoleInfo(){
        List<com.qf.pojo.RoleInfo> roleInfos = roleInfoService.showRoleInfo();
        return roleInfos;
    }
    @RequestMapping("showUserRoleMenu")
    public List<MenuInfo> showUserRoleMenu(@RequestBody(required = false) UserInfo userInfo, HttpSession httpSession){
        System.out.println(userInfo);
        if (httpSession.getAttribute("menuInfos")==null){
            if(userInfo!=null){
                List<MenuInfo> menuInfos = roleInfoService.showUserRoleMenu(userInfo);
                return menuInfos;
            }else {
                return null;
            }
        }else{
               return (List<MenuInfo>) httpSession.getAttribute("menuInfos");
        }

    }

}
