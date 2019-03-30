package com.michael.demo.designmodel.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * 测试调用类 - CGLIB 动态代理 - 使用继承实现
 *
 * @author Michael
 */
public class Main {

    public static void main(String[] args) {

        // 开启CGLIB DEBUG模式，将生成的代理类写入文件中
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\proxy");

        //目标对象
        UserDaoImpl target = new UserDaoImpl();
        //代理对象
        UserDaoImpl proxy = (UserDaoImpl) new ProxyFactory(target).getProxyInstance();
        //执行代理对象的方法
        proxy.save();
    }
}
