package com.michael.demo.spring.cache.caffeine.service.impl;

import com.michael.demo.spring.cache.caffeine.base.service.impl.BaseServiceImpl;
import com.michael.demo.spring.cache.caffeine.model.User;
import com.michael.demo.spring.cache.caffeine.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Michael
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    @CachePut(value = "user", key = "'user'.concat(#user.id)")
    public int saveUser(User user) {
        return this.save(user);
    }

    @Override
    @CacheEvict(value = "user", key = "'user'.concat(#userId)")
    public int deleteUser(String userId) {
        return this.deleteByPK(userId);
    }

    @Override
    @Cacheable(value = "user", key = "'user'.concat(#userId)")
    public User getUser(String userId) {
        return this.getByPK(userId);
    }

    @Override
    @CachePut(value = "user", key = "'user'.concat(#user.id)")
    public int updateUser(User user) {
        return this.updateByPK(user);
    }
}
