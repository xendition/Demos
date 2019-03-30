package com.michael.demo.designmodel.strategy.simple;

/**
 * 具体策略角色 - 1
 *
 * @author Michael
 */
public class ConcreteStrategy1 implements Strategy {

    @Override
    public void doSomething() {
        System.out.println("具体策略1的运算法则");
    }
}
