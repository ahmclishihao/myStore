package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    TbUser getUserByToken(HttpServletRequest request) throws Exception;
            ;

}
