package com.michael.demo.designmodel.proxy.simple;

/**
 * 接口实现
 * 目标对象
 *
 * @author Michael
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
