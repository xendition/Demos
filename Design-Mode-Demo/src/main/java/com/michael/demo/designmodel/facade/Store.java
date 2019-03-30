package com.michael.demo.designmodel.facade;

/**
 * 小商店,只能购物 - 对外的门面B
 *
 * @author Michael
 */
public class Store {

    private Shopping shopping = new ShoppingImpl();

    /**
     * 邮局开展新的业务 - 代客人购物
     */
    public void buy(String something) {
        System.out.println("小店代购物品中...");
        shopping.buy(something);
    }
}
