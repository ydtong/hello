package com.allqj.virtual_number_administrate.util.redis.impl;


import com.allqj.virtual_number_administrate.util.redis.IRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class StringRedisTemplateImpl implements IRedis<String, String> {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String get(String appName, String key) {
        return stringRedisTemplate.opsForValue().get(appName + "_" + key);
    }

    @Override
    public void set(String appName, String key, String value) {
        stringRedisTemplate.opsForValue().set(appName + "_" + key, value);
    }

    @Override
    public void set(String appName, String key, String value, int expireTime, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(appName + "_" + key, value, expireTime, timeUnit);
    }

    @Override
    public void delete(String appName, String key) {
        stringRedisTemplate.opsForValue().set(appName + "_" + key, "", 1, TimeUnit.MILLISECONDS);
    }
}
