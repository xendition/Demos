package com.michael.demo.designmodel.proxy.simple;

/**
 * 测试调用类 - 静态代理 - 显示定义一个业务实现类代理
 *
 * @author Michael
 */
public class Main {

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDaoImpl();

        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);

        proxy.save();//执行的是代理的方法
    }
}
