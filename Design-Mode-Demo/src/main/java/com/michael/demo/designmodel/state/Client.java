package com.michael.demo.designmodel.state;

/**
 * 状态模式 —— 使用场景类
 *
 * @author Michael
 */
public class Client {

    public static void main(String[] args) {
        Lift context = new Lift();
        context.setLiftState(Lift.CLOSING_STATE);

        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
