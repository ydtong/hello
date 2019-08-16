package com.allqj.virtual_number_administrate.util.redis;


import java.util.concurrent.TimeUnit;

public interface IRedis<Key, Value> {
    Value get(String appName, Key key);

    void set(String appName, Key key, Value value);

    void set(String appName, Key key, Value value, int expireTime, TimeUnit timeUnit);

    void delete(String appName, Key key);
}
