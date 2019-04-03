package com.michael.demo.jdk.thread.lock.distributed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Michael
 */
public class OrderCodeGenerator {

    private static AtomicInteger NO = new AtomicInteger();

    public String generatorOrderCode() {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd-hh-mm-sss-");


        return sdf.format(new Date()) + NO.getAndIncrement();
    }
}
