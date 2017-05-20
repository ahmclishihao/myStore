package com.taotao.order.jedis;

public interface JedisGlobalClient {

    Long decr(String key);

    Long incr(String key);

    String set(String key, String value);
    String get(String key);
    Long hset(String key, String filedname, String value);
    String hget(String key, String filed);
    Long expire(String key, int time);
    Long ttl(String key);
    Long del(String key);
    Long hdel(String key, String filed);
}
