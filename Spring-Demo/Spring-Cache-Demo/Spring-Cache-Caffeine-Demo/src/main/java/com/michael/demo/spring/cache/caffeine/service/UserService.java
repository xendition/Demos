package com.michael.demo.spring.cache.caffeine.service;

import com.michael.demo.spring.cache.caffeine.base.service.BaseService;
import com.michael.demo.spring.cache.caffeine.model.User;

/**
 * @author Michael
 */
public interface UserService extends BaseService<User> {

    int saveUser(User user);

    int deleteUser(String userId);

    int updateUser(User user);

    User getUser(String userId);

}
