package com.michael.demo.designmodel.facade;


/**
 * 购物实现 - 业务B
 *
 * @author Michael
 */
public class ShoppingImpl implements Shopping {

    @Override
    public void buy(String something) {

        System.out.println("购买了一个..." + something);
    }
}
