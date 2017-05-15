package com.taotao.rest.jedis;

import javax.annotation.Resource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisGlobalClientImpl implements JedisGlobalClient {
    @Resource
    private JedisPool mJedisPool;

    @Override
    public String set(String key, String value) {
        Jedis resource = mJedisPool.getResource();
        String set = resource.set(key, value);
        resource.close();
        return set;
    }

    @Override
    public String get(String key) {
        Jedis resource = mJedisPool.getResource();
        String s = resource.get(key);
        resource.close();
        return s;
    }

    @Override
    public Long hset(String key, String filedname, String value) {
        Jedis resource = mJedisPool.getResource();
        Long hset = resource.hset(key, filedname, value);
        resource.close();
        return hset;
    }

    @Override
    public String hget(String key, String filed) {
        Jedis resource = mJedisPool.getResource();
        String hget = resource.hget(key, filed);
        resource.close();
        return hget;
    }

    @Override
    public Long expire(String key, int time) {
        Jedis resource = mJedisPool.getResource();
        Long expire = resource.expire(key, time);
        resource.close();
        return expire;
    }

    @Override
    public Long ttl(String key) {
        Jedis resource = mJedisPool.getResource();
        Long ttl = resource.ttl(key);
        resource.close();
        return ttl;
    }

    @Override
    public Long del(String key) {
        Jedis resource = mJedisPool.getResource();
        Long del = resource.del(key);
        resource.close();
        return del;
    }

    @Override
    public Long hdel(String key, String filed) {
        Jedis resource = mJedisPool.getResource();
        Long hdel = resource.hdel(key, filed);
        resource.close();
        return hdel;
    }
}
