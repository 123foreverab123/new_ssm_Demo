package com.qf.filter;

import com.qf.pojo.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       //1.查看Session里面有没有用户信息
        HttpSession session=httpServletRequest.getSession();
        //获取用户信息看看有没有
        UserInfo userInfo= (UserInfo) session.getAttribute("userInfo");
       if(userInfo==null){
           //根据URI进行进一步判断，如果是请求登录和注册，就返回true，否则就返回false
           String requestURI=httpServletRequest.getRequestURI();
           if(requestURI.equals("/loginCheck")||requestURI.equals("/register")||requestURI.equals("/login.html")||requestURI.equals("/reg.html")){
               return  true;
           }else {
               return  false;
           }
       }else{
           return false;
       }

    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
