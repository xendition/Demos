package com.michael.demo.designmodel.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 动态代理类
 *
 * @author Michael
 */
public class ProxyFactory implements MethodInterceptor {

    /**
     * 维护一个目标对象z
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象创建一个代理对象
     */
    public Object getProxyInstance() {
        //1.工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(this.target.getClass());
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.创建子类(代理对象)
        return enhancer.create();

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        this.before();

        //执行目标对象的方法
        Object returnValue = method.invoke(this.target, args);

        this.after();

        return returnValue;
    }

    private void before() {
        System.out.println("开始事务...");
    }

    private void after() {
        System.out.println("提交事务...");
    }
}
