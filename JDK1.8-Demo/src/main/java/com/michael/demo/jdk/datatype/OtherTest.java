package com.michael.demo.jdk.datatype;

public class OtherTest {

    public static void main(String[] args) {

        // int n = 10;
        // for (int i = 0; i < n; i--) {
        //     System.out.print('#');
        // }

        test1();
    }

    public static void test1() {

        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.print('#');
        }
    }

    public static void test2() {

        int n = 10;
        for (int i = 0; i < n; i--) {
            System.out.print('#');
        }
    }


    public static void test3() {

        int n = 10;
        for (int i = 0; -i < n; i--) {
            System.out.print('#');
        }
    }

    public static void test4() {

        int n = 10;
        for (int i = 0; i < n; n--) {
            System.out.print('#');
        }
    }
}
