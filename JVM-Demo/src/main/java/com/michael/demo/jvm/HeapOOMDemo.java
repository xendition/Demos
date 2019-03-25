package com.michael.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟JAVA推内存
 *
 * <p>OOM异常 JVM参数如下
 *
 * <p>-Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author Michael
 */
public class HeapOOMDemo {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
