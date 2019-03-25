package com.michael.demo.jvm;

/**
 * 栈溢出Demo
 * <p>
 * JVM参数：-Xss2m
 * <p>
 * 多线程下，栈越大越容易产生内存溢出
 * <p>
 * WINDOWS下可能死机，慎用
 *
 * @author Michael
 */
public class StackOOMDemo {

    private void dontStop() {
        while (true) {}
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    StackOOMDemo.this.dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOOMDemo demo = new StackOOMDemo();

        demo.stackLeakByThread();
    }
}
