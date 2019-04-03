package com.michael.demo.spring.cache.caffeine.base.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author Michael
 * @date 2018/6/7.
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private static Logger log = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    /**
     * 数据源 KEY 值逻辑处理方法,可按业务需求更新(比如数据库读写分离/分库等)
     */
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSource = DataSourceHolder.getDataSource();
        if (log.isDebugEnabled()) {
            log.debug("当前数据源 -> {}", dataSource.name());
        }
        return dataSource;
    }
}
