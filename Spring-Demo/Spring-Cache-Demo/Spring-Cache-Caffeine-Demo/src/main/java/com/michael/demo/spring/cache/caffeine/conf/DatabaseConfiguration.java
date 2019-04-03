package com.michael.demo.spring.cache.caffeine.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.michael.demo.spring.cache.caffeine.base.db.DataSourceType;
import com.michael.demo.spring.cache.caffeine.base.db.DynamicRoutingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Spring配置文件,相当于spring的xml配置文件
 * 数据库配置
 *
 * @author Michael
 * @date 2018/6/7.
 */
@Configuration
public class DatabaseConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

    @Bean
    public DynamicRoutingDataSource dataSource() {

        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();

        Map<Object, Object> map = new HashMap<>(8);
        map.put(DataSourceType.MASTER, this.masterDataSource());
        map.put(DataSourceType.SLAVE, this.slaveDataSource());

        dataSource.setTargetDataSources(map);
        dataSource.setDefaultTargetDataSource(this.masterDataSource());

        if (logger.isDebugEnabled()) {
            logger.debug("dataSource 配置完成...");
        }
        return dataSource;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "michael.datasource.druid.master")
    public DataSource masterDataSource() {
        if (logger.isDebugEnabled()) {
            logger.debug("主数据库开始初始化连接...");
        }
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "michael.datasource.druid.slave")
    public DataSource slaveDataSource() {
        if (logger.isDebugEnabled()) {
            logger.debug("从数据库开始初始化连接...");
        }
        return new DruidDataSource();
    }
}
