package com.michael.demo.designmodel.observer;

import java.util.Observer;

/**
 * 观察者模式测试
 *
 * @author Michael
 */
public class MainTest {

    public static void main(String[] args) {
        Observer lisi = new LiSi();
        Observer wangSi = new WangSi();

        HanFeiZi hanFeiZi = new HanFeiZi();
        hanFeiZi.addObserver(wangSi);
        hanFeiZi.addObserver(lisi);

        hanFeiZi.doEat();
        hanFeiZi.doFun();
    }
}
