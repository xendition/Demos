package com.michael.demo.spring.cache.caffeine.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.michael.demo.spring.cache.caffeine.base.db.DataSourceType;
import com.michael.demo.spring.cache.caffeine.base.db.annotation.DataSource;
import com.michael.demo.spring.cache.caffeine.base.mapper.MyBaseMapper;
import com.michael.demo.spring.cache.caffeine.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 基础Service 接口实现 基于 MyBaseMapper 的实现<br>
 * 修改数据源可以使用以下方式(默认使用主数据库)<br>
 * <pre>
 *      1. DataSourceHolder.setDataSource(DataSourceType)
 *      2. @DataSource 注解
 * </pre>
 *
 * @author Michael
 * @date 2017/7/8.
 */

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyBaseMapper<T> mapper;

    public MyBaseMapper<T> getMapper() {
        return this.mapper;
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public T getByPK(Object key) {
        return this.mapper.selectByPrimaryKey(key);
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public T getOne(T entity) {
        return this.mapper.selectOne(entity);
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public int getCount(T entity) {
        return this.mapper.selectCount(entity);
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public int getCountByExample(Example example) {
        return this.mapper.selectCountByExample(example);
    }

    @Override
    public int save(T entity) {
        return this.mapper.insert(entity);
    }

    @Override
    public int saveNotNull(T entity) {
        return this.mapper.insertSelective(entity);
    }

    @Override
    public int delete(T entity) {
        return this.mapper.delete(entity);
    }

    @Override
    public int deleteByPK(Object key) {
        return this.mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int deleteByPKS(String keys) {
        return this.mapper.deleteByIds(keys);
    }

    @Override
    public int deleteByExample(Example example) {
        return this.mapper.deleteByExample(example);
    }

    @Override
    public int updateByPK(T entity) {
        return this.mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPKNotNull(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByExample(T entity, Example example) {
        return this.mapper.updateByExample(entity, example);
    }

    @Override
    public int updateByExampleNotNull(T entity, Example example) {
        return this.mapper.updateByExampleSelective(entity, example);
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public List<T> getList(T entity) {
        return this.mapper.select(entity);
    }

    @Override
    public PageInfo<T> getList(T entity, Integer pageNo, Integer pageSize) {
        this.doPage(pageNo, pageSize);
        return new PageInfo<>(this.getList(entity));
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public List<T> getListAll() {
        return this.mapper.selectAll();
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public PageInfo<T> getListAll(Integer pageNo, Integer pageSize) {
        this.doPage(pageNo, pageSize);
        return new PageInfo<>(this.getListAll());
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public List<T> getListByExample(Example example) {
        return this.mapper.selectByExample(example);
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public PageInfo<T> getListByExample(Example example, Integer pageNo, Integer pageSize) {
        this.doPage(pageNo, pageSize);
        return new PageInfo<>(this.getListByExample(example));
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public List<T> getByPKS(String keys) {
        return this.mapper.selectByIds(keys);
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public PageInfo<T> getByPKS(String keys, Integer pageNo, Integer pageSize) {
        this.doPage(pageNo, pageSize);
        return new PageInfo<>(this.getByPKS(keys));
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public boolean isExisis(Object key) {
        return this.mapper.existsWithPrimaryKey(key);
    }

    /**
     * 分页前置处理 (如不传入分页参数, pageNo = 1, pageSize = 10)
     *
     * @param pageNo   页码 default 1
     * @param pageSize 每页大小 default 10
     */
    protected void doPage(Integer pageNo, Integer pageSize) {

        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }

        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNo, pageSize);
    }

    /**
     * 分页前置处理 (如不传入分页参数, pageNo = 1, pageSize = 10)
     *
     * @param pageNo          页码 default 1
     * @param pageSize        每页大小
     * @param defaultPageSize 每页大小默认值,如果该值不合法，默认使用10
     */
    protected void doPage(Integer pageNo, Integer pageSize, Integer defaultPageSize) {

        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }

        if (pageSize == null || pageSize <= 0) {

            if (defaultPageSize == null || defaultPageSize <= 0) {
                pageSize = 10;
            } else {
                pageSize = defaultPageSize;
            }
        }

        PageHelper.startPage(pageNo, pageSize);
    }

    private String errorMsg = "请求错误,请重试";
}
