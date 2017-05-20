package com.taotao.portal.web;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private UserService mUserService;

    @Value("${SSO_LOGIN_ADDRESS}")
    private String SSO_LOGIN_ADDRESS;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            TbUser userLogin = mUserService.getUserByToken(request);
            if (userLogin != null){
                request.setAttribute("currentUser",userLogin);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer requestURL = request.getRequestURL();
        // 指明在哪个页面被拦截
        response.sendRedirect(SSO_LOGIN_ADDRESS+"?redirect="+requestURL);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
