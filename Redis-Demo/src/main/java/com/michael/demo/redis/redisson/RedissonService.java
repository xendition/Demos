package com.michael.demo.redis.redisson;

import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("redissonService")
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    public void getRedissonClient() throws IOException {
        Config config = this.redissonClient.getConfig();
        System.out.println(config.toJSON().toString());
    }

    /**
     * `
     * 获取字符串对象
     */
    public <T> RBucket<T> getRBucket(String objectName) {
        RBucket<T> bucket = this.redissonClient.getBucket(objectName);
        return bucket;
    }

    /**
     * 获取Map对象
     */
    public <K, V> RMap<K, V> getRMap(String objectName) {
        RMap<K, V> map = this.redissonClient.getMap(objectName);
        return map;
    }

    /**
     * 获取有序集合
     */
    public <V> RSortedSet<V> getRSortedSet(String objectName) {
        RSortedSet<V> sortedSet = this.redissonClient.getSortedSet(objectName);
        return sortedSet;
    }

    /**
     * 获取集合
     */
    public <V> RSet<V> getRSet(String objectName) {
        RSet<V> rSet = this.redissonClient.getSet(objectName);
        return rSet;
    }

    /**
     * 获取列表
     */
    public <V> RList<V> getRList(String objectName) {
        RList<V> rList = this.redissonClient.getList(objectName);
        return rList;
    }

    /**
     * 获取队列
     */
    public <V> RQueue<V> getRQueue(String objectName) {
        RQueue<V> rQueue = this.redissonClient.getQueue(objectName);
        return rQueue;
    }

    /**
     * 获取双端队列
     */
    public <V> RDeque<V> getRDeque(String objectName) {
        RDeque<V> rDeque = this.redissonClient.getDeque(objectName);
        return rDeque;
    }


    /**
     * 获取锁
     */
    public RLock getRLock(String objectName) {
        RLock rLock = this.redissonClient.getLock(objectName);
        return rLock;
    }

    /**
     * 获取读取锁
     */
    public RReadWriteLock getRWLock(String objectName) {
        RReadWriteLock rwlock = this.redissonClient.getReadWriteLock(objectName);
        return rwlock;
    }

    /**
     * 获取原子数
     */
    public RAtomicLong getRAtomicLong(String objectName) {
        RAtomicLong rAtomicLong = this.redissonClient.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 获取记数锁
     */
    public RCountDownLatch getRCountDownLatch(String objectName) {
        RCountDownLatch rCountDownLatch = this.redissonClient.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     * 获取消息的Topic
     */
    public RTopic getRTopic(String objectName) {
        RTopic rTopic = this.redissonClient.getTopic(objectName);
        return rTopic;
    }
}
