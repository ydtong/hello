package com.allqj.virtual_number_administrate.util.distributedLocks.impl;


import com.allqj.virtual_number_administrate.util.distributedLocks.IRedissonService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class RedissonService implements IRedissonService, InitializingBean {

    @Value("#{'${spring.redisson.host}'.split(',')}")
    private String[] host;

    @Value("${redis-sentinel.master}")
    private String sentinelMaster;

    @Value("${spring.redis.password}")
    private String redisPassword;

    private static final String keyPrefix = "RedissonKey-";
    private static final Integer leaseTime = 20;

    private Redisson redisson;

    /**
     * 开启锁
     *
     * @param key
     * @return
     */
    @Override
    public boolean lock(String key) {
        RLock mylock = redisson.getLock(keyPrefix + key);
        mylock.lock(leaseTime, TimeUnit.SECONDS);
        System.out.println("开启锁");
        return false;
    }

    /**
     * 关闭所
     *
     * @param key
     * @return
     */
    @Override
    public boolean free(String key) {
        RLock mylock = redisson.getLock(keyPrefix + key);
        mylock.unlock();
        System.out.println("关闭锁");
        return false;
    }

    /**
     * 初始化分布式锁
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Config config = new Config();
        if (host.length > 1) {
            config.useSentinelServers().addSentinelAddress(host);
            config.useSentinelServers().setMasterName(sentinelMaster);
            config.useSentinelServers().setPassword(redisPassword);
        } else
            config.useSingleServer().setAddress(host[0]);
        this.redisson = (Redisson) Redisson.create(config);
    }
}
