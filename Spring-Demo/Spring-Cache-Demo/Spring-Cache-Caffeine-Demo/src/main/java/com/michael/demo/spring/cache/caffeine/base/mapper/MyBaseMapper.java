package com.michael.demo.spring.cache.caffeine.base.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper
 * 特别注意，该接口不能被扫描到，否则会出错
 *
 * @author Michael
 * @date 2018/6/3
 */
public interface MyBaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
}
