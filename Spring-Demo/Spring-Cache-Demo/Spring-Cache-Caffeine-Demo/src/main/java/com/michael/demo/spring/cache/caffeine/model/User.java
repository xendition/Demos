package com.michael.demo.spring.cache.caffeine.model;

import com.michael.demo.spring.cache.caffeine.base.model.BaseIdStringModel;

/**
 * 实体样例
 *
 * @author Michael
 */
public class User extends BaseIdStringModel {

    private String name;

    private Integer age;

    private String address;

    public User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
