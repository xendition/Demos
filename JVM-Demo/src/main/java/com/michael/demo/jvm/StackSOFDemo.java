package com.michael.demo.jvm;

/**
 * 栈溢出Demo
 * <p>
 * JVM参数：-Xss128k
 * <p>
 * 单线程下，无论是由于栈桢太大还是虚拟机栈容量太小，当内存无法分配的时候，虚拟机都会抛出 SOF 异常
 *
 * @author Michael
 */
public class StackSOFDemo {

    private int stackLength = 1;

    public void stackLeak() {
        this.stackLength++;
        this.stackLeak();
    }

    public static void main(String[] args) {
        StackSOFDemo demo = new StackSOFDemo();

        try {
            demo.stackLeak();
        } catch (Throwable e) {
            System.out.println("Stack length : " + demo.stackLength);
            throw e;
        }
    }
}
