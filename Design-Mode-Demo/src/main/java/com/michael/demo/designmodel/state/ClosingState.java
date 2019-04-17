package com.michael.demo.designmodel.state;

/**
 * 关门状态
 *
 * @author Michael
 */
public class ClosingState extends AbstractLiftState {

    /** 电梯门关闭，这是关闭状态要实现的动作 */
    @Override
    public void close() {
        System.out.println("电梯门关闭...");
    }

    /** 电梯门关了再打开 */
    @Override
    public void open() {
        //置为敞门状态
        super.context.setLiftState(Lift.OPENNING_STATE);
        super.context.getLiftState().open();
    }

    /** 电梯门关了就运行，这是再正常不过了 */
    @Override
    public void run() {
        //设置为运行状态
        super.context.setLiftState(Lift.RUNNING_STATE);
        super.context.getLiftState().run();
    }

    /** 电梯门关着，我就不按楼层 */
    @Override
    public void stop() {
        //设置为停止状态
        super.context.setLiftState(Lift.STOPPING_STATE);
        super.context.getLiftState().stop();
    }
}
