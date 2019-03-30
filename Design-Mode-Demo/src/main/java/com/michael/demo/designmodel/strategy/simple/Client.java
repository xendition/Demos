package com.michael.demo.designmodel.strategy.simple;

/**
 * 高层模块 - 调用端</br>
 * PS:单一的使用策略模式会增加代码的耦合度,建议使用混合模式
 *
 * @author Michael
 */
public class Client {
    public static void main(String[] args) {

        //声明一个具体的策略
        Strategy strategy = new ConcreteStrategy1();
        //声明上下文对象
        Context context = new Context(strategy);
        //执行封装后的方法
        context.doAnythinig();
    }
}
