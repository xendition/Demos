package com.michael.demo.designmodel.strategy.simple;

/**
 * 具体策略角色 - 2
 *
 * @author Michael
 */
public class ConcreteStrategy2 implements Strategy {

    @Override
    public void doSomething() {
        System.out.println("具体策略2的运算法则");
    }
}
