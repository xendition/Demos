package com.michael.demo.jdk.annotation.common;

import javax.annotation.PostConstruct;

/**
 * 常用注解使用 - PostConstruct
 *
 * @author Michael
 */
public class PostConstructDemo {

    public static void main(String[] args) {

        PostConstructDemo demo = new PostConstructDemo();
    }

    public PostConstructDemo() {

        System.out.println("调用构造方法");
    }


    @PostConstruct
    public void init() {

        System.out.println("调用@PostConstruct注释的方法");
    }
}
