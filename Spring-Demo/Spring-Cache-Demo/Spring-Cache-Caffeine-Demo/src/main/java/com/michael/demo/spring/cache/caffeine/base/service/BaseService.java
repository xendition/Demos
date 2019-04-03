package com.michael.demo.spring.cache.caffeine.base.service;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 基础Service 接口 基于 MyBaseMapper
 *
 * @author Michael
 * @date 2017/7/8.
 */
public interface BaseService<T> {

    // ########################       查询单条记录          ##############################

    /**
     * 根据主键值查询对象
     *
     * @param key 主键值
     *
     * @return 对应实体
     */
    T getByPK(Object key);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果会抛出异常，查询条件使用等号
     *
     * @param entity 查询条件，必需设定至少一个非NULL属性值，避免全表查询
     *
     * @return 抛出异常(查询结果有多个) NULL(未查询到结果) 目标实体(根据条件查询到唯一的记录)
     */
    T getOne(T entity);

    // ########################       查询记录总数          ##############################

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param entity 查询条件
     *
     * @return 符合查询条件的记录总数
     */
    int getCount(T entity);

    /**
     * 根据Example条件进行查询总数
     *
     * @param example 查询条件
     *
     * @return 符合查询条件的记录总数
     *
     * @see Example
     */
    int getCountByExample(Example example);

    // ########################       增加一条记录          ##############################

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity 需要保存到数据库的实体
     *
     * @return 当前SQL执行影响的表记录行数
     */
    int save(T entity);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity 需要保存到数据库的实体
     *
     * @return 当前SQL执行影响的表记录行数
     */
    int saveNotNull(T entity);

    // ########################       删除记录          ##############################

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param entity 删除条件
     *
     * @return 当前SQL执行影响的表记录行数
     */
    int delete(T entity);

    /**
     * 根据主键值删除表中的实体数据
     *
     * @param key 主键值
     *
     * @return 当前SQL执行影响的表记录行数
     */
    int deleteByPK(Object key);

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     *
     * @param keys "1,2,3,4"
     *             如果主键为varchar 参数大致如下:
     *             "'key1','key2','key3'..." 里面的值会生成 id IN ($keys)
     *
     * @return 当前SQL执行影响的表记录行数
     */
    int deleteByPKS(String keys);

    /**
     * 根据Example条件删除数据
     *
     * @param example 查询条件
     *
     * @return 当前SQL执行影响的表记录行数
     *
     * @see Example
     */
    int deleteByExample(Example example);

    // ########################       修改记录          ##############################

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity 表中最终存储的数据
     *
     * @return 当前SQL执行影响的表记录行数
     */
    int updateByPK(T entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity 需要更新的实体
     *
     * @return 当前SQL执行影响的表记录行数
     */
    int updateByPKNotNull(T entity);

    /**
     * 根据Example条件更新实体包含的全部属性，null值会被更新
     *
     * @param entity  需要更新的最终值(主键不会被更新)
     * @param example 更新的条件
     *
     * @return 当前SQL执行影响的表记录行数
     *
     * @see Example
     */
    int updateByExample(T entity, Example example);

    /**
     * 根据Example条件更新实体包含的非NULL属性值
     *
     * @param entity  需要更新的最终值(主键不会被更新)
     * @param example 更新的条件
     *
     * @return 当前SQL执行影响的表记录行数
     *
     * @see Example
     */
    int updateByExampleNotNull(T entity, Example example);

    // ########################       查询多条记录          ##############################

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity 查询条件
     *
     * @return 查询的结果集
     */
    List<T> getList(T entity);

    /**
     * 根据实体中的属性值进分页查询，查询条件使用等号
     *
     * @param entity   查询条件
     * @param pageNo   查询条几页
     * @param pageSize 查询多少条数据
     *
     * @return 查询的结果集
     */
    PageInfo<T> getList(T entity, Integer pageNo, Integer pageSize);

    /**
     * 查询全部结果(无条件)
     *
     * @return 查询的结果集
     */
    List<T> getListAll();

    /**
     * 分页查询全部结果(无条件)
     *
     * @param pageNo   查询条几页
     * @param pageSize 查询多少条数据
     *
     * @return PageInfo
     *
     * @see PageInfo
     */
    PageInfo<T> getListAll(Integer pageNo, Integer pageSize);

    /**
     * 根据Example条件进行查询
     *
     * @param example 查询条件
     *
     * @return 查询的结果集
     *
     * @see Example
     */
    List<T> getListByExample(Example example);

    /**
     * 根据Example条件进行分页查询
     *
     * @param example  查询条件
     * @param pageNo   查询条几页
     * @param pageSize 查询多少条数据
     *
     * @return 查询的结果集
     *
     * @see Example
     */
    PageInfo<T> getListByExample(Example example, Integer pageNo, Integer pageSize);

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     *
     * @param keys "1,2,3,4"
     *             如果主键为varchar 参数大致如下:
     *             "'key1','key2','key3'..." 里面的值会生成 id IN ($keys)
     *
     * @return 查询的结果集
     */
    List<T> getByPKS(String keys);

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     *
     * @param keys     "1,2,3,4"
     *                 如果主键为varchar 参数大致如下:
     *                 "'key1','key2','key3'..." 里面的值会生成 id IN ($keys)
     * @param pageNo   查询条几页
     * @param pageSize 查询多少条数据
     *
     * @return 查询的结果集
     */
    PageInfo<T> getByPKS(String keys, Integer pageNo, Integer pageSize);

    // ########################       记录是否存在          ##############################

    /**
     * 根据主键字段查询总数，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key 主键
     *
     * @return 查询总数是否 > 0
     */
    boolean isExisis(Object key);
}
