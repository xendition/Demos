package com.michael.demo.designmodel.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者 —— 李斯
 *
 * @author Michael
 */
public class LiSi implements Observer {

    @Override
    public void update(Observable observable, Object data) {

        System.out.println("李斯:观察到韩非子的活动,开始汇报工作");
        this.doReport(data.toString());
        System.out.println("李斯:汇报完毕");
    }

    private void doReport(String reportContext) {
        System.out.println("李斯:报告老板，韩非子有活动了 -> " + reportContext);
    }
}
