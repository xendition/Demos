package com.michael.demo.designmodel.proxy.cglib;


/**
 * 目标对象
 *
 * @author Michael
 */
public class UserDaoImpl {
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
