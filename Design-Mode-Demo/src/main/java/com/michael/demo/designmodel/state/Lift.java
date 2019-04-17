package com.michael.demo.designmodel.state;

/**
 * 上下文 - 环境
 *
 * @author Michael
 */
public class Lift {

    /** 定义出所有的电梯状态 */
    public final static OpenningState OPENNING_STATE = new OpenningState();
    public final static ClosingState CLOSING_STATE = new ClosingState();
    public final static RunningState RUNNING_STATE = new RunningState();
    public final static StoppingState STOPPING_STATE = new StoppingState();

    /** 定义一个当前电梯状态 */
    private AbstractLiftState liftState;

    public AbstractLiftState getLiftState() {
        return this.liftState;
    }

    public void setLiftState(AbstractLiftState liftState) {
        this.liftState = liftState;
        //把当前的环境通知到各个实现类中
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }

    public void stop() {
        this.liftState.stop();
    }
}
