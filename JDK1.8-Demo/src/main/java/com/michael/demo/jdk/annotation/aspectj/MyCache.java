package com.michael.demo.jdk.annotation.aspectj;

import java.lang.annotation.*;

/**
 * 缓存注解标识 —— 为"查询业务代码"优雅增加缓存操作
 *
 * @see MyCacheAspect
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCache {

    /**
     * 缓存的KEY值
     */
    String key();
}
