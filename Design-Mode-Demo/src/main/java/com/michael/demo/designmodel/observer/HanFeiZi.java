package com.michael.demo.designmodel.observer;

import java.util.Observable;

/**
 * 被观察者——韩非子
 *
 * @author Michael
 */
public class HanFeiZi extends Observable {

    /** 吃饭 */
    public void doEat() {
        System.out.println("韩非子：开始吃饭...");
        super.setChanged();
        super.notifyObservers("韩非子开始吃饭了...");
    }

    /** 玩耍 */
    public void doFun() {
        System.out.println("韩非子：开始玩耍...");
        super.setChanged();
        super.notifyObservers("韩非子开始玩耍了...");
    }
}
