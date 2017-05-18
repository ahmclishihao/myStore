package com.taotao.sso.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    TaotaoResult checkData(String param, Integer type) throws Exception;


    TaotaoResult saveUser(TbUser user) throws Exception;


    TaotaoResult selectUserLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    TaotaoResult selectByToken(String token, HttpServletRequest request, HttpServletResponse response) throws Exception;

    TaotaoResult userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
