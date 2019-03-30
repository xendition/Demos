package com.michael.demo.designmodel.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 *
 * @author Michael
 */
public class ProxyFactory implements InvocationHandler {

    /**
     * 维护一个目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成代理对象
     */
    public Object getProxyInstance() {

        return Proxy.newProxyInstance(
                this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    this.before();
                    //执行目标对象方法
                    Object returnValue = method.invoke(this.target, args);
                    this.after();
                    return returnValue;
                }
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    private void before() {
        System.out.println("开始事务...");
    }

    private void after() {
        System.out.println("提交事务...");
    }
}
