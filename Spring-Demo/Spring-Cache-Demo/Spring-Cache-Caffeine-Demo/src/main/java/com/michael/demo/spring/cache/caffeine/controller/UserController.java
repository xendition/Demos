package com.michael.demo.spring.cache.caffeine.controller;

import com.michael.demo.spring.cache.caffeine.model.User;
import com.michael.demo.spring.cache.caffeine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Michael
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String save(User user) {
        this.userService.save(user);

        return "SUCCESS";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String userId) {
        this.userService.deleteUser(userId);

        return "SUCCESS";
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") String userId) {
        return this.userService.getUser(userId);

    }

    @PutMapping
    public String update(User user) {
        this.userService.updateUser(user);

        return "SUCCESS";
    }
}
