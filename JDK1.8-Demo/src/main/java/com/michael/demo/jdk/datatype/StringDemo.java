package com.michael.demo.jdk.datatype;

import java.text.MessageFormat;
import java.util.Random;

/**
 * @author Michael
 */
public class StringDemo {


    private static Integer MAX = 10 * 1000 * 1000;

    public static void main(String[] args) {

//        stringInitTest();

//        try {
//            Thread.sleep(20000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        internTest0();


        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        internTest1();
    }

    /**
     * 不使用intern,大数据量时内存狂升,执行速率低,小数据量时速度快于使用intern
     */
    public static void internTest0() {
        
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        String[] arr = new String[MAX];
        for (int i = 0; i < MAX; i++) {
            // --- 每次都要new一个对象
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
        }

        System.out.println("不加intern() 耗时" + (System.currentTimeMillis() - t) + "ms");
        System.gc();
    }

    /**
     * 使用intern,降低内存,数据量大时提升执行速率
     */
    public static void internTest1() {

        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        String[] arr = new String[MAX];
        for (int i = 0; i < MAX; i++) {
            //其实虽然这么多字符串，但是类型最多为10个，大部分重复的字符串,大大减少内存
             arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

        System.out.println("加intern() 耗时" + (System.currentTimeMillis() - t) + "ms");
        System.gc();
    }


    /**
     * String的＝＝和equals是不同的，＝＝比较的是两个String在内存中的地址是否相同，equals比较的是两个String的值是否相同
     */
    public static void stringInitTest() {

        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = "ab" + "c";

        String s4 = "abcd";
        String s5 = s1 + "d";
        String s6 = (s1 + "d").intern();


        println("");
        System.out.println("String s1 = new String(\"abc\");\nString s2 = \"abc\"; \nString s3 = \"ab\" + \"c\";\n\nString s4 = \"abcd\";\nString s5 = s1 + \"d\";\nString s6 = (s1 + \"d\").intern();");
        println("");
        println("System.identityHashCode(Object) 返回对象最原始的HashCode值,不管有没有复写HashCode方法");

        println("System.identityHashCode(s1) => {0}", System.identityHashCode(s1));
        println("System.identityHashCode(s2) => {0}", System.identityHashCode(s2));
        println("System.identityHashCode(s3) => {0}", System.identityHashCode(s3));
        println("System.identityHashCode(s4) => {0}", System.identityHashCode(s4));
        println("System.identityHashCode(s5) => {0}", System.identityHashCode(s5));
        println("System.identityHashCode(s6) => {0}", System.identityHashCode(s6));
        println("");
        println("s1.equals(s2) => {0}", s1.equals(s2));
        println("s2.equals(s3) => {0}", s2.equals(s3));
        println("s1==s2 => {0}", s1 == s2);
        println("s2==s3 => {0}", s2 == s3);
        println("s2与s3是等价的");

        println("");
        println("s4==s5 => {0}", s4 == s5);
        println("s4==s6 => {0}", s4 == s6);
        println("");
    }

    private static void println(String pattern, Object... arguments) {
        System.out.println(MessageFormat.format(pattern, arguments));
    }

}
