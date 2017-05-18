package com.taotao.sso.service;

import com.taotao.common.bean.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JackSonUtil;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.jedis.JedisGlobalClient;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TbUserMapper mUserMapper;

    @Resource
    private JedisGlobalClient mJedisGlobalClient;

    @Value("${TT_TOKEN}")
    private String TT_TOKEN;
    @Value("${TT_TOKEN_EXPIRE}")
    private Integer TT_TOKEN_EXPIRE;


    @Override
    public TaotaoResult checkData(String param, Integer type) throws Exception {
        // type 1:username 2:phone 3:email
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        TaotaoResult result = TaotaoResult.ok(true);
        // 1.参数为空
        if (StringUtils.isBlank(param)) {
            // 2.用户名不能为空
            if (type == 1) {
                result.setMsg("用户名不能为空");
                result.setData(false);
            }
        } else {
            // 3.参数不为空时
            switch (type) {
                case 1: {
                    criteria.andUsernameEqualTo(param);
                    break;
                }
                case 2: {
                    criteria.andPhoneEqualTo(param);
                    break;
                }
                case 3: {
                    criteria.andEmailEqualTo(param);
                    break;
                }
            }
            List<TbUser> tbUsers = mUserMapper.selectByExample(example);
            if (tbUsers.size() != 0) {
                result.setMsg("数据已存在");
                result.setData(false);
            }
        }
        return result;
    }

    @Override
    public TaotaoResult saveUser(TbUser user) throws Exception {
        TaotaoResult result1 = checkData(user.getUsername(), 1);
        TaotaoResult result2 = checkData(user.getPhone(), 2);
        TaotaoResult result3 = checkData(user.getEmail(), 3);
        // 检查是否通过校验和密码是否为空
        if (!(boolean) result1.getData() || !(boolean) result2.getData() || !(boolean) result3.getData() ||
                StringUtils.isBlank(user.getPassword())) {
            return TaotaoResult.build(400, "注册失败，请校验数据后在提交数据.");
        }

        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        // md5加密
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        mUserMapper.insert(user);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult selectUserLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = mUserMapper.selectByExample(example);
        if (tbUsers == null || tbUsers.size() == 0) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        TbUser user = tbUsers.get(0);
        if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
            return TaotaoResult.build(400, "用户名或密码错误");
        }
        user.setPassword(null);

        String token = UUID.randomUUID().toString();
        TaotaoResult result = TaotaoResult.ok(token);
        // 存储到redis中
        try {
            mJedisGlobalClient.set(TT_TOKEN + ":" + token, JackSonUtil.objectToJson(user));
            mJedisGlobalClient.expire(TT_TOKEN + ":" + token, TT_TOKEN_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置cookie
        CookieUtils.setCookie(request, response, TT_TOKEN, token);


        return result;
    }

    @Override
    public TaotaoResult selectByToken(String token, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TbUser tbUser = null;
        try {
            String jsonData = mJedisGlobalClient.get(TT_TOKEN + ":" + token);
            tbUser = JackSonUtil.jsonToPojo(jsonData, TbUser.class);
            mJedisGlobalClient.expire(TT_TOKEN + ":" + token,TT_TOKEN_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok(tbUser);
    }

    @Override
    public TaotaoResult userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, TT_TOKEN);
        if(StringUtils.isNotBlank(cookieValue)){
            mJedisGlobalClient.del(TT_TOKEN+":"+cookieValue);
        }
        return TaotaoResult.ok();
    }


}
