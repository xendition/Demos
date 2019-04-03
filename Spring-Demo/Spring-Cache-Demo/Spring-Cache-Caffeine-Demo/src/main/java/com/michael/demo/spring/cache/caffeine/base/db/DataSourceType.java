package com.michael.demo.spring.cache.caffeine.base.db;

/**
 * 数据源类型(可自行扩展)
 *
 * @author Michael
 * @date 2018/6/12.
 */
public enum DataSourceType {
    /**
     * 主数据库
     */
    MASTER,
    /**
     * 从数据库
     */
    SLAVE
}
