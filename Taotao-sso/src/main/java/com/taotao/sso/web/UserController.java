package com.taotao.sso.web;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService mUserService;

    @Value("${portal_address}")
    private String PORTAL_ADDRESS;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
        TaotaoResult result;
        try {
            result = mUserService.checkData(param.trim(), type);
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
        // 支持jsonP
        if (StringUtils.isNotBlank(callback)) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object userRegister(TbUser user) {
        try {
            TaotaoResult result = mUserService.saveUser(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object userLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            TaotaoResult result = mUserService.selectUserLogin(username, password, request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }

    }

    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object userLogin(@PathVariable String token, HttpServletRequest request, HttpServletResponse response, String callback) {
        TaotaoResult result;
        try {
            if (StringUtils.isNotBlank(token))
                result = mUserService.selectByToken(token, request, response);
            else
                result = TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(-1, ExceptionUtil.exception2String(e));
        }
        if (StringUtils.isNotBlank(callback)) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }

    @RequestMapping("/logout")
    public void userLogout(HttpServletRequest request, HttpServletResponse response) {
        try {
            mUserService.userLogout(request, response);
            response.sendRedirect(PORTAL_ADDRESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
