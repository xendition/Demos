package com.michael.demo.designmodel.proxy.dynamic;


/**
 * 测试调用类 - JDK动态代理 - 使用接口实现
 *
 * <pre>
 *     为什么JDK动态代理不支持非接口实现类?
 *          因为生成的代理类继承了Proxy并实现了要代理的接口，JAVA不支持多继承，所以JDK动态代理不能代理类
 *     JDK动态代理实现原理?
 *          流程：
 *              1.为接口创建代理类的字节码文件(class文件)
 *              2.使用ClassLoader将字节码文件加载到JVM
 *              3.创建代理类的实例对象，执行对象的目标方法
 *
 *              PS:执行时使用 InvocationHandler.invoke()方式，InvocationHandler的实现类在动态代理类中定义(可对原方法进行增强处理)
 * </pre>
 *
 * @author Michael
 */
public class Main {

    public static void main(String[] args) {

        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 目标对象
        UserDao target = new UserDaoImpl();
        // 【原始的类型 class com.michael.demo.designmodel.proxy.dynamic.UserDaoImpl】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}