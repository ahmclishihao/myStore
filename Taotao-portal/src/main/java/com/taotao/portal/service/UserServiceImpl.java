package com.taotao.portal.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpUtils;
import com.taotao.pojo.TbUser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Value("${TT_TOKEN}")
    private String TT_TOKEN;
    @Value("${SSO_TOKEN_LOGIN_ADDRESS}")
    private String SSO_TOKEN_LOGIN_ADDRESS;

    @Override
    public TbUser getUserByToken(HttpServletRequest request) throws Exception {
        String tt_token = CookieUtils.getCookieValue(request, TT_TOKEN);
        if (StringUtils.isNotBlank(tt_token)) {
            String jsonData = HttpUtils.GET(SSO_TOKEN_LOGIN_ADDRESS + tt_token);
            if (StringUtils.isNotBlank(jsonData)) {
                TaotaoResult result = TaotaoResult.formatToPojo(jsonData, TbUser.class);
                if (result != null) {
                    return (TbUser) result.getData();
                }
            }
        }
        return null;
    }
}
