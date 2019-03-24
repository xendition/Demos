package com.michael.demo.jdk.datatype;

/**
 * @author Michael
 */
public class IntegerDemo {

    public static void main(String[] args) {

        testIncrement();
    }

    /**
     * 测试 ++i 与 i++ 的区别
     */
    public static void testIncrement() {

        int i = 1;
        int j = i++;

        StringDemo.println("int i = 1;\nint j = i++;");
        StringDemo.println("当前i = {0} j = {1}", i, j);
        StringDemo.println("j = i++ 先将i的值赋值给j,然后i的值+1");
        StringDemo.println("");

        i = 2;
        j = ++i;

        StringDemo.println("int i = 2;\nint j = ++i;");
        StringDemo.println("当前i = {0} j = {1}", i, j);
        StringDemo.println("j = i++ 先将i的值+1,然后赋值给j");
        StringDemo.println("");
    }
}
