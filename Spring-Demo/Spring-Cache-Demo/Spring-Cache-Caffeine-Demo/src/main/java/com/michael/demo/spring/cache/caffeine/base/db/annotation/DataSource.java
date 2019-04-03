package com.michael.demo.spring.cache.caffeine.base.db.annotation;


import com.michael.demo.spring.cache.caffeine.base.db.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义当前使用的数据源
 *
 * @author Michael
 * @date 2018/6/11.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    /**
     * 数据源类型 默认使用主数据源
     */
    DataSourceType value() default DataSourceType.MASTER;
}
