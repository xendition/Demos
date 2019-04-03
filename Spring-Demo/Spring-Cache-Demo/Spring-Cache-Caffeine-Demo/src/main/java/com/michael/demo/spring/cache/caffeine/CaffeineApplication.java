package com.michael.demo.spring.cache.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Caffeine 缓存测试启动类
 *
 * @author Michael
 */
@EnableAsync
@EnableCaching
@SpringBootApplication
@MapperScan("com.michael.demo.spring.cache.caffeine.mapper")
@PropertySource({"classpath:db.properties"})
public class CaffeineApplication {

    public static void main(String[] args) {

        SpringApplication.run(CaffeineApplication.class, args);
    }
}
