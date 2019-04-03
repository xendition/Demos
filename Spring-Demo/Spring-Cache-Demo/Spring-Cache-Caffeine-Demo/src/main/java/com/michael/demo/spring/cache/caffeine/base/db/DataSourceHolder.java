package com.michael.demo.spring.cache.caffeine.base.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库切换工具类
 *
 * @author Michael
 * @date 2018/6/7.
 */
public class DataSourceHolder {

    private static Logger logger = LoggerFactory.getLogger(DataSourceHolder.class);

    private static final ThreadLocal<DataSourceType> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void master() {
        setDataSource(DataSourceType.MASTER);
    }

    public static void slave() {
        setDataSource(DataSourceType.SLAVE);
    }

    public static void setDataSource(DataSourceType dataSource) {
        if (dataSource == null) {
            throw new NullPointerException();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("当前数据源切换为 -> {}", dataSource);
        }
        CONTEXT_HOLDER.set(dataSource);
    }

    public static DataSourceType getDataSource() {
        return CONTEXT_HOLDER.get() == null ? DataSourceType.MASTER : CONTEXT_HOLDER.get();
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

    private DataSourceHolder() {
    }
}
