package com.allqj.virtual_number_administrate.util.distributedLocks;

public interface IRedissonService {
    /**
     * 开启锁
     *
     * @param key
     * @return
     */
    boolean lock(String key);

    /**
     * 关闭所
     *
     * @param key
     * @return
     */
    boolean free(String key);
}
