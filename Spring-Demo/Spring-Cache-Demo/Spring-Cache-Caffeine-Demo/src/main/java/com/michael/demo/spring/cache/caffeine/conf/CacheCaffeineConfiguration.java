package com.michael.demo.spring.cache.caffeine.conf;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Caffeine 配置
 *
 * @author Michael
 */
@Configuration
public class CacheCaffeineConfiguration {

    public static final int DEFAULT_MAXSIZE = 10000;
    public static final int DEFAULT_TTL = 600;

    /**
     * 必须要指定这个Bean，refreshAfterWrite=5s这个配置属性才生效
     */
    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        return new CacheLoader<Object, Object>() {

            @Override
            public Object load(Object o) throws Exception {
                return null;
            }

            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                return oldValue;
            }
        };
    }

    /**
     * 创建基于Caffeine的Cache Manager
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        //把各个cache注册到cacheManager中，CaffeineCache实现了org.springframework.cache.Cache接口
        ArrayList<CaffeineCache> caches = new ArrayList<>();
        for (Caches c : Caches.values()) {
            caches.add(new CaffeineCache(
                               c.name(),
                               Caffeine.newBuilder().recordStats()
                                       .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                                       .maximumSize(c.getMaxSize())
                                       .build()
                       )
            );
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    /**
     * 定义cache名称、超时时长秒、最大个数
     * 每个cache缺省3600秒过期，最大个数1000
     */
    public enum Caches {
        user(60, 2),
        info(5),
        role;

        private int maxSize = DEFAULT_MAXSIZE;    //最大數量
        private int ttl = DEFAULT_TTL;        //过期时间（秒）

        Caches() {
        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        public int getMaxSize() {
            return this.maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public int getTtl() {
            return this.ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }
    }
}
