package com.michael.demo.designmodel.proxy.simple;

/**
 * 静态代理类
 *
 * @author Michael
 */
public class UserDaoProxy implements UserDao {

    /**
     * 接收保存目标对象
     */
    private UserDao target;

    public UserDaoProxy(UserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        this.before();
        //执行目标对象的方法
        this.target.save();
        this.after();
    }

    private void before() {
        System.out.println("开始事务...");
    }

    private void after() {
        System.out.println("提交事务...");
    }
}
