package com.michael.demo.jdk.annotation.simple;

/**
 * @author Michael
 */
public class Main {
    //
    // static Object pool;
    //
    // static {
    //
    //     Field field = null;
    //     try {
    //         field = ConstantPool.class.getDeclaredField("constantPoolOop");
    //
    //         field.setAccessible(true);
    //
    //         pool = field.get(null);
    //
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void main(String[] args) {

        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // ConstantPool pool = new ConstantPool();
        // System.out.println(pool.getSize());

        FruitInfoUtil.getFruitInfo(Apple.class);
        int i = 1;
    }
}
