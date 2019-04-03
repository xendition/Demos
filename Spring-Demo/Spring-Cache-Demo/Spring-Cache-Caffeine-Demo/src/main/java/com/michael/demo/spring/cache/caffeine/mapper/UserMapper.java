package com.michael.demo.spring.cache.caffeine.mapper;

import com.michael.demo.spring.cache.caffeine.base.mapper.MyBaseMapper;
import com.michael.demo.spring.cache.caffeine.model.User;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Michael
 */
@ComponentScan
public interface UserMapper extends MyBaseMapper<User> {
}
