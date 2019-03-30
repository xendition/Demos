package com.michael.demo.designmodel.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者 —— 王斯
 *
 * @author Michael
 */
public class WangSi implements Observer {

    @Override
    public void update(Observable observable, Object data) {

        System.out.println("王斯:观察到韩非子的活动,开始处理");
        this.doCry(data.toString());
        System.out.println("王斯:完成处理工作");
    }

    private void doCry(String reportContext) {
        System.out.println("王斯哭了,因为 -> " + reportContext);
    }
}
