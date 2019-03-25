package com.michael.demo.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 常量池导致的OOM异常DEMO
 * <p>JVM参数： 1.7  -XX:PermSize=10M -XX:MaxPermSize=10M TODO 未验证出来
 * <p>JVM参数： 1.8  -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m TODO 无效
 *
 * @author Michael
 */
public class RuntimeConstatPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while (true) {
            String uuid = UUID.randomUUID().toString();
            // System.out.println(uuid);
            list.add(uuid.intern());
        }
    }
}
