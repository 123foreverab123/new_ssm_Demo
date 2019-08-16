package com.qf.controller;

import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.service.RoleInfoService;
import com.qf.service.UserInfoService;
import com.qf.toolkit.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    RoleInfoService roleInfoService;

    //用于登录验证
    //@RequestMapping可以写在类上也可以写在方法上
    //get：路径和超链接请求都是get请求--@RequestMapping(value="login" ,method = RequestMethod.GET)
    //post：必须在表单中实现
    @RequestMapping("loginCheck" )
    //@ResponseBody：该方法得返回值（无论什么类型），都已jison字符串得形式返回
    public boolean checkUser(@RequestBody UserInfo userInfo, HttpSession httpSession){
        String s = Md5.ancodePassword(userInfo.getPassword());
        userInfo.setPassword(s);
        UserInfo userInfo1 = userInfoService.checkUser(userInfo);
        if(userInfo1!=null){
            httpSession.setAttribute("userInfo",userInfo1);
            List<MenuInfo> menuInfos = roleInfoService.showUserRoleMenu(userInfo);
            httpSession.setAttribute("menuInfos",menuInfos);
        }
        return userInfo1!=null;
    }

    //用户注册
    @RequestMapping("register")
    public boolean registerUser(@RequestBody UserInfo userInfo){
        String s = Md5.ancodePassword(userInfo.getPassword());
        userInfo.setPassword(s);
        boolean b = userInfoService.registerUser(userInfo);
        return b;
    }

    //获取所有用户并展示
    @RequestMapping("showUser")
    public List<UserInfo> getAllUser(){
        List<UserInfo> allUser = userInfoService.getAllUser();
        return allUser;
    }
    //删除用户信息
    @RequestMapping("deleteUser")
    public boolean deleteUser(@RequestBody UserInfo id){
        int i = userInfoService.deleteUser(id);
        return i>0;
    }
    @RequestMapping("updateUser")
    public boolean updateUser(@RequestBody UserInfo cc){
        System.out.println(cc.getUserid());
        int i = userInfoService.updateUser(cc);
        return i>0;
    }
    //查询用户
    @RequestMapping("selectUserName")
    public boolean selectUserName(@RequestParam String userName){
        System.out.println(userName);
        return true;
    }
    //测试一个新的传输方式
    @RequestMapping("pathVariableTest/{name}-{password}")
    public Object pathVariableTest(@PathVariable("name") String username,@PathVariable("password") String password){
        System.out.println(username+password);
        return username;
}

}
